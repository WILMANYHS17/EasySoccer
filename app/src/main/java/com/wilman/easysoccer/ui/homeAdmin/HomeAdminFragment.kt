package com.wilman.easysoccer.ui.homeAdmin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.wilman.easysoccer.MapActivity
import com.wilman.easysoccer.RegisterSportCenterActivity
import com.wilman.easysoccer.RegisterUserActivity
import com.wilman.easysoccer.databinding.FragmentHomeAdminBinding
import com.wilman.easysoccer.models.Picture
import com.wilman.easysoccer.ui.header.HeaderViewProfile
import com.wilman.easysoccer.ui.viewpager.ImagesDetailAdapter
import kotlin.math.abs

class HomeAdminFragment : Fragment() {

    private var _binding: FragmentHomeAdminBinding? = null

    private val viewPagerAdapter by lazy {
        ImagesDetailAdapter(
            ::showImageZoom
        )
    }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeAdminViewModel =
            ViewModelProvider(this)[HomeAdminViewModel::class.java]
        _binding = FragmentHomeAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderViewProfile(_binding!!.headerProfile, this).build()
        val imageResource = arrayListOf(
            Picture(url = "screen1"),
            Picture(url = "screen2"),
            Picture(url = "screen3"),
            Picture(url = "screen4"),
            Picture(url = "screen5"),
            Picture(url = "screen6"),
        )
        viewPagerAdapter.setListImage(imageResource)
        binding.txtDescription.text =
            "Es un espacio de 3 canchas para fútbol 5 pero se puedo ajustar para una de fútbol" +
                    " 8 con horario de 6:00 pm a 11:00 pm, incluye espacio de recreación y de comestibles "
        setUpAdapter()
        binding.btnEditSportCenter.setOnClickListener { goEditSportCenter() }
        homeAdminViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    private fun goEditSportCenter() {
        activity?.let {
            val intent = Intent(this.activity, RegisterSportCenterActivity::class.java)
            it.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {
        binding.viewpager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            adapter = viewPagerAdapter
            val compositePageTransformer = CompositePageTransformer()
            val pageTransformer = ViewPager2.PageTransformer { page, position ->
                var r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            with(compositePageTransformer) {
                addTransformer(MarginPageTransformer(40))
                addTransformer(pageTransformer)
            }
            setPageTransformer(compositePageTransformer)
        }
    }

    private fun showImageZoom(position: Int) {}
}