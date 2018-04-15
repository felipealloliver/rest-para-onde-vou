package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Usuario
import com.paraondevou.paraondevou.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuario")
class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository

    @GetMapping("/{id}")
    Usuario listar(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id)
        usuarioBD.get()
    }
}
