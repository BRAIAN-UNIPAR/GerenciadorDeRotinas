package com.gerenciadorDeRotinas.gerenciadorDeRotinas.model

data class Rotina(
    val codigo: String? = null,
    var nome: String? = null,
    var colaborador: String? = null,
    var objetivo: String? = null,
    var instrucoes: String? = null,
    var dataDeExecucao: String? = null,
    var horarioDeExecucao: String? = null,
    var tempoDecorrido: Long? = null,
    var horarioConclusao: String? = null,
    var concluido: Boolean = false
)
