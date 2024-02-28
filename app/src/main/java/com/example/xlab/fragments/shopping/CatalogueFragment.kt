package com.example.xlab.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kelineyt.adapters.HomeViewpagerAdapter
import com.example.xlab.databinding.FragmentHomeBinding
import com.example.xlab.R
import com.example.xlab.fragments.categories.HarmfulCategory
import com.example.xlab.fragments.categories.MainCategoryFragment
import com.example.xlab.fragments.categories.UsefulCategoryFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val categoriesFragments = arrayListOf(
        { MainCategoryFragment() },
        { UsefulCategoryFragment() },
        { HarmfulCategory() },
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager2Adapter = HomeViewpagerAdapter(categoriesFragments, childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.viewpagerHome.offscreenPageLimit = 100
        binding.viewpagerHome.adapter = viewPager2Adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Статьи"
                1 -> tab.text = "Полезные виды"
                2 -> tab.text = "Вредители"
            }
        }.attach()


    }
}