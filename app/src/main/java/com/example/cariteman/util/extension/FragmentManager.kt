package com.example.cariteman.util.extension

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cariteman.R

internal fun FragmentManager.removeFragment(
    tag: String,
    slideIn: Int = R.anim.slide_left,
    slideOut: Int = R.anim.slide_right
) {
    this.findFragmentByTag(tag)?.let {
        this.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(slideIn, slideOut)
            .remove(it)
            .commitNow()
    }
}

internal fun FragmentManager.addFragmentWithBackStack(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.slide_left,
    slideOut: Int = R.anim.slide_right
) {
    this.beginTransaction()
        .setCustomAnimations(slideIn, slideOut, slideIn, slideOut)
        .replace(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

internal fun FragmentManager.addFragmentWithBackStackWithSetTargetFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.slide_left,
    slideOut: Int = R.anim.slide_right
) {
    this.beginTransaction()
        .setCustomAnimations(slideIn, slideOut, slideIn, slideOut)
        .replace(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

internal fun FragmentManager.addFragmentWithoutBackStack(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = R.anim.slide_left,
    slideOut: Int = R.anim.slide_right
) {
    this.beginTransaction().disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut, slideIn, slideOut)
        .replace(containerViewId, fragment, tag)
        .commit()
}

fun FragmentManager.detach() {
    findFragmentById(R.id.container)?.also {
        beginTransaction().detach(it).commit()
    }
}

fun FragmentManager.attach(fragment: Fragment, tag: String) {
    if (fragment.isDetached) {
        beginTransaction().attach(fragment).commit()
    } else {
        beginTransaction().add(R.id.container, fragment, tag).commit()
    }
    // Set addFragmentWithBackStack transition animation for this transaction.
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
}