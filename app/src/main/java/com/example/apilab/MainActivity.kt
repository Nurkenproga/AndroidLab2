package com.example.apilab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragment = com.example.apilab.fragmentPack.Fragment()



        setCurrentFragment(fragment)


    }

    private fun setCurrentFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentt, fragment)
            commit()

        }
    }
}