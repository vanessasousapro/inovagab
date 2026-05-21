package br.com.fiap.inovagab.ui.gestor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.R
import br.com.fiap.inovagab.databinding.ActivityGestorBinding

class GestorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGestorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGestorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerGestor, OrientacoesGestorFragment())
            .commit()

        binding.bottomNavGestor.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_orientacoes_gestor -> OrientacoesGestorFragment()
                R.id.nav_ideias_gestor -> IdeiaGestorFragment()
                R.id.nav_projetos_gestor -> ProjetoGestorFragment()
                else -> OrientacoesGestorFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerGestor, fragment)
                .commit()
            true
        }
    }
}