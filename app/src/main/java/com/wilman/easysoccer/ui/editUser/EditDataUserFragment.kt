package com.wilman.easysoccer.ui.editUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wilman.easysoccer.databinding.FragmentEditDataUserBinding

class EditDataUserFragment : Fragment() {

    private var _binding: FragmentEditDataUserBinding?=null
    private val binding get() =_binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val editDataUserViewModel = ViewModelProvider(this)[EditDataUserViewModel::class.java]

        _binding = FragmentEditDataUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        editDataUserViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}