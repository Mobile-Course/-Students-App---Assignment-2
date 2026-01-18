package com.example.studentsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsViewModel : ViewModel() {

    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> = _students

    init {
        refreshStudents()
    }

    fun refreshStudents() {
        _students.value = Model.instance.getAllStudents()
    }

    fun addStudent(student: Student) {
        Model.instance.addStudent(student)
        refreshStudents()
    }

    fun updateStudent(oldId: String, student: Student) {
        Model.instance.updateStudent(oldId, student)
        refreshStudents()
    }

    fun deleteStudent(id: String) {
        Model.instance.deleteStudent(id)
        refreshStudents()
    }

    fun toggleStudentCheck(student: Student) {
        student.isChecked = !student.isChecked
        Model.instance.updateStudent(student.id, student)
        refreshStudents()
    }
}
