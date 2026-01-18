package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentId = intent.getStringExtra("STUDENT_ID")
        loadStudentData()

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java).apply {
                putExtra("STUDENT_ID", studentId)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Reload data in case it was edited
        loadStudentData()
    }

    private fun loadStudentData() {
        studentId?.let { id ->
            val student = Model.instance.getStudentById(id)
            student?.let {
                binding.tvNameValue.text = it.name
                binding.tvIdValue.text = it.id
                binding.tvPhoneValue.text = it.phone
                binding.tvAddressValue.text = it.address
                binding.cbChecked.isChecked = it.isChecked
                
                // Update local studentId in case ID was changed in Edit screen
                studentId = it.id
            } ?: run {
                // If student was deleted
                finish()
            }
        }
    }
}
