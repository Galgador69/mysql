package com.example.mysql.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysql.adapter.TurmaAdapter
import com.example.mysql.api.EnderecoAPI
import com.example.mysql.api.RetrofitHelper

import com.example.mysql.databinding.ActivityListagemTurmaBinding
import com.example.mysql.model.Professor

import com.example.mysql.model.Turma
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListagemTurmaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListagemTurmaBinding
    private lateinit var turmaAdapter: TurmaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListagemTurmaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadTurmas()

        binding.btnCadastroTurma.setOnClickListener {
            val intent = Intent(this, CadastroTurmaActivity::class.java)
            cadastroTurmaActivityResultLauncher.launch(intent)
        }
    }

    private val cadastroTurmaActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Atualizar a lista de alunos após cadastro/edição
                loadTurmas()
            }
        }

    private fun setupRecyclerView() {
       turmaAdapter = TurmaAdapter(this) { turmaId ->
            deleteTurma(turmaId)
        }
        binding.turmaRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.turmaRecyclerView.adapter = turmaAdapter
    }

    private fun loadTurmas() {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.getTurma()

        call.enqueue(object : Callback<List<Turma>> {
            override fun onResponse(call: Call<List<Turma>>, response: Response<List<Turma>>) {
                if (response.isSuccessful) {
                    response.body()?.let { turmas ->
                        turmaAdapter.setData(turmas)
                    }
                } else {
                    Toast.makeText(
                        this@ListagemTurmaActivity,
                        "Falha ao carregar Turmas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Turma>>, t: Throwable) {
                Toast.makeText(this@ListagemTurmaActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun deleteTurma(turmaId: Int) {
        val retrofit = RetrofitHelper.getRetrofitInstance()
        val service = retrofit.create(EnderecoAPI::class.java)
        val call = service.excluirProfessor(turmaId)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ListagemTurmaActivity,
                        "Turma excluído com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    loadTurmas() // Atualizar a lista de alunos após exclusão
                } else {
                    Toast.makeText(
                        this@ListagemTurmaActivity,
                        "Falha ao excluir Turma",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ListagemTurmaActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
