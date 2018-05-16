package com.paraondevou.paraondevou.entity

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
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

    @JsonIgnore
    Boolean ativo

    @Column(length = 100)
    String nomeLocal

    @Column(length = 100)
    String qrCode
}
