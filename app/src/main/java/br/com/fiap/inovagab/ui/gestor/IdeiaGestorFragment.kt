package br.com.fiap.inovagab.ui.gestor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentIdeiaGestorBinding
import com.google.firebase.firestore.FirebaseFirestore

class IdeiaGestorFragment : Fragment() {

    private var _binding: FragmentIdeiaGestorBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIdeiaGestorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarIdeias()
    }

    private fun carregarIdeias() {
        db.collection("ideias")
            .whereEqualTo("status", "pendente")
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    binding.tvIdeias.text = "Nenhuma ideia pendente no momento."
                    return@addOnSuccessListener
                }

                val sb = StringBuilder()
                for (doc in documents) {
                    val id = doc.id
                    val titulo = doc.getString("titulo") ?: ""
                    val descricao = doc.getString("descricao") ?: ""
                    val autor = doc.getString("autorNome") ?: ""
                    sb.append("💡 $titulo\n👤 $autor\n$descricao\n\n")
                }
                binding.tvIdeias.text = sb.toString()

                // Botões de aprovar e rejeitar última ideia (simplificado)
                val primeiroDoc = documents.first()
                binding.btnAprovar.setOnClickListener {
                    atualizarStatus(primeiroDoc.id, "aprovada")
                }
                binding.btnRejeitar.setOnClickListener {
                    atualizarStatus(primeiroDoc.id, "rejeitada")
                }
            }
            .addOnFailureListener {
                binding.tvIdeias.text = "Erro ao carregar ideias."
            }
    }

    private fun atualizarStatus(id: String, status: String) {
        db.collection("ideias").document(id)
            .update("status", status)
            .addOnSuccessListener {
                val msg = if (status == "aprovada") "Ideia aprovada!" else "Ideia rejeitada."
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                carregarIdeias()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Erro ao atualizar status", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}