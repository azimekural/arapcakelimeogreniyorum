package com.azimut.arapcakelimeogreniyorum.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGoldDark

/**
 * Modifier that draws an ornate manuscript double border with corner filigree flourish accents.
 */
fun Modifier.ornateDoubleBorder(
    outerBorderColor: Color = ParchmentGold,
    innerBorderColor: Color = ManuscriptDeepBrown,
    cornerAccentColor: Color = ParchmentGoldDark,
    outerWidth: Dp = 2.dp,
    innerWidth: Dp = 1.dp,
    borderGap: Dp = 4.dp,
): Modifier = this.then(
    Modifier.drawBehind {
        val outerStroke = outerWidth.toPx()
        val innerStroke = innerWidth.toPx()
        val gap = borderGap.toPx()

        // Draw Outer Border
        drawRect(
            color = outerBorderColor,
            topLeft = Offset(outerStroke / 2, outerStroke / 2),
            size = Size(size.width - outerStroke, size.height - outerStroke),
            style = Stroke(width = outerStroke),
        )

        // Draw Inner Border
        val innerLeft = outerStroke + gap
        val innerTop = outerStroke + gap
        val innerWidthPx = size.width - (2 * innerLeft)
        val innerHeightPx = size.height - (2 * innerTop)

        if (innerWidthPx > 0 && innerHeightPx > 0) {
            drawRect(
                color = innerBorderColor,
                topLeft = Offset(innerLeft, innerTop),
                size = Size(innerWidthPx, innerHeightPx),
                style = Stroke(width = innerStroke),
            )

            // Draw Corner Flourish / Filigree Accents
            val cornerSize = 14.dp.toPx()
            val strokeWidth = 1.5.dp.toPx()

            // Top-Left Corner Ornament
            val pathTL = Path().apply {
                moveTo(innerLeft, innerTop + cornerSize)
                lineTo(innerLeft + cornerSize, innerTop)
                moveTo(innerLeft, innerTop + cornerSize / 2)
                lineTo(innerLeft + cornerSize / 2, innerTop)
            }
            drawPath(pathTL, cornerAccentColor, style = Stroke(width = strokeWidth))

            // Top-Right Corner Ornament
            val pathTR = Path().apply {
                moveTo(innerLeft + innerWidthPx - cornerSize, innerTop)
                lineTo(innerLeft + innerWidthPx, innerTop + cornerSize)
                moveTo(innerLeft + innerWidthPx - cornerSize / 2, innerTop)
                lineTo(innerLeft + innerWidthPx, innerTop + cornerSize / 2)
            }
            drawPath(pathTR, cornerAccentColor, style = Stroke(width = strokeWidth))

            // Bottom-Left Corner Ornament
            val pathBL = Path().apply {
                moveTo(innerLeft, innerTop + innerHeightPx - cornerSize)
                lineTo(innerLeft + cornerSize, innerTop + innerHeightPx)
                moveTo(innerLeft, innerTop + innerHeightPx - cornerSize / 2)
                lineTo(innerLeft + cornerSize / 2, innerTop + innerHeightPx)
            }
            drawPath(pathBL, cornerAccentColor, style = Stroke(width = strokeWidth))

            // Bottom-Right Corner Ornament
            val pathBR = Path().apply {
                moveTo(innerLeft + innerWidthPx - cornerSize, innerTop + innerHeightPx)
                lineTo(innerLeft + cornerSize, innerTop + innerHeightPx - cornerSize)
                moveTo(innerLeft + innerWidthPx - cornerSize / 2, innerTop + innerHeightPx)
                lineTo(innerLeft + innerWidthPx, innerTop + innerHeightPx - cornerSize / 2)
            }
            drawPath(pathBR, cornerAccentColor, style = Stroke(width = strokeWidth))
        }
    }
)

/**
 * Custom Parchment Card Container with ornate frame double border styling.
 */
@Composable
fun ParchmentCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 4.dp,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .ornateDoubleBorder()
                .padding(16.dp),
            content = content,
        )
    }
}

/**
 * Ornate Frame Box for highlighting text, headers, or cards.
 */
@Composable
fun OrnateFrameBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = ParchmentBeige.copy(alpha = 0.5f),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(1.dp, ParchmentGold, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        content = content,
    )
}

@Preview(showBackground = true, name = "Parchment Card Preview")
@Composable
fun ParchmentCardPreview() {
    ArapcakelimeogreniyorumTheme {
        ParchmentCard(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Manuscript Parchment Card Content",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ManuscriptDeepBrown
            )
        }
    }
}
