package com.azimut.arapcakelimeogreniyorum.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Chair
import androidx.compose.material.icons.rounded.DoorFront
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FormatListNumbered
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Mosque
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.TableRestaurant
import androidx.compose.material.icons.rounded.Window
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.ui.theme.EmeraldManuscript
import com.azimut.arapcakelimeogreniyorum.ui.theme.GoldAccent
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentContainer
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.RubyManuscript

/**
 * Custom Canvas vector drawing component for Flashcard visual representations in manuscript style.
 */
@Composable
fun FlashcardIllustration(
    imageName: String?,
    category: String?,
    arabicText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ParchmentBeige.copy(alpha = 0.6f))
            .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        val name = imageName?.lowercase() ?: ""
        val cat = category?.lowercase() ?: ""

        when {
            // Mosque / Prayer / Worship
            name.contains("prayer") || cat.contains("ibadet") || name.contains("mosque") -> {
                MosqueIllustration()
            }
            // Book
            name.contains("book") || arabicText.contains("كِتَاب") -> {
                ManuscriptIconIllustration(Icons.Rounded.Book, "كِتَاب", EmeraldManuscript)
            }
            // Pen
            name.contains("pen") || arabicText.contains("قَلَم") -> {
                ManuscriptIconIllustration(Icons.Rounded.Edit, "قَلَم", ManuscriptDeepBrown)
            }
            // House / Door / Furniture / Objects
            name.contains("house") -> {
                ManuscriptIconIllustration(Icons.Rounded.Home, "بَيْت", ManuscriptDeepBrown)
            }
            name.contains("door") -> {
                ManuscriptIconIllustration(Icons.Rounded.DoorFront, "بَاب", ParchmentGold)
            }
            name.contains("table") -> {
                ManuscriptIconIllustration(Icons.Rounded.TableRestaurant, "طَاوِلَة", ManuscriptDeepBrown)
            }
            name.contains("chair") -> {
                ManuscriptIconIllustration(Icons.Rounded.Chair, "كُرْسِيّ", ManuscriptDeepBrown)
            }
            name.contains("window") -> {
                ManuscriptIconIllustration(Icons.Rounded.Window, "نَافِذَة", EmeraldManuscript)
            }
            name.contains("phone") -> {
                ManuscriptIconIllustration(Icons.Rounded.Call, "هَاتِف", ManuscriptDeepBrown)
            }
            name.contains("clock") -> {
                ManuscriptIconIllustration(Icons.Rounded.Schedule, "سَاعَة", GoldAccent)
            }
            // Animals (Cat, Dog, Camel, Lion, Bird, Fish, Elephant)
            name.contains("lion") -> {
                AnimalIllustration("أسَد", "🦁")
            }
            name.contains("cat") -> {
                AnimalIllustration("قِطّ", "🐱")
            }
            name.contains("dog") -> {
                AnimalIllustration("كَلْب", "🐶")
            }
            name.contains("camel") -> {
                AnimalIllustration("جَمَل", "🐪")
            }
            name.contains("bird") -> {
                AnimalIllustration("طَائِر", "🐦")
            }
            name.contains("fish") -> {
                AnimalIllustration("سَمَك", "🐟")
            }
            name.contains("elephant") -> {
                AnimalIllustration("فِيل", "🐘")
            }
            name.contains("pet") || cat.contains("hayvan") -> {
                ManuscriptIconIllustration(Icons.Rounded.Pets, "حَيَوَان", ManuscriptDeepBrown)
            }
            // Numbers
            name.contains("number") || cat.contains("sayı") -> {
                ManuscriptIconIllustration(Icons.Rounded.FormatListNumbered, arabicText, ParchmentGold)
            }
            // Phrases / Greetings
            name.contains("phrase") || cat.contains("selam") || cat.contains("cümle") -> {
                GreetingIllustration(arabicText)
            }
            // Default Manuscript Shamsa motif
            else -> {
                DefaultManuscriptIllustration(arabicText)
            }
        }
    }
}

@Composable
private fun MosqueIllustration() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(120.dp)) {
            val width = size.width
            val height = size.height

            // Base
            drawRect(
                color = ParchmentGold,
                topLeft = Offset(width * 0.2f, height * 0.55f),
                size = Size(width * 0.6f, height * 0.35f)
            )

            // Dome
            val domePath = Path().apply {
                moveTo(width * 0.25f, height * 0.55f)
                cubicTo(
                    width * 0.25f, height * 0.25f,
                    width * 0.75f, height * 0.25f,
                    width * 0.75f, height * 0.55f
                )
                close()
            }
            drawPath(domePath, EmeraldManuscript)

            // Crescent Moon on top of Dome
            drawCircle(
                color = GoldAccent,
                radius = 12f,
                center = Offset(width * 0.5f, height * 0.22f)
            )

            // Left Minaret
            drawRect(
                color = ManuscriptDeepBrown,
                topLeft = Offset(width * 0.1f, height * 0.35f),
                size = Size(width * 0.08f, height * 0.55f)
            )
            // Right Minaret
            drawRect(
                color = ManuscriptDeepBrown,
                topLeft = Offset(width * 0.82f, height * 0.35f),
                size = Size(width * 0.08f, height * 0.55f)
            )

            // Arch Door
            val archPath = Path().apply {
                moveTo(width * 0.42f, height * 0.9f)
                lineTo(width * 0.42f, height * 0.7f)
                cubicTo(
                    width * 0.42f, height * 0.6f,
                    width * 0.58f, height * 0.6f,
                    width * 0.58f, height * 0.7f
                )
                lineTo(width * 0.58f, height * 0.9f)
                close()
            }
            drawPath(archPath, ManuscriptDeepBrown)
        }
    }
}

@Composable
private fun ManuscriptIconIllustration(
    icon: ImageVector,
    labelArabic: String,
    tintColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(ParchmentContainer)
                .border(2.dp, ParchmentGold, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = labelArabic,
                modifier = Modifier.size(40.dp),
                tint = tintColor
            )
        }
    }
}

@Composable
private fun AnimalIllustration(arabicLabel: String, emojiSymbol: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(ParchmentContainer)
                .border(2.dp, ParchmentGold, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emojiSymbol,
                fontSize = 38.sp
            )
        }
    }
}

@Composable
private fun GreetingIllustration(arabicText: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(ParchmentContainer)
                .border(2.dp, ParchmentGold, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Greeting",
                modifier = Modifier.size(42.dp),
                tint = EmeraldManuscript
            )
        }
    }
}

@Composable
private fun DefaultManuscriptIllustration(arabicText: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(100.dp)) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width * 0.4f

            // Outer Star/Rosette Motif (8-Pointed Star)
            val path = Path()
            val points = 8
            for (i in 0 until points * 2) {
                val r = if (i % 2 == 0) radius else radius * 0.6f
                val angle = Math.toRadians((i * 360.0 / (points * 2)).toDouble())
                val x = (center.x + r * Math.cos(angle)).toFloat()
                val y = (center.y + r * Math.sin(angle)).toFloat()
                if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            path.close()

            drawPath(path, color = ParchmentGold.copy(alpha = 0.3f))
            drawPath(path, color = ParchmentGold, style = Stroke(width = 2.dp.toPx()))

            drawCircle(
                color = RubyManuscript,
                radius = radius * 0.35f,
                center = center
            )
        }
        Icon(
            imageVector = Icons.Rounded.AutoAwesome,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier.size(24.dp)
        )
    }
}
