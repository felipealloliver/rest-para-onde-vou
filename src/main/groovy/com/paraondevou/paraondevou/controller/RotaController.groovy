package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Rota
import com.paraondevou.paraondevou.repository.RotaRepository
import org.hibernate.engine.spi.Mapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rota")
class RotaController {
    @Autowired
    RotaRepository rotaRepository

    @GetMapping("/{id}")
    Rota listar(@PathVariable("id") Long id) {
        Optional<Rota> rotaBD = rotaRepository.findById(id)
        rotaBD.get()
    }
}
