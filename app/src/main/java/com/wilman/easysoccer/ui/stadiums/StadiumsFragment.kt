package com.wilman.easysoccer.ui.stadiums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wilman.easysoccer.databinding.FragmentStadiumsBinding

class StadiumsFragment : Fragment() {

    private var _binding: FragmentStadiumsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val stadiumsViewModel =
            ViewModelProvider(this)[StadiumsViewModel::class.java]


        _binding = FragmentStadiumsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        stadiumsViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}