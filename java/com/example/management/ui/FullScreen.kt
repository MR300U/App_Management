package com.example.management.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.management.databinding.ActivityFullScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FullScreen : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            delay(6000)
            startActivity(Intent(this@FullScreen, MainActivity::class.java))
            finish()
        }


    }
}