package com.milon.milroodmigration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.milon.milroodmigration.databinding.ActivityMainBinding
import com.milon.roomdb.Contact
import com.milon.roomdb.ContactDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"milon","abcd",1))
        }

        binding.getDataBtn.setOnClickListener {

            database.contactDao().getContact().observe(this, Observer {
                Log.d("milon",it.toString())
            })
        }

    }
}