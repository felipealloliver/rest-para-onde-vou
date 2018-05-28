package com.paraondevou.paraondevou.entity

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Percurso {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id
    Long numOrdem

    @ManyToOne
    Local localPartida

    @ManyToOne
    Local localDestino

    @ManyToOne
    Imagem imagem

    @JsonIgnore
    Boolean ativo

    @Column(length = 100)
    String descricao
}
