package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ShowcaseElementActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val buttonName = intent.getStringExtra("BUTTON_NAME")
        val stringId = resources.getIdentifier(buttonName, "string", packageName)
        val imageId = resources.getIdentifier(buttonName?.toLowerCase(), "drawable", packageName)

        setContentView(R.layout.activity_showcase)
        val imageView = findViewById<ImageView>(R.id.image_showcase)
        val textView = findViewById<TextView>(R.id.description)
        imageView.setImageResource(imageId)
        textView.setText(stringId)
    }
}
