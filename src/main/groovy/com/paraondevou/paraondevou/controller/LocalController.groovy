package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.repository.LocalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/local")
class LocalController {
    @Autowired
    LocalRepository localRepository

    @GetMapping("/{id}")
    Local listar(@PathVariable("id") Long id) {
        Optional<Local> localBD = localRepository.findById(id)
        return localBD.get()
    }
}
