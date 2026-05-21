package br.com.fiap.inovagab.ui.gestor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.databinding.ActivityGestorBinding

class GestorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGestorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
