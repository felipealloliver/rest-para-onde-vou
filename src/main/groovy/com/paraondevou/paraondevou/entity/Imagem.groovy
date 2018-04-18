package com.paraondevou.paraondevou.entity

import javassist.bytecode.ByteArray

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Imagem {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id

    Boolean ativo
    String descricaoImagem

    String imagem
}
