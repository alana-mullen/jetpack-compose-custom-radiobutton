package co.uk.thewirelessguy.customradiobutton.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.uk.thewirelessguy.customradiobutton.extension.toast
import co.uk.thewirelessguy.customradiobutton.ui.component.CustomRadioButton
import co.uk.thewirelessguy.customradiobutton.ui.theme.CustomRadioButtonTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomRadioButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CustomRadioButtonDemo()
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomRadioButtonDemo() {
    val radioOptions = listOf("Female", "Male", "Other", "Prefer not to say")
    val selectedOption = remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select Gender", fontSize = 24.sp)
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            radioOptions.forEach { option ->
                Row(
                    Modifier
                        .padding(bottom = 10.dp)
                        .wrapContentSize()
                        .selectable(
                            selected = selectedOption.value == option,
                            onClick = {
                                selectedOption.value = option
                                context.toast("$selectedOption selected")
                                Timber.d("$selectedOption selected")
                            }
                        ),
                ) {
                    CustomRadioButton(
                        colors = RadioButtonDefaults.colors(
                            Color.White, Color.DarkGray),
                        dotColor = Color.Magenta,
                        selected = selectedOption.value == option,
                        radioDotSize = 24.dp,
                        radioButtonSize = 40.dp,
                        onClick = {
                            selectedOption.value = option
                            context.toast("$selectedOption selected")
                            Timber.d("$selectedOption selected")
                        })
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        option,
                        modifier = Modifier.align(alignment = Alignment.CenterVertically),
                        fontSize = 24.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomRadioButtonTheme {
        CustomRadioButtonDemo()
    }
}