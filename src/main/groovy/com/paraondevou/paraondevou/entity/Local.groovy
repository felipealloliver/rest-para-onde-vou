package com.paraondevou.paraondevou.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class Local {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id

    @OneToOne
    Imagem imagem

    Boolean ativo
    String nomeLocal
    String qrCode
}
