package com.emir.dreamjournal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val actionBar = supportActionBar

        actionBar?.title = "Add New Dream"
        actionBar?.elevation = 10F
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val saveButton: Button = findViewById(R.id.saveButton)
        val editTitle: EditText = findViewById(R.id.editTitle)
        val editDesc: EditText = findViewById(R.id.editDesc)

        saveButton.setOnClickListener { view ->
            val title = editTitle.text.toString()
            val description = editDesc.text.toString()

            val time = Calendar.getInstance().time
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

            val now = sdf.format(time)

            if (title.isEmpty()) {
                val snackbar = Snackbar.make(view, "You can't leave the title empty!",
                    Snackbar.LENGTH_LONG)
                snackbar.show()
            } else if (description.isEmpty()) {
                val snackbar = Snackbar.make(view, "You can't leave the description empty!",
                    Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val db = DatabaseHelper(this)

                val random = Random()

                db.insertData(random.nextInt(99999999), title, description, now)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp() : Boolean {
        onBackPressed()
        return true
    }
}