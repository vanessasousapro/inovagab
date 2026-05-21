package br.com.fiap.inovagab.ui.operador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.R
import br.com.fiap.inovagab.databinding.ActivityOperadorBinding

class OperadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOperadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fragment inicial
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, OrientacoesFragment())
            .commit()

        // Navegação bottom nav
        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_orientacoes -> OrientacoesFragment()
                R.id.nav_nova_ideia -> NovaIdeiaFragment()
                R.id.nav_minhas_ideias -> MinhasIdeiaFragment()
                else -> OrientacoesFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
            true
        }
    }
}