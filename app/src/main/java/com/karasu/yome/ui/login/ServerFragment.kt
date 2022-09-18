package com.karasu.yome.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.karasu.yome.databinding.FragmentConnectionServerBinding
import com.karasu.yome.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class ServerFragment : Fragment() {

    private var _binding: FragmentConnectionServerBinding? = null
    private val binding get() = _binding!!

    // Using the activityViewModels() Kotlin property delegate from the
    // fragment-ktx artifact to retrieve the ViewModel in the activity scope
    private val viewModel: ServerViewModel by activityViewModels()

    private val toastMessageObserver: MutableLiveData<String?> = MutableLiveData<String?>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConnectionServerBinding.inflate(inflater, container, false)
        binding.btnServer.setOnClickListener { _ ->
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

    fun validateInput() {
        val address = binding.etServer.text.toString()

        if (address.isNotEmpty()) {

            Utils.closeKeyboard(requireActivity(), requireView())

            GlobalScope.launch {
                try {
                    val exists = Utils.testUrl(address)
                    println("Server exists: $exists")
                    if (exists) viewModel.setServer(address) else toastMessageObserver.postValue("Server not found")
                } catch (code: IOException) {
                    println("IOException $code")
                    toastMessageObserver.postValue("ERROR: ${code.message}")
                }
            }
        } else {
            binding.etServer.error = "Server address must be provided"
        }
    }


}
