package com.example.smartoffice.presentation.screens

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartoffice.databinding.FragmentAddBinding
import com.example.smartoffice.presentation.AddViewModel
import com.example.smartoffice.presentation.MainViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding is null")

    private lateinit var viewModel: AddViewModel

    private var unknownId: String = "null"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        if (!args.containsKey(UNKNOWNID_KEY)) {
            throw RuntimeException("Param unknown id is absent!")
        }
        unknownId = args.getString(UNKNOWNID_KEY, "null")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        binding.editTextId.setText(unknownId)
        binding.editTextId.isFocusable = false
        binding.editTextId.isClickable = false
        binding.buttonAddEmployee.setOnClickListener {
            with(binding) {
                val name = editTextName.text?.toString()
                val surname = editTextSurname.text?.toString()
                val age = editTextAge.text?.toString()
                val phone = editTextPhone.text?.toString()
                val id = unknownId

                viewModel.addNewEmployee(name, surname, age, phone, id, showDoneToast = {
                    Toast.makeText(requireContext(), "Добавлено!", Toast.LENGTH_SHORT).show()
                }, showErrorToast =  {
                    Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
                })
            }
        }

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            requireActivity().onBackPressed()
        }

    }

    companion object {

        private const val UNKNOWNID_KEY = "unknown id"

        fun newInstance(unknownId: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(UNKNOWNID_KEY, unknownId)
                }
            }
    }
}