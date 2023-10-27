package com.example.proyectopruebafirebase

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectopruebafirebase.databinding.ActivityMainBinding
import com.example.proyectopruebafirebase.model.Persona
import com.example.proyectopruebafirebase.model.Sexo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database: FirebaseDatabase
    lateinit var sexo: DatabaseReference
    lateinit var persona: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseDatabase.getInstance()
        sexo = database.getReference("Generos")
        persona = database.getReference("Personas")
        Insertar()
    }
    fun Insertar() {
        binding.btnGuardar.setOnClickListener {
            val id = sexo.push().key
            val genero = Sexo(id!!, "Femenino")
            sexo.child(id!!).setValue(genero)
            val idPersona = persona.push().key
            val nombre = Persona(
                idPersona!!,
                binding.txtNombre.text.toString(),
                genero.toString(),
                binding.txtEdad.text.toString())
            persona.child(id!!).setValue(nombre)
        }
    }
}