package com.example.intentsampleapp

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentsampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        binding.apply {
            btnLaunchActivity.setOnClickListener {
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnLaunchYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_MAIN).also {
                    it.`package` = "com.google.android.youtube"
                    try {
                        startActivity(it)
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
            txtSendEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(txtSendEmail.text))
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
    }
}