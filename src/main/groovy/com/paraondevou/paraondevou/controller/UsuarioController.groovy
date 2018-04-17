package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Usuario
import com.paraondevou.paraondevou.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuario")
class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository

    @GetMapping
    Iterable<Usuario> listarTudo() {
        usuarioRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id)

        if (usuarioBD) {
            ResponseEntity.ok(usuarioBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity inserirNovo(@RequestBody Usuario usuario) {
        def usuarioBD = usuarioRepository.findOneByEmailAndDeviceToken(usuario.email, usuario.deviceToken)

        if (usuarioBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            usuarioRepository.save(usuario)
            ResponseEntity.status(HttpStatus.CREATED).header("Location", "/" + usuario.id).body()
        }
    }

    @PostMapping("/autenticar")
    ResponseEntity autenticar(@RequestBody Usuario usuario) {
        def usuarioAutent = usuarioRepository.findOneByEmailAndDeviceToken(usuario.email, usuario.deviceToken)

        if (usuarioAutent) {
            ResponseEntity.ok().body(usuarioAutent)
        } else {
            def usuarioNovo = new Usuario(email: usuario.email, deviceToken: usuario.deviceToken)
            usuarioRepository.save(usuarioNovo)
            ResponseEntity.created().body(usuarioNovo)
        }
    }
}
