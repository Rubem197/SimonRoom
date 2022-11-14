package com.example.simon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SimonEntity::class], version = 1)
abstract class SimonDataBase: RoomDatabase() {
    abstract fun SimonDao() : SimonDao

}