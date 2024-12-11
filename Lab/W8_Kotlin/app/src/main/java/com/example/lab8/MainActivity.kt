package com.example.lab8

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Inside the onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Initialize Activity
        val btn = findViewById<Buntton>(R.id.button) // Link Button
        btn.setOnClickListener { // Button click event
            showAlertDialog()
        }
    }

    // Show AlertDialog
    private fun showAlertDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Choose an option below")

        dialog.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(this, "Dialog closed", Toast.LENGTH_SHORT).show()
        }

        dialog.setNegativeButton("Custom Toast") { _, _ ->
            showToast()
        }

        dialog.setPositiveButton("Show List") { _, _ ->
            showListDialog()
        }

        dialog.show()
    }

    // Display custom Toast
    private fun showToast() {
        val toast = Toast(this)
        toast.setGravity(Gravity.TOP, 0, 50) // Set position of Toast on screen
        toast.duration = Toast.LENGTH_SHORT // Set Toast display duration

        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_root))
        toast.view = layout // Set custom layout for Toast
        toast.show() // Show Toast
    }

    // Show ListDialog
    private fun showListDialog() {
        val list = arrayOf("Message1", "Message2", "Message3", "Message4", "Message5")
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Message List")
        dialog.setItems(list) { _, which ->
            Toast.makeText(this, "You selected: ${list[which]}", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

}