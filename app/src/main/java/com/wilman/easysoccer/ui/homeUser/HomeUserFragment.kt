package com.wilman.easysoccer.ui.homeUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wilman.easysoccer.databinding.FragmentHomeUserBinding
import com.wilman.easysoccer.ui.header.HeaderViewProfile

class HomeUserFragment : Fragment() {

   private var _binding: FragmentHomeUserBinding?=null
    private val binding get() =_binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeUserViewModel = ViewModelProvider(this)[HomeUserViewModel::class.java]

        _binding = FragmentHomeUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderViewProfile(_binding!!.headerProfile, this).build()
        homeUserViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}