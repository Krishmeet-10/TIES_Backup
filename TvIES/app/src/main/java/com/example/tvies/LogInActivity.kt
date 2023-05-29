package com.example.tvies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var signup_btn:Button
    private lateinit var submit_btn:Button
    private lateinit var Email:EditText
    private lateinit var Password:EditText

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        signup_btn = findViewById(R.id.button2)
        Email = findViewById(R.id.email_login_txt)
        Password = findViewById(R.id.pw_txt_login)
        submit_btn = findViewById(R.id.button1)


        signup_btn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        submit_btn.setOnClickListener{
            val email = Email.text.toString()
            val password = Password.text.toString()

            login_Auth(email,password)
        }


    }

    fun login_Auth(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val intent = Intent(this@LogInActivity,MainActivity::class.java)
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
    private fun reload() {
    }

}