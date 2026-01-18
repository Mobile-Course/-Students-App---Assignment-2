package com.example.studentsapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.example.studentsapp.viewmodel.StudentsViewModel

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private val viewModel: StudentsViewModel by viewModels()
    private var oldStudentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        oldStudentId = intent.getStringExtra("STUDENT_ID")
        loadStudentData()
        setupListeners()
    }

    private fun loadStudentData() {
        oldStudentId?.let { id ->
            val student = Model.instance.getStudentById(id)
            student?.let {
                binding.etName.setText(it.name)
                binding.etId.setText(it.id)
                binding.etPhone.setText(it.phone)
                binding.etAddress.setText(it.address)
                binding.cbChecked.isChecked = it.isChecked
            }
        }
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val newId = binding.etId.text.toString()
            val phone = binding.etPhone.text.toString()
            val address = binding.etAddress.text.toString()
            val isChecked = binding.cbChecked.isChecked

            if (name.isNotEmpty() && newId.isNotEmpty() && oldStudentId != null) {
                val updatedStudent = Student(name, newId, phone, address, isChecked)
                viewModel.updateStudent(oldStudentId!!, updatedStudent)
                finish()
            }
        }

        binding.btnDelete.setOnClickListener {
            oldStudentId?.let {
                viewModel.deleteStudent(it)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}
