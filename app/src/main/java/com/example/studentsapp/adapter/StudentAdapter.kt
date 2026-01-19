package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.StudentListRowBinding
import com.example.studentsapp.model.Student

/**
 * Adapter for the RecyclerView in StudentListActivity.
 * Handles displaying a list of Student objects.
 */
class StudentAdapter(
    private var students: List<Student>,
    private val onRowClick: (Student) -> Unit,
    private val onStudentCheckChanged: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(val binding: StudentListRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        
        with(holder.binding) {
            studentRowName.text = student.name
            studentRowId.text = "ID: ${student.id}"

            // Temporarily remove listener to avoid triggering it when setting state
            studentRowCheckbox.setOnCheckedChangeListener(null)
            studentRowCheckbox.isChecked = student.isChecked

            studentRowCheckbox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
                onStudentCheckChanged(student)
            }

            root.setOnClickListener {
                onRowClick(student)
            }
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}
