package com.karasu.yome.acitivtiy

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.karasu.yome.R
import com.karasu.yome.databinding.ActivityMainBinding
import com.karasu.yome.service.LibraryService
import com.karasu.yome.service.SeriesService
import com.karasu.yome.service.UserService
import com.karasu.yome.ui.main.home.HomeFragment
import com.karasu.yome.ui.main.library.LibraryFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var libraryService: LibraryService
    @Inject
    lateinit var seriesService: SeriesService

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.appBarMain.toolbar
        drawerLayout = binding.drawerLayout
        navView = binding.navView

        navView.setNavigationItemSelectedListener(this)

        setSupportActionBar(toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Switch Dark / Light mode", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            // should be in a ViewModel according to https://stackoverflow.com/a/61128446
            val isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
            val nextMode = if (isNightMode) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.setDefaultNightMode(nextMode)
        }

        val m = navView.menu
        val menuGroup = m.addSubMenu("My libraries")

        GlobalScope.launch {
            val librariesForMember = libraryService.getLibrariesForMember()

            val librariesIds: MutableList<Int> = mutableListOf(R.id.nav_home)
            for (library in librariesForMember) {
                val item = menuGroup.add(0, library.id, 0, library.name)
                librariesIds.add(item.itemId)
            }

            navController = findNavController(R.id.nav_host_fragment_content_main)
            // val fragments = setOf(librariesIds)
            appBarConfiguration = AppBarConfiguration(librariesIds.toSet(), drawerLayout)
            setupActionBarWithNavController(navController, appBarConfiguration)
            // navView.setupWithNavController(navController) // not compatible with onNavigationItemSelected

        }
        setMenuHeader(navView.getHeaderView(0))
    }

    private fun setMenuHeader(header: View) {
        // val avatar: ImageView = header.findViewById(R.id.avatar)
        // val subtitle: TextView = header.findViewById(R.id.subtitle)

        val username: TextView = header.findViewById(R.id.username)
        username.text = UserService.getCurrentUser()?.username
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity", "onNavigationItemSelected: ${item.title} - ${item.itemId}")

        item.isChecked = true

        var fragment: Fragment? = null
        var title = ""

        // Menu items
        when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "HOME clicked", Toast.LENGTH_SHORT).show()
                fragment = HomeFragment()
                title = getString(R.string.menu_home)
            }
        }

        // Libraries
        if (fragment == null) {
            val library = libraryService.getLibraries().find { library -> library.id == item.itemId }

            if (library != null) {
                fragment = LibraryFragment(seriesService, library)
                title = library.name
            }
        }

        // Navigate to fragment
        if (fragment != null) {
            toolbar.title = title
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment_content_main, fragment)
            transaction.commit()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}