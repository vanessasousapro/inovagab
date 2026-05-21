package br.com.fiap.inovagab.ui.lider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentProjetosLiderBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProjetosLiderFragment : Fragment() {

    private var _binding: FragmentProjetosLiderBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjetosLiderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarProjetos()
    }

    private fun carregarProjetos() {
        db.collection("projetos")
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    binding.tvProjetosLider.text = "Nenhum projeto cadastrado ainda."
                    return@addOnSuccessListener
                }

                val sb = StringBuilder()
                for (doc in documents) {
                    val titulo = doc.getString("titulo") ?: ""
                    val etapa = doc.getString("etapa") ?: ""
                    val status = doc.getString("status") ?: ""
                    val prazo = doc.getString("prazo") ?: ""
                    val investimento = doc.getDouble("investimento") ?: 0.0
                    val retorno = doc.getDouble("retornoFinanceiro") ?: 0.0
                    val produtividade = doc.getDouble("ganhosProdutividade") ?: 0.0

                    sb.append("📋 $titulo\n")
                    sb.append("Etapa: $etapa | Status: $status\n")
                    sb.append("Prazo: $prazo\n")
                    sb.append("💰 Investimento: R$ $investimento\n")
                    sb.append("📈 Retorno: R$ $retorno\n")
                    sb.append("⚡ Produtividade: $produtividade%\n\n")
                }
                binding.tvProjetosLider.text = sb.toString()
            }
            .addOnFailureListener {
                binding.tvProjetosLider.text = "Erro ao carregar projetos."
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}