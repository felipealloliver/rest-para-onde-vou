package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Rota
import com.paraondevou.paraondevou.repository.RotaRepository
import org.hibernate.engine.spi.Mapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.xml.ws.Response

@RestController
@RequestMapping("/rota")
class RotaController {
    @Autowired
    RotaRepository rotaRepository

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Rota> rotaBD = rotaRepository.findById(id)
        if (rotaBD.empty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(rotaBD.get())
        }
    }

    @GetMapping("/all")
    Rota listarTudo() {
        List<Rota> lista = rotaRepository.findAll()
    }
}
