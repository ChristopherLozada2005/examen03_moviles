package com.lozada.christopher.laboratoriocalificado03

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lozada.christopher.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private val teachers: List<TeacherResponse>,
    private val context: Context
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: TeacherResponse) {
            binding.tvTeacherName.text = teacher.getFullName()
            binding.tvTeacherEmail.text = teacher.email
            binding.tvTeacherPhoneNumber.text = teacher.phone_number
            Glide.with(binding.root.context).load(teacher.image_url).into(binding.ivTeacherPhoto)

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_CALL).apply {
                    data = Uri.parse("tel:${teacher.phone_number}")
                }
                try {
                    context.startActivity(intent)
                } catch (e: SecurityException) {
                    Toast.makeText(context, "Permiso denegado para realizar llamadas", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Error al intentar realizar la llamada", Toast.LENGTH_SHORT).show()
                }
            }
            binding.root.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${teacher.email}")
                    putExtra(Intent.EXTRA_SUBJECT, "Consulta")
                    putExtra(Intent.EXTRA_TEXT, "Estimado ${teacher.getFullName()},")
                }
                try {
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Error al intentar enviar el correo", Toast.LENGTH_SHORT).show()
                }
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount() = teachers.size
}
