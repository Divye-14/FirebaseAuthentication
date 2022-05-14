package com.example.pocandroid.views
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.pocandroid.R
import com.example.pocandroid.databinding.ActivityDrawerBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import java.lang.RuntimeException

class Drawer : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var actionbarDrawerToggle: ActionBarDrawerToggle
    private lateinit var binding:ActivityDrawerBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_drawer)
        setSupportActionBar(binding.appBar)

        actionbarDrawerToggle = ActionBarDrawerToggle(this,
            binding.mainDrawer,binding.appBar,0,
            0)
        binding.mainDrawer.addDrawerListener(actionbarDrawerToggle)
//        actionBarDrawerToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)

        //button crashlytics
        binding.crashlytics.setOnClickListener {
            throw RuntimeException("Testing for crash")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.nav_home -> {
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_LONG).show()
                binding.mainDrawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_LONG).show()
                binding.mainDrawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Logout CLicked", Toast.LENGTH_LONG).show()
                binding.mainDrawer.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }

    override fun onBackPressed() {
        count++
        if(count==2){
            finishAffinity()
            super.onBackPressed()
        }
        else{
            Toast.makeText(this,"Press again to exit",Toast.LENGTH_LONG).show()
        }
    }
}