package com.paraondevou.paraondevou.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.aspectj.weaver.ast.Not

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @JsonIgnore
    Boolean ativo

    @Column(length = 100)
    String nome

    @Column(length = 100)
    @NotNull
    String email

    @Column(length = 100)
    @NotNull
    String senha

}
