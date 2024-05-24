package com.mikhail.registrationpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mikhail.registrationpractice.databinding.ActivityMainBinding
import constanс.constanс

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding
    var startDataRegistration : ActivityResultLauncher<Intent>? = null
    var startDataColektion : ActivityResultLauncher<Intent>? = null
    private lateinit var passwordG : String
    private lateinit var nameG : String
    private lateinit var lastNameG : String
    private lateinit var loginG : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bindingClass.la2.visibility = View.VISIBLE
        bindingClass.la1.visibility = View.GONE
        bindingClass.textErr.visibility = View.GONE

        startDataRegistration = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
                if (result.resultCode == RESULT_OK) {

                   val textPassword = result.data?.getStringExtra("${constanс.PASSWORD_REG}")

                    bindingClass.la1.visibility = View.VISIBLE
                    bindingClass.la2.visibility = View.GONE
                    val textLastName = result.data?.getStringExtra("${constanс.LAST_NAME}")
                    val textName = result.data?.getStringExtra("${constanс.NAME}")
                    val textLogin = result.data?.getStringExtra("${constanс.LOGIN_REG}")
                    bindingClass.tx5.text = textLastName.toString()
                    bindingClass.tx3.text = textName.toString()
                    bindingClass.tx7.text = textLogin.toString()


                    bindingClass.im1.setImageResource(R.drawable.mic)

                    passwordG = textPassword.toString()
                    nameG = textName.toString()
                    lastNameG = textLastName.toString()
                    loginG = textLogin.toString()

                    Log.d("MyLog1", "$textPassword + $passwordG")
                }
        }

        startDataColektion = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result : ActivityResult ->
            if (result.resultCode == RESULT_OK) {

//                val textPassword = result.data?.getStringExtra("$constanс.PASSWORD")
                val textPasswordReg = result.data?.getStringExtra("${constanс.PASSWORD}")
                val textLogReg = result.data?.getStringExtra("${constanс.LOGIN}")

                if(textPasswordReg == passwordG && textLogReg == loginG) {
                    bindingClass.la1.visibility = View.VISIBLE
                    bindingClass.la2.visibility = View.GONE
                    bindingClass.tx5.text = lastNameG
                    bindingClass.tx3.text = nameG
                    bindingClass.tx7.text = loginG
                    bindingClass.tx7.text = result.data?.getStringExtra("${constanс.LOGIN}")
                    bindingClass.im1.setImageResource(R.drawable.mic)
                } else {
                    bindingClass.la2.visibility = View.GONE
                    bindingClass.la1.visibility = View.GONE
                    bindingClass.textErr.visibility = View.VISIBLE
                }

            }
        }



    }

        fun openRegistration (view : View) {
           val transitRegistration = Intent(this, ActivityLogIn::class.java)
            transitRegistration.putExtra("$constanс.KEY", "$constanс.REGISTRATION")
            startDataRegistration?.launch(transitRegistration)
        }

    fun openDataColection (view : View) {
        val transitRegistrat = Intent(this, ActivityLogIn::class.java)
        transitRegistrat.putExtra("$constanс.KEY", "$constanс.LOG_IN")
        startDataColektion?.launch(transitRegistrat)
    }

    fun exit (view: View) {

        bindingClass.la1.visibility = View.GONE
        bindingClass.textErr.visibility = View.GONE
        bindingClass.la2.visibility = View.VISIBLE

        Log.d("MyLog1", "$passwordG and $loginG")
    }



}