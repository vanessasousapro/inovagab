package br.com.fiap.inovagab.ui.operador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentNovaIdeiaBinding
import br.com.fiap.inovagab.model.Ideia
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NovaIdeiaFragment : Fragment() {

    private var _binding: FragmentNovaIdeiaBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNovaIdeiaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviarIdeia.setOnClickListener {
            val titulo = binding.etTituloIdeia.text.toString().trim()
            val descricao = binding.etDescricaoIdeia.text.toString().trim()

            if (titulo.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val uid = auth.currentUser?.uid ?: return@setOnClickListener

            db.collection("usuarios").document(uid).get()
                .addOnSuccessListener { doc ->
                    val nomeAutor = doc.getString("nome") ?: "Operador"
                    val ideia = Ideia(
                        titulo = titulo,
                        descricao = descricao,
                        autorUid = uid,
                        autorNome = nomeAutor
                    )

                    db.collection("ideias").add(ideia)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Ideia enviada com sucesso!", Toast.LENGTH_SHORT).show()
                            binding.etTituloIdeia.text?.clear()
                            binding.etDescricaoIdeia.text?.clear()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Erro ao enviar ideia", Toast.LENGTH_SHORT).show()
                        }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}