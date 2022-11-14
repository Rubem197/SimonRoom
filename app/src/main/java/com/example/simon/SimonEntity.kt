package com.example.simon

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity ( tableName = "simonJ_tabla")

data class SimonEntity(

    @PrimaryKey()
    var nombreJugador:String="",
    var pulsacionesPartidas:Int=0,



    )
