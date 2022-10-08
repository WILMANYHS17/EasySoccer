package com.wilman.easysoccer.ui.homeUser

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilman.easysoccer.MakeUserReservationActivity
import com.wilman.easysoccer.MapActivity
import com.wilman.easysoccer.databinding.FragmentHomeUserBinding
import com.wilman.easysoccer.models.InYourArea
import com.wilman.easysoccer.models.Stadium
import com.wilman.easysoccer.ui.adapter.StadiumsAdapter
import com.wilman.easysoccer.ui.adapter.StadiumsUserAdapter
import com.wilman.easysoccer.ui.header.HeaderViewProfile

class HomeUserFragment : Fragment() {

   private var _binding: FragmentHomeUserBinding?=null
    private val binding get() =_binding!!

    private val stadiumAdapterNearYou by lazy {
        StadiumsUserAdapter(
            ::goToDetailProduct,
            ::goToReserveStadium
        )
    }

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
        setUpAdapter()
        stadiumAdapterNearYou.setListInYouArea(getListStadiumsNearYou())
        homeUserViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }
    private fun getListStadiumsNearYou(): List<InYourArea> {
        return arrayListOf(
            InYourArea(
                id = "1",
                image = "screen1",
                directionStadium = "Cl. 15 # 5 - 90, Tunja, Boyacá",
                valueStadium = "90000",
                nameStadium = "Invictus fútbol 5"
            ),
            InYourArea(
                id = "2",
                image = "screen2",
                directionStadium = "Cl. 66 # 6-46, Tunja, Boyacá",
                valueStadium = "100000",
                nameStadium = "CANCHAS SINTETICAS TERRA SOCCER"
            ),

            )
    }

    private fun setUpAdapter() {
        binding.recyclerStadiumsNearYou.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = stadiumAdapterNearYou
        }
    }

    private fun goToDetailProduct(inYouArea: InYourArea) {
        activity?.let {
            val intent = Intent(this.activity, MapActivity::class.java)
            it.startActivity(intent)
        }

    }

    private fun goToReserveStadium(inYouArea: InYourArea) {
        activity?.let {
            val intent = Intent(this.activity, MakeUserReservationActivity::class.java)
            it.startActivity(intent)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}