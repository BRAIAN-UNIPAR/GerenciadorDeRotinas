package com.gerenciadorDeRotinas.gerenciadorDeRotinas.service

import com.gerenciadorDeRotinas.gerenciadorDeRotinas.model.Rotina
import com.gerenciadorDeRotinas.gerenciadorDeRotinas.repository.RotinaRepository
import org.springframework.stereotype.Service

@Service
class RotinaService(
    private val rotinaRepository: RotinaRepository //Injeção do Repository
) {

    fun buscarPorTipos(tipo: String): List<Rotina> {
        return rotinaRepository.buscarPorTipos(tipo)
    }

    fun registrarRotina(
        codigo: String,
        descricao: String?//pode ser nulo
    ): Rotina {
        return rotinaRepository.salvar(
            Rotina(
                codigo = codigo,
                descricao = descricao
            )
        )
    }

    fun encontrarRotina(codigo: String): Rotina? {
        return rotinaRepository.buscarId(codigo)
    }

    fun encontrarTodos(): List<Rotina> {
        return rotinaRepository.buscarTodos()
    }

    fun excluirRegistro(codigo: String): Boolean {
        return rotinaRepository.excluirId(codigo)
    }
}