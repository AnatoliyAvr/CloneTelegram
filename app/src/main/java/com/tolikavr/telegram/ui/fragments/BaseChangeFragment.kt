package com.tolikavr.telegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.tolikavr.telegram.R
import com.tolikavr.telegram.utilits.APP_ACTIVITY
import com.tolikavr.telegram.utilits.hideKeyboard

open class BaseChangeFragment(layout: Int) : Fragment(layout) {

  override fun onStart() {
    super.onStart()
    setHasOptionsMenu(true)
    APP_ACTIVITY.mAppDrawer.disableDriver()
    hideKeyboard()
  }

  override fun onStop() {
    super.onStop()
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    APP_ACTIVITY.menuInflater.inflate(R.menu.settings_menu_confirm, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.settings_confirm_change -> change()
    }
    return true
  }

  open fun change() {}
}