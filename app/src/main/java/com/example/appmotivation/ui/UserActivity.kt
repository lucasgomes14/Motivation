package com.example.appmotivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.appmotivation.infra.MotivationConstants
import com.example.appmotivation.R
import com.example.appmotivation.infra.SecurityPreferences
import com.example.appmotivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acesso aos elementos de interface)
        binding.buttonSave.setOnClickListener(this)
    }

    /**
     * Tratamento de clicks dos elementos
     * */
    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    /**
     * Salva o nome do usuário para utilizações futuras
     * */
    private fun handleSave() {
        val name = binding.editName.text.toString()

        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}