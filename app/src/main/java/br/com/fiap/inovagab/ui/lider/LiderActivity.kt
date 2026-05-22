package br.com.fiap.inovagab.ui.lider

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.R
import br.com.fiap.inovagab.databinding.ActivityLiderBinding
import br.com.fiap.inovagab.ui.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class LiderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                confirmarLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun confirmarLogout() {
        AlertDialog.Builder(this)
            .setTitle("Sair")
            .setMessage("Deseja realmente sair?")
            .setPositiveButton("Sim") { _, _ ->
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}