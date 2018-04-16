package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Rota
import com.paraondevou.paraondevou.repository.RotaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    @GetMapping("/")
    List<Rota> listarTudo() {
         rotaRepository.findAll()
    }

    @PostMapping("/")
    ResponseEntity<Rota> inserirNovo (@RequestBody Rota rota) {
        rotaRepository.save(rota)
        ResponseEntity.ok(rota)
    }
}
