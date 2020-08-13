package tideware.app.tidenote

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        toolbar.setupWithNavController(findNavController(R.id.nav_host_fragment))
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {

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

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}