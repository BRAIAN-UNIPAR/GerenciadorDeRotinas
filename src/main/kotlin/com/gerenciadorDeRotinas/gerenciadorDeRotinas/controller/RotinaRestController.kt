package com.gerenciadorDeRotinas.gerenciadorDeRotinas.controller

import com.gerenciadorDeRotinas.gerenciadorDeRotinas.model.Rotina
import com.gerenciadorDeRotinas.gerenciadorDeRotinas.service.RotinaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rotinas")
@CrossOrigin(origins = ["http://localhost:5173"]) // ajuste para o front
class RotinaRestController(
    private val rotinaService: RotinaService
) {

    @GetMapping
    fun listar(): List<Rotina> = rotinaService.encontrarTodos()

    @GetMapping("/{codigo}")
    fun buscarPorId(@PathVariable codigo: String): Rotina? = rotinaService.encontrarRotina(codigo)

    @PostMapping
    fun criar(@RequestBody rotina: Rotina): Rotina = rotinaService.registrarRotina(rotina)

    @PutMapping("/{codigo}")
    fun atualizar(@PathVariable codigo: String, @RequestBody rotina: Rotina): Rotina {
        val rotinaAtualizada = rotina.copy(codigo = codigo)
        return rotinaService.registrarRotina(rotinaAtualizada)
    }

    @DeleteMapping("/{codigo}")
    fun excluir(@PathVariable codigo: String): Boolean = rotinaService.excluirRegistro(codigo)


    @PatchMapping("/{codigo}/status")
    fun atualizarStatus(
        @PathVariable codigo: String,
        @RequestBody statusUpdate: StatusUpdateRequest
    ): Rotina? {
        return rotinaService.atualizarStatus(
            codigo,
            statusUpdate.concluido,
            statusUpdate.tempoDecorrido,
            statusUpdate.horarioConclusao
        )
    }

    // auxilia para mapear JSON do patch
    data class StatusUpdateRequest(
        val concluido: Boolean,
        val tempoDecorrido: Long?,
        val horarioConclusao: String?
    )
}
