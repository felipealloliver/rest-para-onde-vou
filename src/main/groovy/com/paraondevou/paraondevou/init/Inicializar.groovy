package com.paraondevou.paraondevou.init

import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.repository.LocalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class Inicializar implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    LocalRepository localRepository

    @Override
    void onApplicationEvent(ApplicationReadyEvent event) {
        def local1 = new Local(nomeLocal: "Clara Camarão", qrCode: "http://www.google.com.br")
        def local2 = new Local(nomeLocal: "Nelson Mandela", qrCode: "http://www.uol.com.br")
        def local3 = new Local(nomeLocal: "Reitoria", qrCode: "http://www.g1.com.br")

        localRepository.save(local1)
        localRepository.save(local2)
        localRepository.save(local3)
    }
}
