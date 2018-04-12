package com.paraondevou.paraondevou.entity

import com.paraondevou.paraondevou.tipo.StatusRota

class Rota {
    Long id
    Date date
    Usuario usuario
    Local localOrigem
    Local localDestino
    StatusRota status
}
