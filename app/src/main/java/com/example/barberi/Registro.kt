package com.example.barberi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.room.Room
import com.example.barberi.BasesDatos.MyDatabase
import com.example.barberi.BasesDatos.Usuario
import kotlinx.android.synthetic.main.activity_registro.*
import java.sql.Types

class Registro : AppCompatActivity() {


    // base de datos
    private lateinit var db: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // base
        db = Room.databaseBuilder(applicationContext,MyDatabase::class.java,"MyDatabase").allowMainThreadQueries().build()



// BOTON REGISTRAR


        et_register.setOnClickListener {

            if (TextUtils.isEmpty(et_name.text.toString())){
                et_name.error = "Ingrese nombre de usuario"
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_contras.text.toString())) {
                et_contras.error = "Ingrese Contraseña"
                return@setOnClickListener
            }
            else if (et_name.text.isEmpty()  || et_contras.text.isEmpty() ) {
                Toast.makeText(this, " Completar datos", Toast.LENGTH_SHORT).show()
            }
            //else if (et_contras.text.toString() != et_passC.text.toString()) {
            //  Toast.makeText(this, " Contraseñas no coinciden", Toast.LENGTH_SHORT).show()

            //}
            else if (et_contras.text.length < 6 ) {
                Toast.makeText(this, " La contrasena debe ser minimo de 6 caracteres", Toast.LENGTH_SHORT).show()
            }
            else if (et_contras.text.length >= 6) {
                // Ingresa a la base de datos
                val usuario = Usuario(Types.NULL,et_name.text.toString(),et_contras.text.toString())
                Thread(Runnable{
                    db.usuarioDao().insertUser(usuario)
                    runOnUiThread{
                        et_name.setText("")
                        et_contras.setText("")
                    }
                }).start()
                goToLoginActivity()
            }

        }







    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
        super.onBackPressed()
    }


    private fun goToLoginActivity() {
        val intent = Intent(this,Login::class.java)
        startActivity(intent)
    }

}
