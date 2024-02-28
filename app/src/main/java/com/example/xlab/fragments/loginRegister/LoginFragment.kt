package com.example.xlab.fragments.lognRegister

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.Snackbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kelineyt.dialog.setupBottomSheetDialog
import com.example.xlab.R
import com.example.xlab.activities.GuideActivity
import com.example.xlab.databinding.FragmentAccountOptionsBinding
import com.example.xlab.databinding.FragmentLoginBinding
import com.example.xlab.util.Resource
import com.example.xlab.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login)  {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.apply {
            buttonLoginLogin.setOnClickListener{
                val email = edEmailLogin.text.toString().trim()
                val password = edPasswordLogin.text.toString()
                viewModel.login(email, password)
            }
        }

        binding.tvForgotPasswordLogin.setOnClickListener {
        setupBottomSheetDialog {
            email -> viewModel.resetPassword(email)
            }
        }
        lifecycleScope.launch {
            // Ensuring that the code block inside runs when the lifecycle is at least STARTED
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resetPassword.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            // Handle loading state
                        }

                        is Resource.Success -> {
                            com.google.android.material.snackbar.Snackbar.make(
                                requireView(),
                                "Ссылка для восстановления была отправлена на вашу почту",
                                com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                            ).show()
                        }

                        is Resource.Error -> {
                            com.google.android.material.snackbar.Snackbar.make(
                                requireView(),
                                "Ошибка: ${resource.message}",
                                com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                            ).show()
                        }

                        else -> Unit
                    }
                }
            }
        }

        lifecycleScope.launch {
            // Ensuring that the code block inside runs when the lifecycle is at least STARTED
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.login.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.buttonLoginLogin.startAnimation()
                        }
                        is Resource.Success -> {
                            binding.buttonLoginLogin.revertAnimation()
                            Intent(requireActivity(),GuideActivity::class.java).also { intent ->
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
                            binding.buttonLoginLogin.revertAnimation()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}
