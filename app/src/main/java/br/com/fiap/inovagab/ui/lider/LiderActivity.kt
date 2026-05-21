package br.com.fiap.inovagab.ui.lider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.databinding.ActivityLiderBinding

class LiderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
