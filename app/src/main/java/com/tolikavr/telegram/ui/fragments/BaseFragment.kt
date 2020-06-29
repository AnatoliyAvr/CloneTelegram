package com.tolikavr.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.tolikavr.telegram.MainActivity
import com.tolikavr.telegram.R
import com.tolikavr.telegram.utilits.APP_ACTIVITY


open class BaseFragment(layout: Int) : Fragment(layout) {

  override fun onStart() {
    super.onStart()
    APP_ACTIVITY.mAppDrawer.disableDriver()
  }

  override fun onStop() {
    super.onStop()
    APP_ACTIVITY.mAppDrawer.enableDriver()
  }
}
