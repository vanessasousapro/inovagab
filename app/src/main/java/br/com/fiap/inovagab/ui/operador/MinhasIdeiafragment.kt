package br.com.fiap.inovagab.ui.operador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentMinhasIdeiaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MinhasIdeiaFragment : Fragment() {

    private var _binding: FragmentMinhasIdeiaBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMinhasIdeiaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarMinhasIdeias()
    }

    private fun carregarMinhasIdeias() {
        val uid = auth.currentUser?.uid ?: return

        db.collection("ideias")
            .whereEqualTo("autorUid", uid)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    binding.tvMinhasIdeias.text = "Você ainda não cadastrou nenhuma ideia."
                    return@addOnSuccessListener
                }

                val sb = StringBuilder()
                for (doc in documents) {
                    val titulo = doc.getString("titulo") ?: ""
                    val descricao = doc.getString("descricao") ?: ""
                    val status = when (doc.getString("status")) {
                        "aprovada" -> "✅ Aprovada"
                        "rejeitada" -> "❌ Rejeitada"
                        else -> "⏳ Pendente"
                    }
                    sb.append("💡 $titulo\n$descricao\nStatus: $status\n\n")
                }
                binding.tvMinhasIdeias.text = sb.toString()
            }
            .addOnFailureListener {
                binding.tvMinhasIdeias.text = "Erro ao carregar ideias."
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}