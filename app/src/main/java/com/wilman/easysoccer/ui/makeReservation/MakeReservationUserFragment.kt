package com.wilman.easysoccer.ui.makeReservation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wilman.easysoccer.R
import com.wilman.easysoccer.databinding.FragmentInYourAreaBinding
import com.wilman.easysoccer.databinding.FragmentMakeReservationUserBinding
import com.wilman.easysoccer.ui.homeUser.InYourAreaViewModel

class MakeReservationUserFragment : Fragment() {

    private var _binding: FragmentMakeReservationUserBinding?=null
    private val binding get() =_binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val makeReservationUserViewModel = ViewModelProvider(this)[MakeReservationUserViewModel::class.java]

        _binding = FragmentMakeReservationUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        makeReservationUserViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}