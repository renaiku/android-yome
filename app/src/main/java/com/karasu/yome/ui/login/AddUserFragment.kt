package com.karasu.yome.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.karasu.yome.databinding.FragmentConnectionAddUserBinding
import com.karasu.yome.service.AccountService
import com.karasu.yome.service.UserService
import com.karasu.yome.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@AndroidEntryPoint
class AddUserFragment : Fragment() {

    @Inject
    lateinit var accountService: AccountService

    private var _binding: FragmentConnectionAddUserBinding? = null
    private val binding get() = _binding!!

    // Using the activityViewModels() Kotlin property delegate from the
    // fragment-ktx artifact to retrieve the ViewModel in the activity scope
    private val viewModel: AddUserViewModel by activityViewModels()

    private val toastMessageObserver: MutableLiveData<String?> = MutableLiveData<String?>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConnectionAddUserBinding.inflate(inflater, container, false)
        binding.btnApiKey.setOnClickListener { _ ->
            validateInput()
        }

        toastMessageObserver.observe(viewLifecycleOwner) { message ->
            Utils.showToast(activity, message)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateInput() {
        val apiKey = binding.etApiKey.text.toString()

        if (apiKey.isNotEmpty()) {

            Utils.closeKeyboard(requireActivity(), requireView())

            GlobalScope.launch {
                try {
                    accountService.login(apiKey)
                    val user = UserService.getCurrentUser()!!
                    viewModel.setUser(user)
                } catch (code: IOException) {
                    println("IOException $code")
                    toastMessageObserver.postValue("ERROR: ${code.message}")
                }
            }
        } else {
            binding.etApiKey.error = "API Key must be provided"
        }
    }

}
