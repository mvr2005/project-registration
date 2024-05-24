package com.mikhail.registrationpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mikhail.registrationpractice.databinding.ActivityLogInBinding
import constanс.Constant

class ActivityLogIn : AppCompatActivity() {
    lateinit var bindingClass : ActivityLogInBinding


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

         val key1 = intent.getStringExtra(Constant.KEY)
        if (key1 == Constant.REGISTRATION) {
            bindingClass.la1L2.visibility = View.VISIBLE
        } else if (key1 == Constant.LOG_IN) {
            bindingClass.la2L2.visibility = View.VISIBLE
        }
    }

        fun resultDataInMainActivity (view : View) {
            val intent = Intent()
            val lastName = bindingClass.txPl1.text.toString()
            val name = bindingClass.txPl2.text.toString()
            val loginReg = bindingClass.txPl3.text.toString()
            val login = bindingClass.tx1Pl2L2.text.toString()
            val passwordReg = bindingClass.txPl4.text.toString()
            val password = bindingClass.tx2Pl2l2.text.toString()
            intent.putExtra(Constant.LAST_NAME, lastName)
            intent.putExtra(Constant.NAME, name)
            intent.putExtra(Constant.LOGIN_REG, loginReg)
            intent.putExtra(Constant.LOGIN, login)
            intent.putExtra(Constant.PASSWORD_REG, passwordReg)
            intent.putExtra(Constant.PASSWORD, password)
            setResult(RESULT_OK, intent)
            finish()
        }

}