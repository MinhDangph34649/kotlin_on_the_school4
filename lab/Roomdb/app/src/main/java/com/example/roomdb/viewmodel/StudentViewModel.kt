package com.example.roomdb.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.repository.Repository
import com.example.roomdb.room.StudentEntity

import kotlinx.coroutines.launch

class StudentViewModel(val repository: Repository): ViewModel() {

    fun addStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.addStudentToRoom(student)
        }
    }

    val students = repository.getAllStudents()

    fun deleteStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.deleteStudentFromRoom(student)
        }
    }

    fun updateStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.updateStudent(student)
        }
    }
}