package com.example.mysql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql.R
import com.example.mysql.adapter.AlunoAdapter
import com.example.mysql.adapter.TurmaAdapter
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.databinding.ActivityItemTurmaBinding
import com.example.mysql.model.Turma

class ItemTurmaActivity: AppCompatActivity() {//Início class

    //Configuração do viewBinding
    private val binding by lazy {
        ActivityItemTurmaBinding.inflate(layoutInflater)
    }

    //variável do Adapter
    private  lateinit var adapter: TurmaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {//Início fun onCreate
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }//Fim fun onCreate

}//Fim class