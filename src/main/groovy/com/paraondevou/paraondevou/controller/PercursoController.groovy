package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Percurso
import com.paraondevou.paraondevou.repository.PercursoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/percurso")
class PercursoController {
    @Autowired
    PercursoRepository percursoRepository

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Percurso> percursoBD = percursoRepository.findById(id)

        if (percursoBD.empty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(percursoBD.get())
        }
    }

    @GetMapping("/all")
    Percurso listarTudo() {
        List<Percurso> lista = percursoRepository.findAll()
    }
}
