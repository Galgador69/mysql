package com.example.mysql.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql.activity.CadastroAlunoActivity
import com.example.mysql.activity.CadastroProfessorActivity
import com.example.mysql.databinding.ActivityItemAlunoBinding
import com.example.mysql.databinding.ActivityItemProfessorBinding
import com.example.mysql.model.Aluno
import com.example.mysql.model.Professor

class ProfessorAdapter (
    private val context: Context,
    private val deleteCallback: (Int) -> Unit
) : RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder>() {

    private var professores: List<Professor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorViewHolder {
        val binding = ActivityItemProfessorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfessorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfessorViewHolder, position: Int) {
        val professor = professores[position]
        holder.bind(professor)

        holder.binding.btnDeletarProf.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Excluir Professor")
                .setMessage("Deseja realmente excluir o aluno ${professor.nome}?")
                .setPositiveButton("Sim") { _, _ ->
                    deleteCallback(professor.id)
                }
                .setNegativeButton("Não", null)
                .show()
        }

        holder.binding.btnEditarProf.setOnClickListener {
            val intent = Intent(context, CadastroProfessorActivity::class.java)
            intent.putExtra("PROFESSOR_ID", professor.id)
            intent.putExtra("PROFESSOR_NOME", professor.nome)
            intent.putExtra("PROFESSOR_CPF", professor.cpf)
            intent.putExtra("PROFESSOR_EMAIL", professor.email)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return professores.size
    }

    fun setData(professores: List<Professor>) {
        this.professores = professores
        notifyDataSetChanged()
    }

    inner class ProfessorViewHolder(val binding: ActivityItemProfessorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(professor: Professor) {
            binding.apply {
                nomeTextViewProf.text = professor.nome
                cpfTextViewProf.text = professor.cpf
                emailTextViewProf.text = professor.email
            }
        }
    }
}

