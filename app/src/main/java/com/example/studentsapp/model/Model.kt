package com.example.studentsapp.model

/**
 * Singleton class to manage the data (Students).
 * Simulates a local database or data source.
 */
class Model private constructor() {

    private val students = mutableListOf<Student>()

    companion object {
        val instance: Model by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Model()
        }
    }

    init {
        // Add some initial data for testing
        for (i in 0..15) {
            students.add(
                Student(
                    name = "Student $i",
                    id = "$i",
                    phone = "050-123456$i",
                    address = "Street $i",
                    isChecked = false
                )
            )
        }
    }

    fun getAllStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun deleteStudent(id: String) {
        students.removeAll { it.id == id }
    }

    fun updateStudent(oldId: String, updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == oldId }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }
}
