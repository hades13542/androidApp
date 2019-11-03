package com.example.myapplication

import android.os.Bundle

class ShowcaseElementActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageId = intent.getStringExtra("IMAGE_ID")
        val stringId = intent.getStringExtra("STRING_ID")
        setContentView(R.layout.activity_showcase)
    }
}
