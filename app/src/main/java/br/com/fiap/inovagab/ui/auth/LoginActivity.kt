package br.com.fiap.inovagab.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.inovagab.databinding.ActivityLoginBinding
import br.com.fiap.inovagab.ui.gestor.GestorActivity
import br.com.fiap.inovagab.ui.lider.LiderActivity
import br.com.fiap.inovagab.ui.operador.OperadorActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnEntrar.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val senha = binding.etSenha.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty()) {
                mostrarErro("Preencha todos os campos")
                return@setOnClickListener
            }

            binding.btnEntrar.isEnabled = false
            binding.tvErro.visibility = View.GONE

            auth.signInWithEmailAndPassword(email, senha)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid ?: return@addOnSuccessListener
                    db.collection("usuarios").document(uid).get()
                        .addOnSuccessListener { doc ->
                            val perfil = doc.getString("perfil") ?: ""
                            redirecionarPorPerfil(perfil)
                        }
                        .addOnFailureListener {
                            mostrarErro("Erro ao buscar perfil do usuário")
                            binding.btnEntrar.isEnabled = true
                        }
                }
                .addOnFailureListener {
                    mostrarErro("E-mail ou senha incorretos")
                    binding.btnEntrar.isEnabled = true
                }
        }
    }

    private fun redirecionarPorPerfil(perfil: String) {
        val intent = when (perfil) {
            "gestor" -> Intent(this, GestorActivity::class.java)
            "lider" -> Intent(this, LiderActivity::class.java)
            else -> Intent(this, OperadorActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun mostrarErro(mensagem: String) {
        binding.tvErro.text = mensagem
        binding.tvErro.visibility = View.VISIBLE
    }
}
