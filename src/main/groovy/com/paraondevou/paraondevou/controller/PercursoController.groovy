package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Percurso
import com.paraondevou.paraondevou.repository.PercursoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/percurso")
class PercursoController {
    @Autowired
    PercursoRepository percursoRepository

    @GetMapping
    Iterable<Percurso> listarTudo() {
        percursoRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Percurso> percursoBD = percursoRepository.findById(id)

        if (percursoBD) {
            ResponseEntity.ok(percursoBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity<Percurso> inserirNovo (@RequestBody Percurso percurso) {
        Percurso percursoBD = percursoRepository.findOneByLocalPartidaAndLocalDestino(percurso.localPartida, percurso.localDestino)

        if (percursoBD) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(percursoBD)
        } else {
            percursoRepository.save(percurso)
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(percurso.id).toUri()
            ResponseEntity.created(location).build()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletePercurso(@PathVariable("id") Long id) {
        Percurso percurso = percursoRepository.findOneById(id)

        if (percurso) {
            percurso.ativo = false
            percursoRepository.save(percurso)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
