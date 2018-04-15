package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Imagem
import com.paraondevou.paraondevou.repository.ImagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/imagem")
class ImagemController {
    @Autowired
    ImagemRepository imagemRepository

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Imagem> imagemBD = imagemRepository.findById(id)

        if (imagemBD.empty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(imagemBD.get())
        }
    }

    @GetMapping("/all")
    Imagem listarTudo() {
        List<Imagem> lista = imagemRepository.findAll()
    }
}
