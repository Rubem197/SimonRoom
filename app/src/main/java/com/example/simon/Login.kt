package com.example.simon


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simon.databinding.ActivityLoginBinding
import com.example.simon.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var roomDB: SimonDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roomDB = Room.databaseBuilder(
            this.applicationContext,
            SimonDataBase::class.java,
            "simonDataBase"
        ).build()




        binding.btnLogin.setOnClickListener {

            writeData()
        }





        binding.btnIrSimon.setOnClickListener {
            irLogin()
        }


    }

    private fun writeData() {

        val nombre = binding.etNombre.text.toString()

        if (nombre.isNotEmpty()) {

            val simonJugador = SimonEntity(
                nombre, 1
            )

            GlobalScope.launch(Dispatchers.IO) {
                roomDB.SimonDao().insertJugador(simonJugador)
            }

            binding.etNombre.text.clear()
        }
    }

    private fun irLogin() {
        val recogerNombre = findViewById<EditText>(R.id.etNombreBuscar)
        val nombre = recogerNombre.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("Nombre", nombre)
        startActivity(intent)

    }


}