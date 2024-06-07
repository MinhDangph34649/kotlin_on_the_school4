package com.example.roomdb


import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomdb.room.StudentEntity
import com.example.roomdb.viewmodel.StudentViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: StudentViewModel, navController: NavController) {
    var inputHoten by remember { mutableStateOf("") }
    var inputMssv by remember { mutableStateOf("") }
    var inputDiemTB by remember { mutableStateOf("") }
    var inputDaRaTruong by remember { mutableStateOf("") }
    val emty by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // du lieu sinhviens dung chung giua main va detail screen
    val students by viewModel.students.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Student name",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.background
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null
                )
            }
        }
    ) {
        if (students.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Text(text = "No data")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(students) {
                    Card(
                        onClick = {
                            navController.navigate("${ROUTE_NAME_SCREEN.Detail.name}/${ Uri.encode((it.uid.toString()))}/${Uri.encode(it.hoten)}/${Uri.encode(it.mssv)}/${Uri.encode(it.diemTB.toString())}/${Uri.encode(it.daratruong.toString())}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Text(
                                text = "ID: " + it.uid,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(5.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Họ tên: " + it.hoten,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(5.dp),
                            )
                            Text(
                                text = "MSSV: " + it.mssv,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(5.dp),
                            )
                            Text(
                                text = "Điểm TB: " + it.diemTB,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(5.dp),
                            )
                            Text(
                                text = "Đã Ra Trường: " + it.daratruong,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(5.dp),
                            )
                        }
                    }
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        inputHoten = emty
                        inputMssv = emty
                        inputDiemTB = emty
                        inputDaRaTruong = emty

                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                if (inputHoten.isNotEmpty() && inputMssv.isNotEmpty() && inputDiemTB.isNotEmpty() && inputDaRaTruong.isNotEmpty()) {

                    Button(
                        onClick = {
                            viewModel.addStudent(
                                StudentEntity(
                                    0,
                                    inputHoten,
                                    inputMssv,
                                    inputDiemTB.toFloat(),
                                    inputDaRaTruong.toBoolean()
                                )
                            )
                            showDialog = false
                            inputHoten = emty
                            inputMssv = emty
                            inputDiemTB = emty
                            inputDaRaTruong = emty
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            },
            title = {
                Text(
                    text = "Add Student",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(5.dp)
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = inputHoten,
                        onValueChange = {inputHoten = it},
                        label = {
                            Text(text = "Họ Tên")
                        },
                        placeholder = { Text(text = "Nhập Họ Tên")}
                    )
                    OutlinedTextField(
                        value = inputMssv,
                        onValueChange = {inputMssv = it},
                        label = {
                            Text(text = "MSSV")
                        },
                        placeholder = { Text(text = "Nhập MSSV")}
                    )
                    OutlinedTextField(
                        value = inputDiemTB,
                        onValueChange = {inputDiemTB = it},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(text = "Điểm TB")
                        },
                        placeholder = { Text(text = "Nhập Điểm TB")}
                    )
                    OutlinedTextField(
                        value = inputDaRaTruong,
                        onValueChange = {inputDaRaTruong = it},
                        label = {
                            Text(text = "Đã Ra Trường")
                        },
                        placeholder = { Text(text = "Đã Ra Trường Hay Chưa")}
                    )
                }
            }
        )
    }
}