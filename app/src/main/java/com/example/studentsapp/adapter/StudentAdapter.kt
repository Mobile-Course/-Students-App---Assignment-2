package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.StudentListRowBinding
import com.example.studentsapp.model.Student

class StudentAdapter(
    private var students: List<Student>,
    private val onRowClick: (Student) -> Unit,
    private val onCheckChanged: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(val binding: StudentListRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.binding.studentRowName.text = student.name
        holder.binding.studentRowId.text = "ID: ${student.id}"
        
        // Temporarily remove listener to avoid triggering it when setting state
        holder.binding.studentRowCheckbox.setOnCheckedChangeListener(null)
        holder.binding.studentRowCheckbox.isChecked = student.isChecked
        
        holder.binding.studentRowCheckbox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked // Update instance directly or via callback
            onCheckChanged(student)
        }

        holder.binding.root.setOnClickListener {
            onRowClick(student)
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}
