package br.com.fiap.inovagab.ui.lider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.R
import br.com.fiap.inovagab.databinding.ActivityLiderBinding

class LiderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerLider, OrientacoesLiderFragment())
            .commit()

        binding.bottomNavLider.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_orientacoes_lider -> OrientacoesLiderFragment()
                R.id.nav_projetos_lider -> ProjetosLiderFragment()
                R.id.nav_dashboard -> DashboardFragment()
                else -> OrientacoesLiderFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerLider, fragment)
                .commit()
            true
        }
    }
}