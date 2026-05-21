package br.com.fiap.inovagab.ui.gestor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.inovagab.databinding.FragmentProjetoGestorBinding
import br.com.fiap.inovagab.model.Projeto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProjetoGestorFragment : Fragment() {

    private var _binding: FragmentProjetoGestorBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjetoGestorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarProjetos()

        binding.btnCadastrarProjeto.setOnClickListener {
            cadastrarProjeto()
        }
    }

    private fun cadastrarProjeto() {
        val titulo = binding.etTituloProjeto.text.toString().trim()
        val descricao = binding.etDescricaoProjeto.text.toString().trim()
        val etapa = binding.etEtapa.text.toString().trim()
        val prazo = binding.etPrazo.text.toString().trim()
        val investimento = binding.etInvestimento.text.toString().toDoubleOrNull() ?: 0.0

        if (titulo.isEmpty() || descricao.isEmpty()) {
            Toast.makeText(requireContext(), "Preencha título e descrição", Toast.LENGTH_SHORT).show()
            return
        }

        val uid = auth.currentUser?.uid ?: return
        val projeto = Projeto(
            titulo = titulo,
            descricao = descricao,
            etapa = etapa,
            prazo = prazo,
            investimento = investimento,
            gestorUid = uid
        )

        db.collection("projetos").add(projeto)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Projeto cadastrado!", Toast.LENGTH_SHORT).show()
                limparCampos()
                carregarProjetos()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Erro ao cadastrar projeto", Toast.LENGTH_SHORT).show()
            }
    }

    private fun carregarProjetos() {
        val uid = auth.currentUser?.uid ?: return
        db.collection("projetos")
            .whereEqualTo("gestorUid", uid)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    binding.tvProjetos.text = "Nenhum projeto cadastrado ainda."
                    return@addOnSuccessListener
                }
                val sb = StringBuilder()
                for (doc in documents) {
                    val titulo = doc.getString("titulo") ?: ""
                    val etapa = doc.getString("etapa") ?: ""
                    val status = doc.getString("status") ?: ""
                    val prazo = doc.getString("prazo") ?: ""
                    val investimento = doc.getDouble("investimento") ?: 0.0
                    sb.append("📋 $titulo\nEtapa: $etapa | Status: $status\nPrazo: $prazo | Investimento: R$ $investimento\n\n")
                }
                binding.tvProjetos.text = sb.toString()
            }
    }

    private fun limparCampos() {
        binding.etTituloProjeto.text?.clear()
        binding.etDescricaoProjeto.text?.clear()
        binding.etEtapa.text?.clear()
        binding.etPrazo.text?.clear()
        binding.etInvestimento.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}