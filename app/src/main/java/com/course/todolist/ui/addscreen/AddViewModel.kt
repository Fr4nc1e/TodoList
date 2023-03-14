package com.course.todolist.ui.addscreen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AddViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _todoList = MutableStateFlow<MutableSet<String?>?>(null)

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    init {
        _todoList.value = sharedPreferences.getStringSet("list", emptySet())
    }

    fun input(text: String) {
        _inputText.update { text }
    }

    fun add(): Boolean {
        if (_inputText.value != "") {
            _todoList.value?.add(_inputText.value)
            sharedPreferences.edit().remove("list").apply()
            sharedPreferences.edit().putStringSet("list", _todoList.value).apply()
            return true
        }
        return false
    }
}
