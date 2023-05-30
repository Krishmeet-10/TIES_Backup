package com.example.tvies

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val name: String? = null, val email: String? = null, val userid: String? = null) {
}
