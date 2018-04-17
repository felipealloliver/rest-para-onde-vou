package com.paraondevou.paraondevou.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String email
    String deviceToken
}
