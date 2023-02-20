package com.example.chefschoice.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.chefschoice.R
import com.example.chefschoice.app.App
import com.example.chefschoice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    private lateinit var  navHostFragment: NavHostFragment

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //supportActionBar?.show()

        with(binding){

            setSupportActionBar(appBarMain.toolbar)
            this@MainActivity.navView = navView
            // Get the navigation host fragment from this Activity
            navHostFragment = supportFragmentManager
                .findFragmentById(appBarMain.contentMain.navHost.id) as NavHostFragment
        }

        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_categories
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        //Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.search_menu, menu)
////        val item = menu?.findItem(R.id.action_search)
////        val searchView = item?.actionView as SearchView
////        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
////            override fun onQueryTextSubmit(query: String?): Boolean {
////                TODO("Not yet implemented")
////            }
////
////            override fun onQueryTextChange(newText: String?): Boolean {
////                TODO("Not yet implemented")
////            }
////
////        })
//
//
//        return true
//    }
}