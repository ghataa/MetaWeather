package com.ghataa.metaweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/** The main and the only [Activity] of the application which holds the Fragment(s). */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
