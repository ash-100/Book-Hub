package com.example.bookhub.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bookhub.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    lateinit var editName:EditText
    lateinit var editEmail:EditText
    lateinit var editPassword:EditText
    lateinit var editConfirmPassword:EditText
    lateinit var btnSignUp:Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        editName=findViewById(R.id.name)
        editEmail=findViewById(R.id.email)
        editPassword=findViewById(R.id.passwordInRegister)
        editConfirmPassword=findViewById(R.id.confirmPassword)
        btnSignUp=findViewById(R.id.signUpButton)

        val name=editName.text.toString()
        val email=editEmail.text.toString()
        val password=editPassword.text.toString()
        val confirmPassword=editConfirmPassword.text.toString()

        auth= FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val progressBar = Dialog(this)
            progressBar.setContentView(R.layout.progress_bar)
            progressBar.show()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                }



        }
    }


}