package com.wilman.easysoccer.ui.stadiums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilman.easysoccer.databinding.FragmentStadiumsBinding
import com.wilman.easysoccer.models.Stadium
import com.wilman.easysoccer.ui.adapter.StadiumsAdapter
import com.wilman.easysoccer.ui.header.HeaderViewProfile

class StadiumsFragment : Fragment() {

    private var _binding: FragmentStadiumsBinding? = null

    private val binding get() = _binding!!

    private val stadiumAdapter by lazy {
        StadiumsAdapter(
            ::goToDetailProduct,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val stadiumsViewModel =
            ViewModelProvider(this)[StadiumsViewModel::class.java]
        _binding = FragmentStadiumsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderViewProfile(_binding!!.headerProfile, this).build()

        setUpAdapter()
        stadiumAdapter.setList(getListStadiums())
        stadiumsViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    private fun getListStadiums(): List<Stadium> {
        return arrayListOf(
            Stadium(
                id = "1",
                image = "screen1",
                numberStadium = "1",
                status = "disponible"
            ),
            Stadium(
                id = "2",
                image = "screen2",
                numberStadium = "2",
                status = "ocupado"
            ),
            Stadium(
                id = "3",
                image = "screen3",
                numberStadium = "3",
                status = "disponible"
            ),
            Stadium(
                id = "4",
                image = "screen4",
                numberStadium = "4",
                status = "ocupado"
            ),
            Stadium(
                id = "5",
                image = "screen5",
                numberStadium = "5",
                status = "ocupado"
            ),

            )
    }

    private fun setUpAdapter() {
        binding.recyclerStadiums.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stadiumAdapter
        }
    }

    private fun goToDetailProduct(stadium: Stadium) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}