package com.mikhail.registrationpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mikhail.registrationpractice.databinding.ActivityLogInBinding
import constanс.constanс

class ActivityLogIn : AppCompatActivity() {
    lateinit var bindingClass : ActivityLogInBinding
    lateinit var lastName : String
    lateinit var name : String
    lateinit var login : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bindingClass = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bindingClass.la1L2.visibility = View.VISIBLE

    }

        fun resultDataInMainActivity (view : View) {
            val intent = Intent()
            lastName = bindingClass.txPl1.text.toString()
            name = bindingClass.txPl2.text.toString()
            login = bindingClass.txPl3.text.toString()
            intent.putExtra("${constanс.LAST_NAME}", "$lastName")
            intent.putExtra("${constanс.NAME}", "$name")
            intent.putExtra("${constanс.LOGIN}", "$login")
            setResult(RESULT_OK, intent)
            finish()
        }

}