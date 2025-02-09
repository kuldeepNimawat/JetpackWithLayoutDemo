package com.compose.jetpackwithlayoutdemo.firebaseauth

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.jetpackwithlayoutdemo.ui.theme.GREEN
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun FirebaseAuthDemo() {
    Box(modifier = Modifier.fillMaxSize())
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(color = GREEN),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Firebase Authentication Demo", color = Color.White, fontSize = 20.sp)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            authenticationView(LocalContext.current)
        }
    }
}

@Composable
fun authenticationView(context: Context) {
    val phoneNumber = remember {
        mutableStateOf("")
    }

    val otp = remember {
        mutableStateOf("")
    }

    val verificationID = remember {
        mutableStateOf("")
    }

    val message = remember {
        mutableStateOf("")
    }

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Enter your Phone Number") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (TextUtils.isEmpty(phoneNumber.value)) {
                    Toast.makeText(context, "Please enter phone number..", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val number = "+91${phoneNumber.value}"
                    sentVarificationCode(number, mAuth, context as Activity, callbacks)
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Generate OTP", modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = otp.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                otp.value = it
            },
            placeholder = { Text(text = "Enter your OTP") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
            singleLine = true
        )

        Button(
            onClick = {
                if (TextUtils.isEmpty(otp.value)) {
                    Toast.makeText(context, "Please enter otp..", Toast.LENGTH_SHORT).show()
                } else {
                    val credentials: PhoneAuthCredential =
                        PhoneAuthProvider.getCredential(verificationID.value, otp.value)
                    signInWithPhoneAuthCredential(
                        credentials,
                        mAuth,
                        context as Activity,
                        context,
                        message
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Verify OTP", modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = message.value,
            style = TextStyle(color = GREEN, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
    }

    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            message.value = "Verification Successful"
            Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            message.value = "Fail to verify user : \n" + p0.message
            Toast.makeText(context, "Verification failed..", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
        }

    }
}

fun signInWithPhoneAuthCredential(
    credentials: PhoneAuthCredential,
    mAuth: FirebaseAuth,
    activity: Activity,
    context: Activity,
    message: MutableState<String>
) {
   mAuth.signInWithCredential(credentials)
       .addOnCompleteListener(activity){ task ->
          if(task.isSuccessful){
              message.value = "Verification successful"
              Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
          }else{
              if(task.exception is FirebaseAuthInvalidCredentialsException){
                  Toast.makeText(context, "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                      Toast.LENGTH_SHORT).show()
              }
          }
       }
}

fun sentVarificationCode(
    number: String,
    mAuth: FirebaseAuth,
    activity: Activity,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
   val options = PhoneAuthOptions.newBuilder(mAuth)
       .setPhoneNumber(number)
       .setTimeout(60L,TimeUnit.SECONDS)
       .setActivity(activity)
       .setCallbacks(callbacks)
       .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}
