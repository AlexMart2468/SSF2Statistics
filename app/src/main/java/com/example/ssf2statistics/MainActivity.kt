package com.example.ssf2statistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.ssf2statistics.databinding.ActivityMainBinding
import com.example.ssf2statistics.viewmodel.PersonajeViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PersonajeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO ENLAZAR EL MODELO CON EL LIVE DATA

        viewModel = ViewModelProvider(this,).get()
    }
}