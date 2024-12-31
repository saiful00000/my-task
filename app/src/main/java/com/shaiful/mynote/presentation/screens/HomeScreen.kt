package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.shaiful.mynote.presentation.viewmodels.UserViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shaiful.mynote.presentation.widgets.AddNoteBottomSheet
import com.shaiful.mynote.presentation.widgets.NoteCategoryListWidget
import com.shaiful.mynote.presentation.widgets.NoteListWidget
import com.shaiful.mynote.presentation.widgets.ThemeToggleButton
import com.shaiful.mynote.presentation.widgets.UsernameInputDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, onThemeChange: (Boolean) -> Unit, isDarkTheme: Boolean) {
    val userViewModel: UserViewModel = UserViewModel(context = LocalContext.current)

    /// user name input related fields
    val userName by userViewModel.userName.collectAsState()
    val showDialog by remember {
        derivedStateOf { userName == null }
    }

    /// note group related fields
    var showGroupCreationDialog by remember {
        mutableStateOf(false)
    }

    /// note creation related fields
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Hi, ${userName ?: "There"}") },
                actions = {
                    ThemeToggleButton(
                        initialThemeIsDark = isDarkTheme,
                        onThemeChange = onThemeChange
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.padding(end = 4.dp),
                )
            }
        }
    ) { innerPadding ->

        if (showDialog) {
            UsernameInputDialog(
                onSave = { name ->
                    userViewModel.setUserName(name)
                }
            )
        }

        if (showSheet) {
            AddNoteBottomSheet(
                sheetTitle = "Group Name",
                onSave = { noteItem ->
                    println("Note item = $noteItem")
                    showSheet = false
                },
                onDismiss = {
                    showSheet = false
                }
            )
        }

        NoteCategoryListWidget(innerPadding = innerPadding)
    }
}