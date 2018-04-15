package com.paraondevou.paraondevou.entity

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@ToString
@EqualsAndHashCode(includes = ['id'])
class Percurso {
    @Id
    @GeneratedValue
    Long id

    Long numOrdem
    Local localPartida
    Local localDestino
    Imagem imagem
    String descricao
}
