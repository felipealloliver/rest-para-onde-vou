package com.paraondevou.paraondevou.repository

import com.paraondevou.paraondevou.entity.Local
import com.paraondevou.paraondevou.entity.Percurso
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PercursoRepository extends PagingAndSortingRepository<Percurso, Long> {
    Percurso findOneByLocalPartidaAndLocalDestinoAndNumOrdem(Local localPartida, Local localDestino, Long NumOrdem)
    List<Percurso> findByLocalPartidaAndLocalDestino(Local localPartida, Local localDestino)
    Percurso findOneById(Long id)
}