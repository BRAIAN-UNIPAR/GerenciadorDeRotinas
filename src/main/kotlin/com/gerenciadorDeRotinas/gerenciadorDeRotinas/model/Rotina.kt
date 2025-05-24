package com.gerenciadorDeRotinas.gerenciadorDeRotinas.model

data class Rotina(
    val codigo: String? = null,
    val nome: String? = null,
    val colaborador: String? = null,
    val objetivo: String? = null,
    val instrucoes: String? = null,
    val dataDeExecucao: String? = null,
    val horarioDeExecucao: String? = null,
    val tempoDecorrido: Long? = null,
    val horarioConclusao: String? = null,
    val concluido: Boolean = false
)
