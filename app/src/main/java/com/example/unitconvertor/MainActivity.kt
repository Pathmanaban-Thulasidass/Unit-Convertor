package com.example.unitconvertor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import kotlin.math.roundToInt



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConvertor();
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun UnitConvertor(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor =  remember { mutableStateOf(1.0) }

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0;
        val result = (inputValueDouble * (conversionFactor.value).toDouble() * 100.0 / oConversionFactor.value.toDouble()).roundToInt() / 100.0
        outputValue = result.toString();
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Unit Convertor", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit() },
            label = {
                Text(text = "Enter the Number ")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                // InputBox
                Button(onClick = { iExpanded = true }){
                    //  Input Button
                    Text(text = inputUnit)
                    Icon(imageVector = Icons.Default.KeyboardArrowDown,contentDescription = null)
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter")},
                        onClick = {
                            inputUnit = "Centimeter"
                            iExpanded = false
                            conversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter")},
                        onClick = {
                            inputUnit = "Meter"
                            iExpanded = false
                            conversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            inputUnit = "Feet"
                            iExpanded = false
                            conversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeter")},
                        onClick = {
                            inputUnit = "Millimeter"
                            iExpanded = false
                            conversionFactor.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp));
            Box {
                // OutputBox
                Button(onClick = { oExpanded = true}) {
                    // OutputButton
                    Text(text = outputUnit)
                    Icon(Icons.Default.KeyboardArrowDown,contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter")},
                        onClick = {
                            outputUnit = "Centimeter"
                            oExpanded = false
                            oConversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter")},
                        onClick = {
                            outputUnit = "Meter"
                            oExpanded = false
                            oConversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            outputUnit = "Feet"
                            oExpanded = false
                            oConversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeter")},
                        onClick = {
                            outputUnit = "Millimeter"
                            oExpanded = false
                            oConversionFactor.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : $outputValue $outputUnit" ,style = MaterialTheme.typography.headlineMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConvertorPreview(){
    UnitConvertor();
}