package com.example.tvies

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User_Data (val name:String? = null, val email:String? = null, val uid:String? = null){
}
