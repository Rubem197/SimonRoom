package com.example.simon

import androidx.room.*

@Dao
interface SimonDao {

    @Query("SELECT * FROM simonJ_tabla")
    fun mostrarTodosDatos() : List<SimonEntity>

    @Query("Select * From simonJ_tabla Where nombreJugador =:nombre")
    fun mostrarDatosPorNombre(nombre:String) : SimonEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJugador(jugador:SimonEntity)


    @Update
    suspend fun update(jugador: SimonEntity)

}