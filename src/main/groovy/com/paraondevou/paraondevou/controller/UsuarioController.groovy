package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Usuario
import com.paraondevou.paraondevou.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

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
        Usuario usuarioBD = usuarioRepository.findOneByEmail(usuario.email)

        if (usuarioBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        } else {
            usuario.ativo = true
            usuarioRepository.save(usuario)

            AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder()
            auth.inMemoryAuthentication()
                    .withUser(usuario.email)
                    .password("{noop}" + usuario.senha)
                    .roles("ADMIN")

            ResponseEntity.status(HttpStatus.CREATED).header("Location", "/" + usuario.id).body()
        }
    }

    @PostMapping("/autenticar")
    ResponseEntity autenticar(@RequestBody Usuario usuario) {
        Usuario usuarioAutent = usuarioRepository.findOneByEmailAndSenha(usuario.email, usuario.senha)

        if (usuarioAutent) {
            ResponseEntity.ok().body(usuarioAutent)
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findOneById(id)

        if (usuario) {
            usuario.ativo = false
            usuarioRepository.save(usuario)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
