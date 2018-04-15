package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Imagem
import com.paraondevou.paraondevou.repository.ImagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/imagem')
class ImagemController {
    @Autowired
    ImagemRepository imagemRepository

    @GetMapping('/{id}')
    Imagem listar(@PathVariable("id") Long id) {
        Optional<Imagem> imagemBD = imagemRepository.findById(id)
        imagemBD.get()
    }
}
