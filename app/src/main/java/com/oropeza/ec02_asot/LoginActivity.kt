package com.oropeza.ec02_asot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.oropeza.ec02_asot.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isEmailOk: Boolean = false;
    private var isPasswordOk: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        binding.tilEmail.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validateInputs(text.toString(),binding.tilPassword.editText?.text.toString())
        }
        binding.tilPassword.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validateInputs(binding.tilEmail.editText?.text.toString(),text.toString())
        }
        binding.btnLogin.setOnClickListener {
            Toast.makeText(this,"Login Iniciado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs(email:String, password:String): Boolean {
        val expectedEmail = "ejemplo@idat.edu.pe"
        val expectedPassword = "123456"
        val isEmailOk = email == expectedEmail && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordOk = password == expectedPassword && password.length >= 6
        return isPasswordOk && isEmailOk
    }
}