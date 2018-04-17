package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Imagem
import com.paraondevou.paraondevou.repository.ImagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.xml.ws.Response

@RestController
@RequestMapping("/imagem")
class ImagemController {
    @Autowired
    ImagemRepository imagemRepository

    @GetMapping
    Iterable<Imagem> listarTudo() {
        imagemRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Imagem> imagemBD = imagemRepository.findById(id)

        if (imagemBD) {
            ResponseEntity.ok(imagemBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity inserirNovo(@RequestBody Imagem imagem) {
        imagemRepository.save(imagem)
        ResponseEntity.ok(imagem)
    }
}
