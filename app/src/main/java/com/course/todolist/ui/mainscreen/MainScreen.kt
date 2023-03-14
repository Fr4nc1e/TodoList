package com.course.todolist.ui.mainscreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.course.todolist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigation: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val todoList = viewModel.todoList.collectAsState().value
    val showDialog = viewModel.showDialog.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.showDialog() },
                confirmButton = {
                    TextButton(onClick = { }) {
                        Text(text = stringResource(R.string.yes))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.showDialog() }) {
                        Text(text = stringResource(R.string.no))
                    }
                },
                title = { Text(text = stringResource(R.string.are_you_sure)) },
                text = { Text(text = stringResource(R.string.do_you_want_to_delete_this_task)) }
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text(text = stringResource(R.string.to_do_list)) },
                actions = {
                    TextButton(onClick = { onNavigation("add_screen") }) {
                        Text(text = stringResource(R.string.add))
                    }
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp)
            ) {
                todoList?.let { list ->
                    items(list.toList()) { item ->
                        item?.let { text ->
                            Text(
                                text = text,
                                modifier = Modifier.pointerInput(Unit) {
                                    detectTapGestures(
                                        onLongPress = {
                                            viewModel.deleteItem(text)
                                        }
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
