package com.example.notes

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.navigation.NavListener
import com.example.notes.navigation.NavListenerImpl
import com.example.notes.room.Note
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        setSupportActionBar(toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        navController = findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController)
      }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // TODO
//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.nav_host_fragment).navigateUp()
//    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // TODO
    fun mainToAddNote() {
//        findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_NoteFragment_to_EditNoteFragment)
        navController.navigate(R.id.action_NoteFragment_to_EditNoteFragment)
        Log.d("NAV", "working")
    }

}