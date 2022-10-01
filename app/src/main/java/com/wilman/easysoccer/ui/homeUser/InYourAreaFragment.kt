package com.wilman.easysoccer.ui.homeUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wilman.easysoccer.databinding.FragmentInYourAreaBinding

class InYourAreaFragment : Fragment() {

   private var _binding: FragmentInYourAreaBinding?=null
    private val binding get() =_binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inYourAreaViewModel = ViewModelProvider(this)[InYourAreaViewModel::class.java]

        _binding = FragmentInYourAreaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        inYourAreaViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}