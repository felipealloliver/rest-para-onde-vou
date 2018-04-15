package com.paraondevou.paraondevou.init

import com.paraondevou.paraondevou.entity.Imagem
import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.repository.ImagemRepository
import com.paraondevou.paraondevou.repository.LocalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class Inicializar implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    LocalRepository localRepository
    @Autowired
    ImagemRepository imagemRepository

    @Override
    void onApplicationEvent(ApplicationReadyEvent event) {
        def local1
        local1 = new Local(nomeLocal: "Clara Camarão", qrCode: "http://www.google.com.br")
        def local2
        local2 = new Local(nomeLocal: "Nelson Mandela", qrCode: "http://www.uol.com.br")
        def local3
        local3 = new Local(nomeLocal: "Reitoria", qrCode: "http://www.g1.com.br")
        def imagem1
        imagem1 = new Imagem(descricaoImagem: "Imagem 1")
        def imagem2
        imagem2 = new Imagem(descricaoImagem: "Imagem 2")
        def imagem3
        imagem3 = new Imagem(descricaoImagem: "Imagem 3")

        localRepository.save(local1)
        localRepository.save(local2)
        localRepository.save(local3)
        imagemRepository.save(imagem1)
        imagemRepository.save(imagem2)
        imagemRepository.save(imagem3)
    }
}
