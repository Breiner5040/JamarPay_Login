package com.example.jamarpay

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    //private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val myButton: Button = setContentView(R.id.myButton)
        myButton.isEnabled = false

    }

}