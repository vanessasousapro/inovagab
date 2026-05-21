package br.com.fiap.inovagab.ui.lider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentOrientacoesLiderBinding
import com.google.firebase.firestore.FirebaseFirestore

class OrientacoesLiderFragment : Fragment() {

    private var _binding: FragmentOrientacoesLiderBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrientacoesLiderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarOrientacoes()

        binding.btnSalvarOrientacao.setOnClickListener {
            salvarOrientacao()
        }
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
                    binding.tvOrientacoesLider.text = "Nenhuma orientação cadastrada ainda."
                } else {
                    binding.tvOrientacoesLider.text = sb.toString()
                }
            }
            .addOnFailureListener {
                binding.tvOrientacoesLider.text = "Erro ao carregar orientações."
            }
    }

    private fun salvarOrientacao() {
        val titulo = binding.etTituloOrientacao.text.toString().trim()
        val descricao = binding.etDescricaoOrientacao.text.toString().trim()

        if (titulo.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val orientacao = hashMapOf(
            "titulo" to titulo,
            "descricao" to descricao,
            "dataCriacao" to System.currentTimeMillis()
        )

        db.collection("orientacoes").add(orientacao)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Orientação salva!", Toast.LENGTH_SHORT).show()
                binding.etTituloOrientacao.text?.clear()
                binding.etDescricaoOrientacao.text?.clear()
                carregarOrientacoes()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Erro ao salvar orientação", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}