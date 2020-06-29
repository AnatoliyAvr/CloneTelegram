package com.tolikavr.telegram.ui.objects

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.tolikavr.telegram.R
import com.tolikavr.telegram.ui.fragments.CreateGroupFragment
import com.tolikavr.telegram.ui.fragments.SettingsFragment
import com.tolikavr.telegram.utilits.USER
import com.tolikavr.telegram.utilits.downloadAndSetImage
import com.tolikavr.telegram.utilits.replaceFragment

class AppDrawer(private val mainActivity: AppCompatActivity, private val toolbar: Toolbar) {
  private lateinit var mDrawer: Drawer
  private lateinit var mHeader: AccountHeader
  private lateinit var mDriverLayout: DrawerLayout
  private lateinit var mCurrentProfile: ProfileDrawerItem

  fun create() {
    initLoader()
    createHeader()
    createDrawer()
    mDriverLayout = mDrawer.drawerLayout
  }

  fun disableDriver() {
    mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
    mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    mDriverLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    toolbar.setNavigationOnClickListener {
      mainActivity.supportFragmentManager.popBackStack()
    }
  }

  fun enableDriver() {
    mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
    mDriverLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    toolbar.setNavigationOnClickListener {
      mDrawer.openDrawer()
    }
  }

  private fun createHeader() {
    mCurrentProfile = ProfileDrawerItem()
      .withName(USER.fullname)
      .withEmail(USER.phone)
      .withIcon(USER.photoUrl)
      .withIdentifier(200)

    mHeader = AccountHeaderBuilder()
      .withActivity(mainActivity)
      .withHeaderBackground(R.drawable.header)
      .addProfiles(
        mCurrentProfile
      ).build()
  }

  fun updateHeader() {
    mCurrentProfile
      .withName(USER.fullname)
      .withEmail(USER.phone)
      .withIcon(USER.photoUrl)

    mHeader.updateProfile(mCurrentProfile)
  }

  private fun initLoader() {
    DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
      override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
        super.set(imageView, uri, placeholder)
        imageView.downloadAndSetImage(uri.toString())
      }
    })
  }

  private fun createDrawer() {
    mDrawer = DrawerBuilder()
      .withActivity(mainActivity)
      .withToolbar(toolbar)
      .withActionBarDrawerToggle(true)
      .withSelectedItem(-1)
      .withAccountHeader(mHeader)
      .addDrawerItems(
        PrimaryDrawerItem().withIdentifier(100)
          .withIconTintingEnabled(true)
          .withName("Создать группу")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_create_groups),
        PrimaryDrawerItem().withIdentifier(101)
          .withIconTintingEnabled(true)
          .withName("Создать секретный чат")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_secret_chat),
        PrimaryDrawerItem().withIdentifier(102)
          .withIconTintingEnabled(true)
          .withName("Создать канал")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_create_channel),
        PrimaryDrawerItem().withIdentifier(103)
          .withIconTintingEnabled(true)
          .withName("Контакты")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_contacts),
        PrimaryDrawerItem().withIdentifier(104)
          .withIconTintingEnabled(true)
          .withName("Звонки")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_phone),
        PrimaryDrawerItem().withIdentifier(105)
          .withIconTintingEnabled(true)
          .withName("Избранное")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_favorites),
        PrimaryDrawerItem().withIdentifier(106)
          .withIconTintingEnabled(true)
          .withName("Настройки")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_settings),
        DividerDrawerItem(),
        PrimaryDrawerItem().withIdentifier(107)
          .withIconTintingEnabled(true)
          .withName("Пригласить друзей")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_invate),
        PrimaryDrawerItem().withIdentifier(108)
          .withIconTintingEnabled(true)
          .withName("Вопросы о Telegram")
          .withSelectable(false)
          .withIcon(R.drawable.ic_menu_help)
      )
      .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
        override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean {
          Toast.makeText(mainActivity, "$position", Toast.LENGTH_SHORT).show()
          Log.d("AAA", "${drawerItem.identifier}")
          when (drawerItem.identifier) {
            100L -> mainActivity.replaceFragment(CreateGroupFragment())
            101L -> mainActivity.replaceFragment(SettingsFragment())
            102L -> mainActivity.replaceFragment(SettingsFragment())
            103L -> mainActivity.replaceFragment(SettingsFragment())
            104L -> mainActivity.replaceFragment(SettingsFragment())
            105L -> mainActivity.replaceFragment(SettingsFragment())
            106L -> mainActivity.replaceFragment(SettingsFragment())
            107L -> mainActivity.replaceFragment(SettingsFragment())
            108L -> mainActivity.replaceFragment(SettingsFragment())
          }
          return false
        }
      })
      .build()
  }
}