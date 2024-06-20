package com.example.mysql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql.adapter.AlunoAdapter
import com.example.mysql.databinding.ActivityItemAlunoBinding


class ItemAlunoActivity : AppCompatActivity() {//Início class

    //Configuração do viewBinding
    private val binding by lazy {
        ActivityItemAlunoBinding.inflate(layoutInflater)
    }

    //variável do Adapter
    private  lateinit var adapter: AlunoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {//Início fun onCreate
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }//Fim fun onCreate

}//Fim class
