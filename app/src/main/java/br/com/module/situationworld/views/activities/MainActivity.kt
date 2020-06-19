package br.com.module.situationworld.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import br.com.module.situationworld.R
import br.com.module.situationworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
    }
}
