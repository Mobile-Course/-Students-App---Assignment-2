package com.example.studentsapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityAddStudentBinding
import com.example.studentsapp.model.Student
import com.example.studentsapp.viewmodel.StudentsViewModel

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private val viewModel: StudentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val id = binding.etId.text.toString()
            val phone = binding.etPhone.text.toString()
            val address = binding.etAddress.text.toString()
            val isChecked = binding.cbChecked.isChecked

            if (name.isNotEmpty() && id.isNotEmpty()) {
                val newStudent = Student(name, id, phone, address, isChecked)
                viewModel.addStudent(newStudent)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}
