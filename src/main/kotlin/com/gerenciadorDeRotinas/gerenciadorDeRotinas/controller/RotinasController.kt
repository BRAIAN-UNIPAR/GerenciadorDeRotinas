package com.gerenciadorDeRotinas.gerenciadorDeRotinas.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RotinasController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }
}