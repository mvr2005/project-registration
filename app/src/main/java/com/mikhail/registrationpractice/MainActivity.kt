package com.mikhail.registrationpractice

import android.content.Intent
import android.os.Bundle
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
    var startDataColection : ActivityResultLauncher<Intent>? = null


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

        startDataColection = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val textLastName = result.data?.getStringExtra("${constanс.LAST_NAME}")
                    val textName = result.data?.getStringExtra("${constanс.NAME}")
                    val login = result.data?.getStringExtra("${constanс.LOGIN}")
                    bindingClass.la1.visibility = View.VISIBLE
                    bindingClass.la2.visibility = View.GONE
                    bindingClass.tx5.text = textLastName.toString()
                    bindingClass.tx3.text = textName.toString()
                    bindingClass.tx7.text = login.toString()
                    bindingClass.im1.setImageResource(R.drawable.mic)
                }
        }

    }

        fun openDataColection (view : View) {
            startDataColection?.launch(Intent(this, ActivityLogIn::class.java))
        }

}