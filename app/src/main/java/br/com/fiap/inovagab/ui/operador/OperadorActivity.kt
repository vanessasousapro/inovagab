package br.com.fiap.inovagab.ui.operador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.databinding.ActivityOperadorBinding

class OperadorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOperadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
