package com.paraondevou.paraondevou.repository

import com.paraondevou.paraondevou.entity.Rota
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface RotaRepository extends PagingAndSortingRepository<Rota, Long> {

}