package com.emir.dreamjournal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        val layout = findViewById<ConstraintLayout>(R.id.main_layout)
        val cardLinearLayout = LinearLayout(this)

        actionBar?.elevation = 10F

        val db = DatabaseHelper(this)

        val allData = db.allData

        if (allData.moveToFirst()) {
            for (i in 0 until allData.count) {
                allData.move(i)

                val title = allData.getString(1)
                val desc = allData.getString(2)
                val date = allData.getString(3)
                
                val cardView = CardView(this)

                val layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )

                layoutParams.setMargins(30,30,30,30)

                cardView.layoutParams = layoutParams

                cardView.setCardBackgroundColor(Color.GRAY)

                val titleView = TextView(this)
                titleView.text = title
                titleView.textSize = 40f
                titleView.setTextColor(Color.WHITE)

                val descView = TextView(this)
                descView.text = desc
                descView.textSize = 35f
                descView.setTextColor(Color.WHITE)

                val dateView = TextView(this)
                dateView.text = date
                dateView.textSize = 20f
                dateView.setTextColor(Color.WHITE)

                cardLinearLayout.addView(titleView)
                cardLinearLayout.addView(descView)
                cardLinearLayout.addView(dateView)

                cardView.addView(cardLinearLayout)

                layout.addView(cardView)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
            val intentAdd = Intent(this, AddActivity::class.java)
            startActivity(intentAdd)

            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}