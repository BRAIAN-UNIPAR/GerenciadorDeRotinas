package com.gerenciadorDeRotinas.gerenciadorDeRotinas.controller

import com.gerenciadorDeRotinas.gerenciadorDeRotinas.model.Rotina
import com.gerenciadorDeRotinas.gerenciadorDeRotinas.service.RotinaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rotinas")
@CrossOrigin(origins = ["http://localhost:5173"]) // ajuste para a porta do seu front
class RotinaRestController(
    private val rotinaService: RotinaService
) {

    @GetMapping
    fun listar(): List<Rotina> = rotinaService.encontrarTodos()

    @GetMapping("/{codigo}")
    fun buscarPorId(@PathVariable codigo: String): Rotina? = rotinaService.encontrarRotina(codigo)

    @PostMapping
    fun criar(@RequestBody rotina: Rotina): Rotina = rotinaService.registrarRotina(rotina)

    @DeleteMapping("/{codigo}")
    fun excluir(@PathVariable codigo: String): Boolean = rotinaService.excluirRegistro(codigo)
}
