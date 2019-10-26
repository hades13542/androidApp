package com.example.myapplication

import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BasicActivity : AppCompatActivity() {

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("Back button", item.itemId.toString())
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}