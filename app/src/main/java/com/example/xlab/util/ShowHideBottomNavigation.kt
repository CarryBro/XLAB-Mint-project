package com.example.xlab.util

import android.view.View
import androidx.fragment.app.Fragment
import com.example.xlab.R
import com.example.xlab.activities.GuideActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigationView(){
    val bottomNavigationView =
        (activity as GuideActivity).findViewById<BottomNavigationView>(
            com.example.xlab.R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.GONE
}

fun Fragment.showBottomNavigationView(){
    val bottomNavigationView =
        (activity as GuideActivity).findViewById<BottomNavigationView>(
            com.example.xlab.R.id.bottomNavigation
        )
    bottomNavigationView.visibility = android.view.View.VISIBLE
}