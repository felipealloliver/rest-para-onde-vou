package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Usuario
import com.paraondevou.paraondevou.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuario")
class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id)

        if (usuarioBD.empty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(usuarioDB.get())
        }
    }

    @GetMapping("/all")
    Usuario listarTudo() {
        List<Usuario> lista = usuarioRepository.findAll()
    }
}
