package br.com.fiap.inovagab.model

data class Orientacao(
    val id: String = "",
    val titulo: String = "",
    val descricao: String = "",
    val dataCriacao: Long = System.currentTimeMillis()
)
