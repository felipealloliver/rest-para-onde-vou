package com.paraondevou.paraondevou.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.paraondevou.paraondevou.tipo.StatusRota
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date date

    @ManyToOne
    Usuario usuario

    @ManyToOne
    Local localOrigem

    @ManyToOne
    Local localDestino

    @Enumerated
    StatusRota status
}
