package br.com.fiap.inovagab.ui.gestor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentOrientacoesBinding
import com.google.firebase.firestore.FirebaseFirestore

class OrientacoesGestorFragment : Fragment() {

    private var _binding: FragmentOrientacoesBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrientacoesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarOrientacoes()
    }

    private fun carregarOrientacoes() {
        db.collection("orientacoes")
            .get()
            .addOnSuccessListener { documents ->
                val sb = StringBuilder()
                for (doc in documents) {
                    val titulo = doc.getString("titulo") ?: ""
                    val descricao = doc.getString("descricao") ?: ""
                    sb.append("📌 $titulo\n$descricao\n\n")
                }
                if (sb.isEmpty()) {
                    binding.tvOrientacoes.text = "Nenhuma orientação cadastrada ainda."
                } else {
                    binding.tvOrientacoes.text = sb.toString()
                }
            }
            .addOnFailureListener {
                binding.tvOrientacoes.text = "Erro ao carregar orientações."
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}