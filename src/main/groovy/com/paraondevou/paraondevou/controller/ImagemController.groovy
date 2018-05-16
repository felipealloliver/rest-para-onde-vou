package com.paraondevou.paraondevou.controller

import com.paraondevou.paraondevou.entity.Imagem
import com.paraondevou.paraondevou.repository.ImagemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/imagem")
class ImagemController {
    @Autowired
    ImagemRepository imagemRepository

    @GetMapping
    ResponseEntity negado() {
        ResponseEntity.status(HttpStatus.FORBIDDEN).build()
    }

    @GetMapping("/{id}")
    ResponseEntity listar(@PathVariable("id") Long id) {
        Imagem imagem = imagemRepository.findOneById(id)

        if (imagem) {
            if (imagem.ativo) {
                ResponseEntity.ok().body(imagem)
            } else {
                ResponseEntity.notFound().build()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}/imagem")
    ResponseEntity<byte[]> downloadimage(@PathVariable("id") Long id) {
        Imagem imagem = imagemRepository.findOneById(id)

        if (imagem) {
            if (imagem.ativo) {
                return ResponseEntity.ok()
                        .header("Content-Disposition", "inline;filename=${imagem.id}.png")
                        .contentLength(imagem.imagem.length)
                        .contentType(MediaType.IMAGE_PNG)
                        .body(imagem.imagem)
            } else {
                ResponseEntity.notFound().build()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/{id}")
    ResponseEntity atualizardados(@PathVariable("id") Long id, @RequestBody Map<String, Object> informacao) {
        Imagem imagem = imagemRepository.findOneById(id)

        if (imagem) {
            if (imagem.ativo) {
                imagem.descricaoImagem = informacao.get("descricaoImagem")
                imagemRepository.save(imagem)
                ResponseEntity.created(URI.create('/imagem/' + imagem.id.toString())).build()
            } else {
                ResponseEntity.notFound().build()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    ResponseEntity inserirImagem(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            ResponseEntity.badRequest().build()
        } else {
            Imagem imagem = new Imagem(imagem: file.getBytes(), ativo: true)
            imagemRepository.save(imagem)

            ResponseEntity.created().header('Identity', imagem.id.toString()).build()
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteimagem(@PathVariable("id") Long id) {
        Imagem imagem = imagemRepository.findOneById(id)

        if (imagem) {
            imagem.ativo = false
            imagemRepository.save(imagem)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
