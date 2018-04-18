package com.paraondevou.paraondevou.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Percurso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id

    @ManyToOne
    Local localPartida

    @ManyToOne
    Local localDestino

    @ManyToOne
    Imagem imagem

    Boolean ativo
    Long numOrdem
    String descricao
}
