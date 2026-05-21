package br.com.fiap.inovagab.ui.lider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentDashboardBinding
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarDashboard()
    }

    private fun carregarDashboard() {
        db.collection("projetos").get()
            .addOnSuccessListener { projetos ->
                var totalInvestimento = 0.0
                var totalRetorno = 0.0
                var totalProdutividade = 0.0
                var totalProjetos = projetos.size()

                for (doc in projetos) {
                    totalInvestimento += doc.getDouble("investimento") ?: 0.0
                    totalRetorno += doc.getDouble("retornoFinanceiro") ?: 0.0
                    totalProdutividade += doc.getDouble("ganhosProdutividade") ?: 0.0
                }

                val roi = if (totalInvestimento > 0) {
                    ((totalRetorno - totalInvestimento) / totalInvestimento) * 100
                } else 0.0

                binding.tvTotalProjetos.text = totalProjetos.toString()
                binding.tvInvestimento.text = "R$ %.2f".format(totalInvestimento)
                binding.tvRetorno.text = "R$ %.2f".format(totalRetorno)
                binding.tvRoi.text = "%.1f%%".format(roi)
                binding.tvProdutividade.text = "%.1f%%".format(totalProdutividade)
            }

        db.collection("ideias").get()
            .addOnSuccessListener { ideias ->
                var aprovadas = 0
                var pendentes = 0
                var rejeitadas = 0

                for (doc in ideias) {
                    when (doc.getString("status")) {
                        "aprovada" -> aprovadas++
                        "rejeitada" -> rejeitadas++
                        else -> pendentes++
                    }
                }

                binding.tvIdeaisAprovadas.text = aprovadas.toString()
                binding.tvIdeaisPendentes.text = pendentes.toString()
                binding.tvIdeaisRejeitadas.text = rejeitadas.toString()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}