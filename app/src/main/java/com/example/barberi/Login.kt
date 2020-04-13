package com.example.barberi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.barberi.Barber.MainBarber
import com.example.barberi.BasesDatos.MyDatabase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var db: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        db = Room.databaseBuilder(applicationContext, MyDatabase::class.java,"MyDatabase").allowMainThreadQueries().build()
        // LOGGIN



        // perfiles

        var perfil = getString(R.string.B)
        et_Barber.setOnClickListener {
            perfil = getString(R.string.B)
        }

        et_Usuario.setOnClickListener {
            perfil=getString(R.string.U)
        }



        et_Ingreso.setOnClickListener {

            Thread(Runnable{
                val usuario = db.usuarioDao().findByName(user.text.toString())
                val barberos= db.barberosDao().findByName(user.text.toString())
                runOnUiThread{
                    if((usuario != null ) && (perfil.toString() != null)) {
                        if ( (usuario.nombre == user.text.toString() && (perfil.toString() == "Usuario")  )){
                            if (usuario.contrasenaa == contra.text.toString()){
                                val intent = Intent(this,MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }

                    // Ingreso de Barberos

                    if  ((barberos != null ) && (perfil.toString() != null)){
                        if ( (barberos.name== user.text.toString()) && (perfil.toString() == "Barbero")){
                            if (barberos.passw == contra.text.toString()){
                                val intent = Intent(this,MainBarber::class.java)
                                startActivity(intent)
                            }

                        }

                    }

                    else{

                        textView.text = "Usuario " + usuario + " no encontrado"
                        //val intent = Intent(this,Registro::class.java)
                        //startActivity(intent)
                    }


                }
            }).start()

        }

        // REGISTRO
        et_registro.setOnClickListener {
            val intent= Intent(this, Registro::class.java)
            startActivityForResult(intent,1)
        }

    }
}
