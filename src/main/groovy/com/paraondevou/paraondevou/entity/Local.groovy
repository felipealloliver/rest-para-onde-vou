package com.paraondevou.paraondevou.entity

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@ToString
@EqualsAndHashCode(includes = ['id'])
class Local {
    @Id
    @GeneratedValue
    Long id

    String nomeLocal
    String qrCode
}
