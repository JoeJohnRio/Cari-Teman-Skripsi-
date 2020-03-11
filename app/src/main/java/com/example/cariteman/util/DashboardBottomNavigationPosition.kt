package com.example.cariteman.util

import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.ui.dashboard.view.FavoriteFragment
import com.example.cariteman.ui.dashboard.view.HomeFragment
import com.example.cariteman.ui.dashboard.view.ProfileFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    HOME(0,  R.id.nav_item_beranda ),
    FAVORITE(1, R.id.nav_item_favorite),
    PROFILE(2, R.id.nav_item_profil)
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
    BottomNavigationPosition.FAVORITE.id -> BottomNavigationPosition.FAVORITE
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.HOME
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.newInstance()
    BottomNavigationPosition.FAVORITE -> FavoriteFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.HOME -> HomeFragment.TAG
    BottomNavigationPosition.FAVORITE -> FavoriteFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}