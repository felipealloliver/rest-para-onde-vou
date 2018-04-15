package com.paraondevou.paraondevou.repository

import com.paraondevou.paraondevou.entity.Usuario
import org.springframework.data.repository.PagingAndSortingRepository

interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

}