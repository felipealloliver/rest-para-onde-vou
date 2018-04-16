package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.repository.LocalRepository
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
@RequestMapping("/local")
class LocalController {
    @Autowired
    LocalRepository localRepository

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Local> localBD = localRepository.findById(id)

        if (localBD.empty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(localBD.get())
        }
    }

    @GetMapping("/")
    List<Local> listarTudo() {
        localRepository.findAll()
    }

    @PostMapping("/")
    ResponseEntity<Local> inserirNovo(@RequestBody Local local) {
        localRepository.save(local)
        ResponseEntity.ok(local)
    }
}
