package com.example.mysql.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql.adapter.ProfessorAdapter
import com.example.mysql.databinding.ActivityItemProfessorBinding

class ItemProfessorActivity : AppCompatActivity() {//Início class
    //Configuração do viewBinding
    private val binding by lazy {
    ActivityItemProfessorBinding.inflate(layoutInflater)
}
    //variável do Adapter
    private  lateinit var adapter: ProfessorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}