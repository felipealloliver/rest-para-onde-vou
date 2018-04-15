package com.paraondevou.paraondevou.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Local {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id

    String nomeLocal
    String qrCode
}
