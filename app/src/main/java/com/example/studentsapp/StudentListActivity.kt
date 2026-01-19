package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.adapter.StudentAdapter
import com.example.studentsapp.databinding.ActivityStudentListBinding
import com.example.studentsapp.viewmodel.StudentsViewModel

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding
    private val viewModel: StudentsViewModel by viewModels()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshStudents()
    }

    private fun setupRecyclerView() {
        adapter = StudentAdapter(
            students = emptyList(),
            onRowClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                    putExtra(StudentDetailsActivity.STUDENT_ID_KEY, student.id)
                }
                startActivity(intent)
            },
            onStudentCheckChanged = { student ->
                viewModel.toggleStudentCheck(student)
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.students.observe(this) { students ->
            adapter.updateData(students)
        }
    }
}
