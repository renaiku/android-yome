package com.karasu.yome.acitivtiy

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.karasu.yome.R
import com.karasu.yome.databinding.ActivityLoginBinding
import com.karasu.yome.model.User
import com.karasu.yome.service.ServerService
import com.karasu.yome.service.UserService
import com.karasu.yome.ui.login.*
import com.karasu.yome.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var users: MutableList<User> = mutableListOf()

    private val loginViewModel: LoginViewModel by viewModels()
    private val serverViewModel: ServerViewModel by viewModels()
    private val addUserViewModel: AddUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isServerAddressDefined()) {
            showFragment(ServerFragment())
        }

        users = getUsersFromFile()
        loginViewModel.setUsers(users)

        // ServerFragment.validateInput() -> ServerViewModel -> this observer
        serverViewModel.serverAddress.observe(this, Observer { newAddress ->
            saveServerAddress(newAddress)
            showFragment(LoginFragment())
        })

        // AddUserFragment.validateInput() -> AddUserViewModel -> this observer
        addUserViewModel.user.observe(this, Observer { newUser ->
            val saved = saveUserToFile(newUser)
            if (saved) showFragment(LoginFragment())
        })
    }

    fun onModifyServerButtonClicked(v: View) {
        showFragment(ServerFragment())
    }

    fun onAddUserButtonClicked(view: View) {
        showFragment(AddUserFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment_content_login, fragment)
            setReorderingAllowed(true)
            addToBackStack("name") // name can be null
        }
    }

    private fun saveServerAddress(address: String) {
        val sharedPref = getPreferences(MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(getString(R.string.saved_server_address), address)
            apply()
        }
        println("SAVED SERVER ADDRESS: $address")
        ServerService.setUrl(address)
    }

    private fun isServerAddressDefined(): Boolean {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val key = getString(R.string.saved_server_address)
        val found = sharedPref.getString(key, null) ?: return false
        println("FOUND SERVER ADDRESS IN LOCAL STORAGE: $found")
        ServerService.setUrl(found)
        return true
    }

    private fun getUsersFromFile(): MutableList<User> {
        var mUsers = mutableListOf<User>()
        try {
            val raw = openFileInput("users").bufferedReader().readText()
            mUsers = Json.decodeFromString(raw)
        } catch (ex: FileNotFoundException) {
            System.err.println("No savefile yet !");
        }

        println("USERS FROM FILE:")
        println(mUsers.toString())
        return mUsers
    }

    private fun saveUserToFile(newUser: User): Boolean {
        val user = users.find{ it.apiKey == newUser.apiKey }

        if (user != null) {
            Utils.showToast(this, "A user with the same API Key already exists: ${user.username}")
            return false
        } else {
            users.add(newUser)

            val usersAsString = Json.encodeToString(users)
            openFileOutput("users", Context.MODE_PRIVATE).use {
                it.write(usersAsString.toByteArray())
            }

            loginViewModel.setUsers(users)
            return true
        }
    }
}