package com.wilman.easysoccer.ui.reserveUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wilman.easysoccer.databinding.FragmentReserveUserBinding
import com.wilman.easysoccer.ui.header.HeaderViewProfile

class ReserveUserFragment : Fragment() {

    private var _binding: FragmentReserveUserBinding?=null
    private val binding get() =_binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val reserveUserViewModel = ViewModelProvider(this)[ReserveUserViewModel::class.java]

        _binding = FragmentReserveUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderViewProfile(_binding!!.headerProfile, this).build()
        reserveUserViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}