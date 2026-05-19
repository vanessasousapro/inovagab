package br.com.fiap.inovagab.model

data class Usuario(
    val uid: String = "",
    val nome: String = "",
    val email: String = "",
    val perfil: String = "" // "operador", "gestor", "lider"
)
