package ua.ilyadreamix.rootinspector.common.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

val NavHostController.route @Composable get() =
    currentBackStackEntryAsState().value?.destination?.route
