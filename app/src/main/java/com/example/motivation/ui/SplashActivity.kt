package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var securityPreferences: SecurityPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        securityPreferences = SecurityPreferences(this)

        supportActionBar?.hide();

        buttonSave.setOnClickListener(this);

        verifyName();
    }

    override fun onClick(view: View) {
        val id = view.id;
        if (id == R.id.buttonSave) {
            handleSave();
        }
    }

    private fun verifyName() {
        val name = securityPreferences.getString(MotivationConstants.Key.PERSON_NAME);
        if (name != "") {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
        }
    }

    private fun handleSave() {
        val name = editName.text.toString();
        if (name != "") {
            securityPreferences.storeString(MotivationConstants.Key.PERSON_NAME, name);
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}
