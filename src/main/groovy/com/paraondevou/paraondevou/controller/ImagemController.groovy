package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Imagem
import com.paraondevou.paraondevou.repository.ImagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

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

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(imagem.id).toUri()
        ResponseEntity.created(location).build()
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteimagem(@PathVariable("id") Long id) {
        Imagem imagem = imagemRepository.findOneById(id)

        if (imagem) {
            imagem.ativo = false
            imagemRepository.save(imagem)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }


}
