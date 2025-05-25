package com.gerenciadorDeRotinas.gerenciadorDeRotinas.service

import com.gerenciadorDeRotinas.gerenciadorDeRotinas.model.Rotina
import com.gerenciadorDeRotinas.gerenciadorDeRotinas.repository.RotinaRepository
import org.springframework.stereotype.Service

@Service
class RotinaService(
    private val rotinaRepository: RotinaRepository
) {

    //Métodos
    fun encontrarTodos(): List<Rotina> = rotinaRepository.encontrarTodos()

    fun encontrarRotina(codigo: String): Rotina? = rotinaRepository.encontrarPorId(codigo)

    fun registrarRotina(rotina: Rotina): Rotina {
        return rotinaRepository.salvar(rotina)
    }

    fun excluirRegistro(codigo: String): Boolean = rotinaRepository.excluir(codigo)


    fun atualizarStatus(codigo: String, concluido: Boolean, tempoDecorrido: Long?, horarioConclusao: String?): Rotina? {
        return rotinaRepository.atualizarStatus(codigo, concluido, tempoDecorrido, horarioConclusao)
    }
}
