package com.paraondevou.paraondevou.repository

import com.paraondevou.paraondevou.entity.Imagem
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ImagemRepository extends PagingAndSortingRepository<Imagem, Long> {

}