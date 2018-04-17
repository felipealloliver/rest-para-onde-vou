package com.paraondevou.paraondevou.repository

import com.paraondevou.paraondevou.entity.Usuario
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
    Usuario findOneByEmail(String email)
    Usuario findOneByEmailAndDeviceToken(String email, String deviceID)
}