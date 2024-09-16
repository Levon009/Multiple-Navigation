package com.example.jetpack_multiplenavigation.students.studentsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.draf.students.data.model.Student
import com.example.jetpack_multiplenavigation.students.data.StudentsEvent
import com.example.jetpack_multiplenavigation.students.data.StudentsSortType
import com.example.jetpack_multiplenavigation.students.data.StudentsState
import com.example.jetpack_multiplenavigation.students.data.model.ProgrammingLanguage
import com.example.jetpack_multiplenavigation.students.data.model.StudentMark
import com.example.jetpack_multiplenavigation.students.screensUI.allLanguages
import com.example.jetpack_multiplenavigation.students.screensUI.allMarks
import com.example.jetpack_multiplenavigation.students.studentsDatabase.StudentsDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class StudentsViewModel(
    private val dao: StudentsDao
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _sortType = MutableStateFlow(StudentsSortType.AGE)
    private val _students = _sortType.flatMapLatest { sortType ->
        when(sortType) {
            StudentsSortType.FIRST_NAME -> dao.getStudentsOrderByFirstName()
            StudentsSortType.SECOND_NAME -> dao.getStudentsOrderBySecondName()
            StudentsSortType.AGE -> dao.getStudentsOrderByAge()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(StudentsState())
    val state = combine(_state, _sortType, _students) { state, sortType, students ->
        state.copy(
            students = students,
            studentsSortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StudentsState())

    private val _languages = MutableStateFlow<List<ProgrammingLanguage>>(emptyList())
    val languages = _languages.asStateFlow()

    private val _marks = MutableStateFlow<List<StudentMark>>(emptyList())
    val marks = _marks.flatMapLatest {
        dao.getAllMarks()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        setLanguages()
        setMarks()
    }

    fun checkForRefresh() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            delay(2000)
            _isRefreshing.update { false }
        }
    }

    fun onEvent(event: StudentsEvent) {
        when(event) {
            StudentsEvent.DeleteAllStudents -> {
                viewModelScope.launch {
                    dao.deleteAllStudents()
                }
            }
            is StudentsEvent.DeleteStudent -> {
                viewModelScope.launch {
                    dao.deleteStudent(event.student)
                }
            }
            StudentsEvent.SaveStudent -> {
                val firstName = state.value.firstName
                val secondName = state.value.secondName
                val age = state.value.age
                val marks = _marks.value
                if (firstName.isBlank() || secondName.isBlank() || age <= 0) {
                    return
                }
                val student = Student(firstName, secondName, age, 0, marks)
                viewModelScope.launch {
                    dao.upsertStudent(student)
                }

                _state.update {
                    it.copy(
                        showLanguagesDialog = false,
                        studentId = -1,
                        firstName = "",
                        secondName = "",
                        age = 0
                    )
                }
            }
            is StudentsEvent.SetAge -> {
                _state.update { it.copy(age = event.age) }
            }
            is StudentsEvent.SetFirstName -> {
                _state.update { it.copy(firstName = event.firstName) }
            }
            is StudentsEvent.SetSecondName -> {
                _state.update { it.copy(secondName = event.secondName) }
            }
            is StudentsEvent.SortStudents -> {
                _sortType.value = event.sortType
            }
            is StudentsEvent.UpdateStudent -> {
                val studentId = state.value.studentId
                val firstName = state.value.firstName
                val secondName = state.value.secondName
                val age = state.value.age
                if (firstName.isBlank() || secondName.isBlank() || age <= 0 || studentId == -1) {
                    return
                }

                val student = Student(firstName, secondName, age, studentId)
                viewModelScope.launch {
                    dao.updateStudent(student)
                }

                _state.update {
                    it.copy(
                        showLanguagesDialog = false,
                        studentId = -1,
                        firstName = "",
                        secondName = "",
                        age = 0
                    )
                }
            }
            is StudentsEvent.SetStudentId -> {
                _state.update { it.copy(studentId = event.id) }
            }
            is StudentsEvent.ShowLanguagesDialog -> {
                _state.update { it.copy(showLanguagesDialog = true) }
                _state.update { it.copy(studentId = event.studentID) }
            }
            StudentsEvent.HideLanguagesDialog -> {
                _state.update { it.copy(showLanguagesDialog = false) }
            }

            StudentsEvent.HideMarksDialog -> {
                _state.update { it.copy(showMarksDialog = false) }
            }
            StudentsEvent.ShowMarksDialog -> {
                _state.update { it.copy(showMarksDialog = true) }
            }
        }
    }

    fun setLanguages() {
        viewModelScope.launch {
            allLanguages.forEach {
                dao.upsertLanguage(it)
            }
        }
    }

    fun getStudentLanguage(id: Int) {
        viewModelScope.launch {
            dao.getStudentLanguage(id)
        }
        _languages.update { allLanguages }
    }

    fun setMarks() {
        viewModelScope.launch {
            allMarks.forEach { mark ->
                dao.upsertMark(mark)
            }
        }
    }
}