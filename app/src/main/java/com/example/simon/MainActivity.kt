package com.example.simon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), Comunicador {

    private lateinit var roomDB: SimonDataBase
    var nombre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras
         nombre = bundle?.getString("Nombre").toString()

        roomDB = Room.databaseBuilder(
            this.applicationContext,
            SimonDataBase::class.java,
            "simonDataBase"
        ).build()


        GlobalScope.launch(Dispatchers.IO) {


            val buscarDB = roomDB.SimonDao().mostrarDatosPorNombre(nombre)

            var meterTexto = findViewById<TextView>(R.id.tvNombreJugador)

            var meterPulsaciones = findViewById<TextView>(R.id.tvPulsacioneJugador)

            meterPulsaciones.text = buscarDB.pulsacionesPartidas.toString()


            meterTexto.text = buscarDB.nombreJugador

        }
    }

    val listaMaquina = ArrayList<Int>()
    val listaJugador = ArrayList<Int>()


    override fun iniciarPartida() {

        listaMaquina.clear()
        listaJugador.clear()

        empezarRonda()

    }

    fun empezarRonda() {


        listaMaquina.add(generarNumeroAleatorio())
        listaJugador.clear()
        iluminarBotones(listaMaquina)


    }

    fun aumentarContador(){

        GlobalScope.launch(Dispatchers.IO) {

            val jugador = roomDB.SimonDao().mostrarDatosPorNombre(nombre)

            jugador.pulsacionesPartidas++

            roomDB.SimonDao().update(jugador)

        }
    }

    override fun pulsarVerde() {
        val btnVerde = this.findViewById<Button>(R.id.btnVerde)
        val btnVerdeIluminado = this.findViewById<Button>(R.id.btnVerde2)

        cambiarVisivilidad(btnVerde, btnVerdeIluminado)

        aumentarContador()

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnVerdeIluminado, btnVerde)
            }

        }
        listaJugador.add(0)
        Log.d("COLORES_Jv", listaJugador.size.toString())
        Log.d("COLORES_Mv", listaMaquina.size.toString())

        if (listaJugador.size == listaMaquina.size) {
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.MICROSECONDS.toMillis(500))
                comprobarResultados()
            }
        }
    }

    override fun pulsarRojo() {
        val btnRojo = this.findViewById<Button>(R.id.btnRojo)
        val btnRojoIluminado = this.findViewById<Button>(R.id.btnRojo2)

        cambiarVisivilidad(btnRojo, btnRojoIluminado)
        aumentarContador()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnRojoIluminado, btnRojo)
            }
        }
        listaJugador.add(1)
        Log.d("COLORES_Jrojo", listaJugador.size.toString())
        Log.d("COLORES_Mrojo", listaMaquina.size.toString())

        if (listaJugador.size == listaMaquina.size) {
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.MICROSECONDS.toMillis(500))
                comprobarResultados()
            }
        }

    }

    override fun pulsarAmarillo() {
        val btnAmarillo = this.findViewById<Button>(R.id.btnAmarillo)
        val btnAmarilloIluminado = this.findViewById<Button>(R.id.btnAmarillo2)

        cambiarVisivilidad(btnAmarillo, btnAmarilloIluminado)
        aumentarContador()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnAmarilloIluminado, btnAmarillo)
            }
        }
        listaJugador.add(2)
        Log.d("COLORES_Jama", listaJugador.size.toString())
        Log.d("COLORES_Mama", listaMaquina.size.toString())

        if (listaJugador.size == listaMaquina.size) {
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.MICROSECONDS.toMillis(500))
                comprobarResultados()
            }

        }


    }

    override fun pulsarAzul() {
        val btnAzul = this.findViewById<Button>(R.id.btnAzul)
        val btnAzulIluminado = this.findViewById<Button>(R.id.btnAzul2)
        aumentarContador()
        cambiarVisivilidad(btnAzul, btnAzulIluminado)
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnAzulIluminado, btnAzul)
            }
        }

        listaJugador.add(3)
        Log.d("COLORES_Jzul", listaJugador.size.toString())
        Log.d("COLORES_Mzul", listaMaquina.size.toString())

        if (listaJugador.size == listaMaquina.size) {
            CoroutineScope(Dispatchers.IO).launch {
                delay(TimeUnit.MICROSECONDS.toMillis(500))
                comprobarResultados()
            }
        }


    }

    fun maquinaVerde() {
        val btnVerde = this.findViewById<Button>(R.id.btnVerde)
        val btnVerdeIluminado = this.findViewById<Button>(R.id.btnVerde2)

        cambiarVisivilidad(btnVerde, btnVerdeIluminado)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnVerdeIluminado, btnVerde)
            }
        }


    }

    fun maquinaRojo() {
        val btnRojo = this.findViewById<Button>(R.id.btnRojo)
        val btnRojoIluminado = this.findViewById<Button>(R.id.btnRojo2)

        cambiarVisivilidad(btnRojo, btnRojoIluminado)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnRojoIluminado, btnRojo)
            }
        }

    }

    fun maquinaAzul() {
        val btnAzul = this.findViewById<Button>(R.id.btnAzul)
        val btnAzulIluminado = this.findViewById<Button>(R.id.btnAzul2)

        cambiarVisivilidad(btnAzul, btnAzulIluminado)

        cambiarVisivilidad(btnAzul, btnAzulIluminado)
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnAzulIluminado, btnAzul)
            }
        }
    }

    fun maquinaAmarillo() {
        val btnAmarillo = this.findViewById<Button>(R.id.btnAmarillo)
        val btnAmarilloIluminado = this.findViewById<Button>(R.id.btnAmarillo2)

        cambiarVisivilidad(btnAmarillo, btnAmarilloIluminado)

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {
                cambiarVisivilidad(btnAmarilloIluminado, btnAmarillo)
            }
        }
    }

    fun cambiarVisivilidad(boton1: Button, boton2: Button) {

        boton1.visibility = View.INVISIBLE;
        boton2.visibility = View.VISIBLE;
    }

    fun generarNumeroAleatorio(): Int {
        val opcionesRandom = arrayOf(0, 1, 2, 3)


        val eleccionRandom = opcionesRandom.random()
        println(eleccionRandom)
        return eleccionRandom
    }


    fun iluminarBotones(lista: List<Int>) {


        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(1))
            withContext(Dispatchers.Main) {


                for (i in lista.indices) {

                    if (lista[i] == 0) {
                        maquinaVerde()
                        delay(TimeUnit.SECONDS.toMillis(1))
                    } else if (lista[i] == 1) {
                        maquinaRojo()
                        delay(TimeUnit.SECONDS.toMillis(1))
                    } else if (lista[i] == 2) {
                        maquinaAmarillo()
                        delay(TimeUnit.SECONDS.toMillis(1))
                    } else {
                        maquinaAzul()
                        delay(TimeUnit.SECONDS.toMillis(1))
                    }
                }
            }
        }
    }

    fun comprobarResultados() {

        if (listaMaquina == listaJugador) {
            empezarRonda()
        }else{
            //mostrarMensajePerder().show()

        }

    }

    fun mostrarMensajePerder(): AlertDialog {

        val mensaje = AlertDialog.Builder(this)
        mensaje.setTitle("Has perdido")
        mensaje.setMessage("Pulse Aceptar para salir")
        mensaje.setNeutralButton("Aceptar", null)

        return mensaje.create()
    }

}

