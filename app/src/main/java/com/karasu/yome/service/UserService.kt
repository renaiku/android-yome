package com.karasu.yome.service

import com.auth0.android.jwt.JWT
import com.karasu.yome.model.User
import java.util.*

object UserService {

    private lateinit var currentUser: User
    private var roles: Array<String> = arrayOf()

    var token = ""

    fun getCurrentUser(): User? {
        return currentUser
    }

    fun setCurrentUser(user: User) {
        println("current user set")
        println("$user")
        currentUser = user
        setRolesAndToken()
    }

    private fun setRolesAndToken() {

        if (currentUser.token != null) {
            token = currentUser.token

            val jwt = JWT(token)
            val claim = jwt.getClaim("role")
            var role = claim.asString() ?: ""

            if (role.isNotEmpty()) {
                roles = arrayOf(role)
            } else {
                roles = claim.asArray(String::class.java)
            }
        }
    }

    fun isAdmin(): Boolean {
        return Arrays.stream(roles).anyMatch { t -> t == "Admin" }
    }
}