package com.tolikavr.telegram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tolikavr.telegram.activities.RegisterActivity
import com.tolikavr.telegram.databinding.ActivityMainBinding
import com.tolikavr.telegram.ui.fragments.ChatsFragment
import com.tolikavr.telegram.ui.objects.AppDrawer
import com.tolikavr.telegram.utilits.*

class MainActivity : AppCompatActivity() {

  private lateinit var mBinding: ActivityMainBinding
  lateinit var mAppDrawer: AppDrawer
  private lateinit var mToolbar: Toolbar

  override fun onStart() {
    super.onStart()
    AppStates.updateState(AppStates.ONLINE)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(mBinding.root)
    APP_ACTIVITY = this
    initFirebase()
    initUser {
      initFields()
      initFunc()
    }
  }

  override fun onStop() {
    super.onStop()
    AppStates.updateState(AppStates.OFFLINE)
  }

  private fun initFields() {
    mToolbar = mBinding.mainToolbar
    mAppDrawer = AppDrawer(this, mToolbar)
  }


  private fun initFunc() {
    if (AUTH.currentUser != null) {
      setSupportActionBar(mToolbar)
      mAppDrawer.create()
      replaceFragment(ChatsFragment(), false)
    } else {
      replaceActivity(RegisterActivity())
    }

  }
}