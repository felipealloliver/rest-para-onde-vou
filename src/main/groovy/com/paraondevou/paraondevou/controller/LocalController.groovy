package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.repository.LocalRepository
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
@RequestMapping("/local")
class LocalController {
    @Autowired
    LocalRepository localRepository

    @GetMapping
    Iterable<Local> listarTudo() {
        localRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Local> localBD = localRepository.findById(id)

        if (localBD) {
            ResponseEntity.ok(localBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity<Local> inserirNovo(@RequestBody Local local) {
        local.ativo = true
        localRepository.save(local)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(local.id).toUri()
        ResponseEntity.created(location).build()
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteLocal(@PathVariable("id") Long id) {
        Local local = localRepository.findOneById(id)

        if (local) {
            local.ativo = false
            localRepository.save(local)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
