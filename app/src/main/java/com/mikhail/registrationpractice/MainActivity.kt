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
import constanс.Constant

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

                   passwordG = result.data?.getStringExtra(Constant.PASSWORD_REG)!!

                    bindingClass.la1.visibility = View.VISIBLE
                    bindingClass.la2.visibility = View.GONE

                    lastNameG = result.data?.getStringExtra(Constant.LAST_NAME)!!
                    nameG = result.data?.getStringExtra(Constant.NAME)!!
                    loginG = result.data?.getStringExtra(Constant.LOGIN_REG)!!

                    bindingClass.tx5.text = lastNameG
                    bindingClass.tx3.text = nameG
                    bindingClass.tx7.text = loginG


                    bindingClass.im1.setImageResource(R.drawable.mic)

                    Log.d("MyLog1", passwordG)
                }
        }

        startDataColektion = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result : ActivityResult ->
            if (result.resultCode == RESULT_OK) {

                val textPasswordReg = result.data?.getStringExtra(Constant.PASSWORD)
                val textLogReg = result.data?.getStringExtra(Constant.LOGIN)

                if(textPasswordReg == passwordG && textLogReg == loginG) {

                    bindingClass.la1.visibility = View.VISIBLE
                    bindingClass.la2.visibility = View.GONE

                    bindingClass.tx5.text = lastNameG
                    bindingClass.tx3.text = nameG
                    bindingClass.tx7.text = loginG

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
        transitRegistration.putExtra(Constant.KEY, Constant.REGISTRATION)
        startDataRegistration?.launch(transitRegistration)
    }

    fun openDataColection (view : View) {
        val transitRegistrat = Intent(this, ActivityLogIn::class.java)
        transitRegistrat.putExtra(Constant.KEY, Constant.LOG_IN)
        startDataColektion?.launch(transitRegistrat)
    }

    fun exit (view: View) {

        bindingClass.la1.visibility = View.GONE
        bindingClass.textErr.visibility = View.GONE
        bindingClass.la2.visibility = View.VISIBLE

        Log.d("MyLog1", "$passwordG and $loginG")
    }



}