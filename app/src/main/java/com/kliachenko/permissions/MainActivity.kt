package com.kliachenko.permissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kliachenko.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.feature1Button.setOnClickListener {

        }

        binding.feature2Button.setOnClickListener {

        }
    }
}