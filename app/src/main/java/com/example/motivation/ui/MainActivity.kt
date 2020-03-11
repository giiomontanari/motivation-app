package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var securityPreferences: SecurityPreferences;

    private var phaseFilter: Int = MotivationConstants.PHRASE_FILTER.ALL;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide();

        securityPreferences = SecurityPreferences(this);
        val name = securityPreferences.getString(MotivationConstants.Key.PERSON_NAME);
        textName.text = "Olá, $name!"

        imageAll.setColorFilter(resources.getColor(R.color.colorAccent));
        handleNewPhrase();

        buttonNewPhase.setOnClickListener(this);
        imageAll.setOnClickListener(this);
        imageHappy.setOnClickListener(this);
        imageSun.setOnClickListener(this);
    }

    override fun onClick(view: View) {

        val id = view.id;

        val ListFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageSun);

        if (id == R.id.buttonNewPhase) {
            handleNewPhrase();
        } else if (id in ListFilter) {
            handleFilter(id);
        }
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.white));
        imageHappy.setColorFilter(resources.getColor(R.color.white));
        imageSun.setColorFilter(resources.getColor(R.color.white));

        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.colorAccent));
                phaseFilter = MotivationConstants.PHRASE_FILTER.ALL;
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.colorAccent));
                phaseFilter = MotivationConstants.PHRASE_FILTER.HAPPY;
            }
            R.id.imageSun -> {
                imageSun.setColorFilter(resources.getColor(R.color.colorAccent));
                phaseFilter = MotivationConstants.PHRASE_FILTER.MORNING;
            }
        }
    }

    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(phaseFilter);
    }
}
