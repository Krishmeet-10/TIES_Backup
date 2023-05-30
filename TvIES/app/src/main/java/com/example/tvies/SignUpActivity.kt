package com.example.tvies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private lateinit var submit_btn :Button
    private lateinit var name_txt: EditText
    private lateinit var password_txt : EditText
    private lateinit var email_txt: EditText

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        submit_btn = findViewById(R.id.button3)
        name_txt = findViewById(R.id.txt3_name)
        password_txt = findViewById(R.id.txt2_pw)
        email_txt = findViewById(R.id.txt1_email)

        database = Firebase.database.reference

        submit_btn.setOnClickListener{
            val name = name_txt.text.toString()
            val email = email_txt.text.toString()
            val password_txt = password_txt.text.toString()

            Signup_Auth(name,email,password_txt)
        }

    }

    private fun Signup_Auth(name:String,email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(this,auth.currentUser?.uid!!,Toast.LENGTH_SHORT).show()
                    addusertodb(name,email,auth.currentUser?.uid.toString())

                    val intent = Intent(this@SignUpActivity,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }
    }
    companion object {
        private const val TAG = "EmailPassword"
    }

    private fun addusertodb(name: String, email: String, uid: String) {
        Toast.makeText(this,"add user to db",Toast.LENGTH_LONG).show()
        database.child("users").push().setValue(User(name,email,uid)).addOnCompleteListener {
            task->
            if(task.isSuccessful){
                Toast.makeText(this,"Success Data",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"failed",Toast.LENGTH_LONG).show()
            }
        }
    }



}