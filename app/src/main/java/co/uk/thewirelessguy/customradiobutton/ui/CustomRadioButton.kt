package co.uk.thewirelessguy.customradiobutton.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    dotColor: Color = MaterialTheme.colors.secondary,
    radioDotSize: Dp = 12.dp,
    radioButtonSize: Dp = 20.dp,
    radioStrokeWidth: Dp = 2.dp,
    radioButtonPadding: Dp = 2.dp,
    radioButtonRippleRadius: Dp = 24.dp,
    radioAnimationDuration: Int = 100
) {
    val dotRadius by animateDpAsState(
        targetValue = if (selected) radioDotSize / 2 else 0.dp,
        animationSpec = tween(durationMillis = radioAnimationDuration)
    )
    val radioColor by colors.radioColor(enabled, selected)
    val selectableModifier =
        if (onClick != null) {
            Modifier.selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = radioButtonRippleRadius
                )
            )
        } else {
            Modifier
        }
    Canvas(
        modifier
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .padding(radioButtonPadding)
            .requiredSize(radioButtonSize)
    ) {
        drawRadio(radioColor, dotColor, radioButtonSize, dotRadius, radioStrokeWidth)
    }
}

private fun DrawScope.drawRadio(
    color: Color,
    dotColor: Color,
    radioButtonSize: Dp,
    dotRadius: Dp,
    radioStrokeWidth: Dp
) {
    val strokeWidth = radioStrokeWidth.toPx()
    drawCircle(color, (radioButtonSize / 2).toPx() - strokeWidth / 2, style = Stroke(strokeWidth))
    if (dotRadius > 0.dp) {
        drawCircle(dotColor, dotRadius.toPx() - strokeWidth / 2, style = Fill)
    }
}
