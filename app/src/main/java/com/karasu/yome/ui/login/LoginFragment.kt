package com.karasu.yome.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.karasu.yome.acitivtiy.MainActivity
import com.karasu.yome.adapter.UsersAdapter
import com.karasu.yome.databinding.FragmentLoginBinding
import com.karasu.yome.service.AccountService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var accountService: AccountService
    private lateinit var mAdapter: UsersAdapter

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var input: EditText

    // Using the activityViewModels() Kotlin property delegate from the
    // fragment-ktx artifact to retrieve the ViewModel in the activity scope
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        mAdapter = UsersAdapter(mutableListOf())
        mAdapter.onUserClick = { user ->
            login(user.apiKey)
        }

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.HORIZONTAL
            )
            adapter = mAdapter
            setHasFixedSize(true)
        }

        // LoginActivity -> LoginViewModel -> this observer
        viewModel.users.observe(viewLifecycleOwner) { users ->
            mAdapter.users = users
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun login(apiKey: String) {
        GlobalScope.launch {
            accountService.login(apiKey)
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
