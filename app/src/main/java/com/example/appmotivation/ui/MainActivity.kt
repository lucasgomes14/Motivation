package com.example.appmotivation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.appmotivation.infra.MotivationConstants
import com.example.appmotivation.R
import com.example.appmotivation.data.Mock
import com.example.appmotivation.infra.SecurityPreferences
import com.example.appmotivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adiciona eventos
        setListeners()

        // Inicializa
        handleFilter(R.id.image_all_inclusive)
        handleNextPhrase()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * Trata eventos de click
     * */
    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(
                R.id.image_all_inclusive,
                R.id.image_emoji,
                R.id.image_sunny
            )
        ) {
            handleFilter(view.id)
        } else if (view.id == R.id.text_hello) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    /**
     * Atribui eventos aos elementos
     * */
    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageEmoji.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textHello.setOnClickListener(this)
    }

    /**
     * Trata o filtro aplicado para as frases
     * */
    private fun handleFilter(id: Int) {
        binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageEmoji.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all_inclusive -> {
                binding.imageAllInclusive.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.ALL
            }

            R.id.image_emoji -> {
                binding.imageEmoji.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.EMOJI
            }

            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    /**
     * Busca o nome do usuário
     * */
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name == "") {
            binding.textHello.text = "Usuário"
        } else {
            binding.textHello.text = "Olá, $name!"
        }
    }

    /**
     * Atualiza frase de motivação
     * */
    private fun handleNextPhrase() {
        binding.textMotivation.text = Mock().getPhrase(categoryId)
    }
}
