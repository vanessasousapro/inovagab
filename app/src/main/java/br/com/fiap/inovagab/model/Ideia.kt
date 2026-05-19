package br.com.fiap.inovagab.model

data class Ideia(
    val id: String = "",
    val titulo: String = "",
    val descricao: String = "",
    val autorUid: String = "",
    val autorNome: String = "",
    val status: String = "pendente", // "pendente", "aprovada", "rejeitada"
    val prioridade: Int = 0,
    val dataCriacao: Long = System.currentTimeMillis()
)
