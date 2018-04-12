package com.paraondevou.paraondevou.repository

import com.paraondevou.paraondevou.entity.Local
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface LocalRepository extends PagingAndSortingRepository<Local, Long> {

}