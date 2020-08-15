package tideware.app.tidenote

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {
    var check = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(toolbar);
        toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            when(destination.id){
                R.id.MainFragment -> {
                        if (toolbar.navigationIcon == ContextCompat.getDrawable(this,R.drawable.ic_star) )
                        toolbar.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_checked_star)
                        else
                            toolbar.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_star)



                }
            }
            toolbar.setNavigationOnClickListener {
                onBackPressed()
                //if(it.id== R.id.main_activity)
                //checkFavoriteIconChange(toolbar)



                navController.navigateUp() || super.onSupportNavigateUp()
            }
        }


       // supportActionBar?.setHomeButtonEnabled(true)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //supportActionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this,R.drawable.ic_star))

        /*toolbar.setOnMenuItemClickListener {

            return@setOnMenuItemClickListener when(it.itemId){
                R.id.action_settings ->{
                    Toast.makeText(this,"You Clicked on settings",Toast.LENGTH_LONG).show()
                    true
                }
                R.id.action_delete ->{
                    Toast.makeText(this,"You Clicked on delete all",Toast.LENGTH_LONG).show()
                    true
                }
                else ->  it.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) || super.onOptionsItemSelected(it)
            }

        }*/

    }

    private fun checkFavoriteIconChange(toolbar: Toolbar) {
        if (!check) {
            check = true
            toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_checked_star)
        } else if (check) {
            check = false
            toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_star)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}