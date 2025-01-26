package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaiful.mynote.presentation.viewmodels.NoteViewmodel
import com.shaiful.mynote.presentation.widgets.notes.CategoryCreationDialog
import com.shaiful.mynote.presentation.widgets.MenuBottomSheet
import com.shaiful.mynote.presentation.widgets.notes.NoteCategoryListWidget
import com.shaiful.mynote.presentation.widgets.ThemeToggleButton
import com.shaiful.mynote.presentation.widgets.habit.GoToHabitTrackerButton
import com.shaiful.mynote.presentation.widgets.notes.UsernameInputDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onThemeChange: (Boolean) -> Unit,
    isDarkTheme: Boolean,
    noteViewModel: NoteViewmodel = hiltViewModel()
) {
    val userViewModel: UserViewModel = UserViewModel(context = LocalContext.current)

    /// user name input related fields
    val userName by userViewModel.userName.collectAsState()
    val showUsernameDialog by remember {
        derivedStateOf { userName == null }
    }

    /// note group related fields
    var showCategoryCreationDialog by remember {
        mutableStateOf(false)
    }

    /// bottom sheet related fields
    var showMenuBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        showMenuBottomSheet = true
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu button")
                    }
                },
                title = { Text(text = "Hi, ${userName ?: "There"}") },
                actions = {
                    ThemeToggleButton(
                        initialThemeIsDark = isDarkTheme,
                        onThemeChange = onThemeChange
                    )
                },
            )
        },
    ) { innerPadding ->

        if (showUsernameDialog) {
            UsernameInputDialog(
                onSave = { name ->
                    userViewModel.setUserName(name)
                }
            )
        }

        if (showCategoryCreationDialog) {
            CategoryCreationDialog(
                onSave = { categoryName ->
                    noteViewModel.addCategory(categoryName)
                    showCategoryCreationDialog = false
                },
                onDismiss = {
                    showCategoryCreationDialog = false
                }
            )
        }

        if (showMenuBottomSheet) {
            MenuBottomSheet (
                onDismiss = {
                    showMenuBottomSheet = false
                },
                navController = navController
            )
        }

        Column (
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            GoToHabitTrackerButton(navController = navController)
            Box (
                modifier = Modifier.weight(1F)
            ) {
                NoteCategoryListWidget(
                    innerPadding = innerPadding,
                    isDarkTheme = isDarkTheme,
                    noteViewModel = noteViewModel,
                    onAddCategoryBtnClick = {
                        showCategoryCreationDialog = true
                    },
                )
            }
        }
    }
}