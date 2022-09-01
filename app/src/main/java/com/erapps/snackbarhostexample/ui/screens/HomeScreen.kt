package com.erapps.snackbarhostexample.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        delay(5000)
        snackBarHostState.showSnackbar(
            message = "Delay",
            duration = SnackbarDuration.Short,
            actionLabel = "Hide"
        )
    }

    Scaffold(
        modifier = modifier,
        scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState),
        snackbarHost = {
            SnackBarContent(snackbarHostState = it)
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            actionLabel = "Hide",
                            message = "Klk Snack",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text(text = "showSnack")
            }
        }
    }
}

@Composable
fun SnackBarContent(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        modifier = Modifier.padding(16.dp),
        hostState = snackbarHostState
    ) { data ->
        Snackbar(
            action = {
                TextButton(
                    onClick = { snackbarHostState.currentSnackbarData?.dismiss() },
                ) {
                    Text(text = data.actionLabel.toString(), color = SnackbarDefaults.primaryActionColor)
                }
            }
        ) {
            Text(text = data.message)
        }
    }
}