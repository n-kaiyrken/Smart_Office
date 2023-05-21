package com.example.smartoffice.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.smartoffice.R
import com.example.smartoffice.databinding.FragmentUnknownsBinding
import com.example.smartoffice.presentation.MainViewModel
import com.example.smartoffice.presentation.adapters.UnknownIdsAdapter

class UnknownsFragment : Fragment() {

    private var _binding: FragmentUnknownsBinding? = null
    private val binding: FragmentUnknownsBinding
        get() = _binding ?: throw RuntimeException("FragmentUnknownsBinding is null")

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnknownsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val adapter = UnknownIdsAdapter()
        binding.rvUnknownIdsList.adapter = adapter
        viewModel.unknownIdsLivaData.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                if (!it.get(0).equals(0L)) {
                    binding.textViewNoRecords.visibility = View.INVISIBLE
                }
            }
            adapter.submitList(it)
        }
        adapter.onAddButtonClickListener = object : UnknownIdsAdapter.OnAddButtonClickListener {
            override fun onAddButtonClick(unknownId: String) {
                launchAddFragment(unknownId)
            }
        }
        adapter.convertToTime = { viewModel.convertToTime(it) }
    }

    private fun launchAddFragment(unknownId: String) {
        Log.d("Fragment Manager", "AddFragment addedToBackStack")
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, AddFragment.newInstance(unknownId))
            .commit()
    }

    companion object {
        fun newInstance() = UnknownsFragment()
    }
}