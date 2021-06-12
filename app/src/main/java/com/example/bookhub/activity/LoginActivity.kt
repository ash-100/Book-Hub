package com.example.bookhub.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookhub.R
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    lateinit var forgotPassword: TextView
    lateinit var loginButton: Button
    lateinit var txtsignUp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        forgotPassword=findViewById(R.id.forgotPassword)
        loginButton=findViewById(R.id.loginButton)
        txtsignUp=findViewById(R.id.dontHaveAnAccount)

        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        loginButton.setOnClickListener {
            val progressBar=Dialog(this)
            progressBar.setContentView(R.layout.progress_bar)
            progressBar.show()

            var email=findViewById<EditText>(R.id.email).text.toString()
            val password=findViewById<EditText>(R.id.password).text.toString()

            if(email !="" && password !="" ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"You have been logged in",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            progressBar.hide()
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Login Failed",Toast.LENGTH_LONG).show()
                            progressBar.hide()
                        }

                    }
            }
            else{
                Toast.makeText(this, " Please Enter all credentials",Toast.LENGTH_LONG).show()
            }
        }

        txtsignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}