package com.course.todolist.ui.addscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.course.todolist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onNavigateUp: () -> Unit,
    viewModel: AddViewModel = hiltViewModel()
) {
    val inputText = viewModel.inputText.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = stringResource(R.string.to_do_list)) }
        )

        Text(
            text = stringResource(R.string.add_an_item_here),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = inputText,
            onValueChange = {
                viewModel.input(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                when (viewModel.add()) {
                    true -> { onNavigateUp() }
                    false -> {}
                }
            }
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}
