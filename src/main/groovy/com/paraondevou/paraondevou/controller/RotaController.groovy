package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Rota
import com.paraondevou.paraondevou.repository.RotaRepository
import com.paraondevou.paraondevou.tipo.StatusRota
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import sun.misc.Request

import javax.swing.text.html.Option

@RestController
@RequestMapping("/rota")
class RotaController {
    @Autowired
    RotaRepository rotaRepository

    @GetMapping
    Iterable<Rota> listarTudo() {
        rotaRepository.findAll()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Optional<Rota> rotaBD = rotaRepository.findById(id)
        if (rotaBD) {
            ResponseEntity.ok(rotaBD.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity<Rota> inserirNovo (@RequestBody Rota rota) {
        rotaRepository.save(rota)
        ResponseEntity.status(HttpStatus.CREATED).header("Location", "/" + rota.id).body()
    }

    @PatchMapping("/{id}")
    ResponseEntity finalizaRota (@PathVariable("id") Long id) {
        Rota rota = rotaRepository.findOneById(id)

        if (rota) {
            if (rota.status == StatusRota.CANCELADO) {
                ResponseEntity.status(HttpStatus.FORBIDDEN).body()
            } else {
                rota.status = StatusRota.FINALIZADO
                rotaRepository.save(rota)
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(rota.id).toUri()
                ResponseEntity.created(location).build()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity cancelaRota (@PathVariable("id") Long id) {
        Rota rota = rotaRepository.findOneById(id)

        if (rota) {
            if (rota.status == StatusRota.FINALIZADO) {
                ResponseEntity.status(HttpStatus.FORBIDDEN).body()
            } else {
                rota.status = StatusRota.CANCELADO
                rotaRepository.save(rota)
                ResponseEntity.ok().body(rota)
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
