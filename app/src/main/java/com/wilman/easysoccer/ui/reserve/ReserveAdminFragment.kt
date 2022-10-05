package com.wilman.easysoccer.ui.reserve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wilman.easysoccer.databinding.FragmentReserveAdminBinding
import com.wilman.easysoccer.models.Reserve
import com.wilman.easysoccer.models.Stadium
import com.wilman.easysoccer.ui.adapter.ReservesAdminAdapter
import com.wilman.easysoccer.ui.header.HeaderViewProfile

class ReserveAdminFragment : Fragment() {

    private var _binding: FragmentReserveAdminBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val reserveAdminAdapter by lazy {
        ReservesAdminAdapter(
            ::goToDetailProduct,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val reserveAdminViewModel =
            ViewModelProvider(this)[ReserveAdminViewModel::class.java]

        _binding = FragmentReserveAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderViewProfile(_binding!!.headerProfile, this).build()

        setUpAdapter()
        reserveAdminAdapter.setList(getListStadiums())
        reserveAdminViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    private fun getListStadiums(): List<Reserve> {
        return arrayListOf(
            Reserve(
                idReserve = "123456",
                nameStadium = "Cancha 1",
                nameReserve = "Luis Garcia",
                dateReserve = "30/10/2022 a las 6:00 pm",
                statusPayment = "pagado",
                valueReserve = "70000"
            ),
            Reserve(
                idReserve = "5647328",
                nameStadium = "Cancha 2",
                nameReserve = "Javier Cruz",
                dateReserve = "29/10/2022 a las 8:00 pm",
                statusPayment = "Esperando pago",
                valueReserve = "70000"
            ),


            )
    }

    private fun setUpAdapter() {
        binding.recyclerReserves.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reserveAdminAdapter
        }
    }

    private fun goToDetailProduct(reserve: Reserve) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}