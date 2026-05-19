package br.com.fiap.inovagab.model

data class Projeto(
    val id: String = "",
    val titulo: String = "",
    val descricao: String = "",
    val etapa: String = "",
    val status: String = "em_andamento",
    val investimento: Double = 0.0,
    val retornoFinanceiro: Double = 0.0,
    val ganhosProdutividade: Double = 0.0,
    val prazo: String = "",
    val gestorUid: String = "",
    val dataCriacao: Long = System.currentTimeMillis()
)
