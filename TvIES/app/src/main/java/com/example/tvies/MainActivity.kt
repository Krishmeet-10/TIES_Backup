package com.example.tvies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var chat_rv :RecyclerView
    private lateinit var userlist: ArrayList<User_Data>
    private lateinit var adapter: user_rv_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userlist = ArrayList()
        adapter = user_rv_adapter(this,userlist)

        chat_rv = findViewById(R.id.User_rv)
    }
}