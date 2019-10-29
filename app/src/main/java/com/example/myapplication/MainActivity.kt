package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import androidx.cardview.widget.CardView
import androidx.core.content.res.use
import com.google.android.material.floatingactionbutton.FloatingActionButton

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_qr_code.view.*
import java.io.File

class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        sideButton.setOnClickListener { view ->
            openQRAcivity(view)
        }

        val prefs = getSharedPreferences("thisApp", Context.MODE_PRIVATE)
        if (prefs.getBoolean("FirstRun", true)) {
            val editor = prefs.edit()
            resources.getStringArray(R.array.FileNames).forEach { editor.putBoolean(it, false) }
            editor.putBoolean("FirstRun", false)
            editor.apply()
        }
        applyVisibility(prefs)
    }

    private fun applyVisibility(prefs: SharedPreferences) {
        resources.getStringArray(R.array.FileNames).forEach {
            var visibility = View.GONE
            if (prefs.getBoolean(it, false)) {

                visibility = View.VISIBLE
            }
            findViewById<CardView>(resources.getIdentifier(it, "id", packageName)).visibility =
                visibility
        }
    }

    fun printPrefs() {
        Log.d(
            "PREFS printPrefs " + this::class,
            getPreferences(Context.MODE_PRIVATE).all.entries.toString()
        )
    }

    override fun onResume() {
        super.onResume()
        printPrefs()
        applyVisibility(getSharedPreferences("thisApp", Context.MODE_PRIVATE))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun handleItem(view: View) {

        Log.d("MENU ITEM", view.id.toString())
        val id = findViewById<CardView>(view.id)
        Log.d("MENU ITEM", id.textView.text.toString())
        TODO("finished here")
        id.visibility = View.GONE
    }

    fun openQRAcivity(view: View) {
//        val editText = findViewById<FloatingActionButton>(R.id.sideButton)
//        val message = editText.text.toString()
        val intent =
            Intent(this, QrCodeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
