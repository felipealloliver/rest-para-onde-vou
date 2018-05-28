package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.entity.Percurso
import com.paraondevou.paraondevou.repository.LocalRepository
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

import javax.xml.ws.Response

@RestController
@RequestMapping("/percurso")
class PercursoController {
    @Autowired
    PercursoRepository percursoRepository
    @Autowired
    LocalRepository localRepository

    @GetMapping
    ResponseEntity acessonegado() {
        ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Percurso percurso = percursoRepository.findOneById(id)

        if (percurso) {
            ResponseEntity.ok(percurso)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{localPartida}/{localDestino}")
    ResponseEntity listaPercursos(@PathVariable("localPartida") Long idLocalPartida, @PathVariable("localPartida") Long idLocalDestino) {
        Local localPartida = localRepository.findOneById(idLocalPartida)
        Local localDestino = localRepository.findOneById(idLocalDestino)

        if ((!localDestino) || (!localPartida)) {
            ResponseEntity.badRequest().build()
        } else {
            // ResponseEntity.ok().body(percursoRepository.findByLocalPartidaAndLocalDestino(localPartida, localDestino))
            // ResponseEntity.ok().body(percursoRepository.findOneByLocalPartidaAndLocalDestinoAndNumOrdem(localPartida, localDestino, 1))
            ResponseEntity.ok().body('ok')
        }
    }

    @PostMapping
    ResponseEntity inserirNovo (@RequestBody Percurso percurso) {
        Local local1 = localRepository.findOneById(percurso.localPartida.id)
        Local local2 = localRepository.findOneById(percurso.localDestino.id)

        if ((!local1) || (!local2)) {
            ResponseEntity.badRequest().build()
        } else {
            percursoRepository.save(percurso)
            ResponseEntity.created().header("identifier", percurso.id.toString()).build()
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
