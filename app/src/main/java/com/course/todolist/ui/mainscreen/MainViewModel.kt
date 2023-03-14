package com.course.todolist.ui.mainscreen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _todoList = MutableStateFlow<MutableSet<String?>?>(null)
    val todoList = _todoList.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    init {
        _todoList.value = sharedPreferences.getStringSet("list", emptySet())
    }

    fun showDialog() {
        _showDialog.update { !it }
    }

    fun deleteItem(item: String) {
        _todoList.update { set ->
            set?.let {
                it.remove(item)
                set
            }
        }
        sharedPreferences.edit().remove("list").apply()
        sharedPreferences.edit().putStringSet("list", _todoList.value).apply()
    }
}
