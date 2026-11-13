package com.azimut.arapcakelimeogreniyorum.ui.components

import android.content.res.Configuration
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
import androidx.compose.material.icons.automirrored.rounded.AltRoute
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.AltRoute
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Backpack
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Bathroom
import androidx.compose.material.icons.rounded.Bed
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Chair
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.DoorFront
import androidx.compose.material.icons.rounded.Dry
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.FormatListNumbered
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Mosque
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Soap
import androidx.compose.material.icons.rounded.TableRestaurant
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.rounded.Wc
import androidx.compose.material.icons.rounded.Window
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme
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
            name.contains("prayer") || cat.contains("ibadet") || cat.contains("dini") || name.contains("mosque") -> {
                MosqueIllustration()
            }

            // Family Members
            cat.contains("aile") || name.contains("family") -> {
                ManuscriptIconIllustration(Icons.Rounded.People, "عَائِلَة", EmeraldManuscript)
            }

            // Weather
            cat.contains("hava") || name.contains("weather") -> {
                when {
                    arabicText.contains("شَمْس") -> ManuscriptIconIllustration(Icons.Rounded.WbSunny, "شَمْس", GoldAccent)
                    arabicText.contains("مَطَر") -> ManuscriptIconIllustration(Icons.Rounded.WaterDrop, "مَطَر", EmeraldManuscript)
                    arabicText.contains("ثَلْج") -> ManuscriptIconIllustration(Icons.Rounded.AcUnit, "ثَلْج", GoldAccent)
                    arabicText.contains("سَحَاب") || arabicText.contains("غَائِم") -> ManuscriptIconIllustration(Icons.Rounded.Cloud, "سَحَاب", ManuscriptDeepBrown)
                    arabicText.contains("رِيح") -> ManuscriptIconIllustration(Icons.Rounded.Air, "رِيح", EmeraldManuscript)
                    else -> ManuscriptIconIllustration(Icons.Rounded.Thermostat, "طَقْس", GoldAccent)
                }
            }

            // School & Education
            cat.contains("okul") || cat.contains("eğitim") || name.contains("school") -> {
                when {
                    arabicText.contains("كِتَاب") || arabicText.contains("دَفْتَر") || arabicText.contains("مَكْتَبَة") ->
                        ManuscriptIconIllustration(Icons.AutoMirrored.Rounded.MenuBook, arabicText, EmeraldManuscript)
                    arabicText.contains("قَلَم") -> ManuscriptIconIllustration(Icons.Rounded.Edit, "قَلَم", ManuscriptDeepBrown)
                    arabicText.contains("حَقِيبَة") -> ManuscriptIconIllustration(Icons.Rounded.Backpack, "حَقِيبَة", ManuscriptDeepBrown)
                    else -> ManuscriptIconIllustration(Icons.Rounded.School, "مَدْرَسَة", EmeraldManuscript)
                }
            }

            // Restroom & Daily Needs
            cat.contains("temizlik") || cat.contains("ihtiyaç") || cat.contains("tuvalet") || name.contains("restroom") -> {
                when {
                    arabicText.contains("حَمَّام") -> ManuscriptIconIllustration(Icons.Rounded.Wc, "حَمَّام", ManuscriptDeepBrown)
                    arabicText.contains("مَاء") -> ManuscriptIconIllustration(Icons.Rounded.WaterDrop, "مَاء", EmeraldManuscript)
                    arabicText.contains("صَابُون") -> ManuscriptIconIllustration(Icons.Rounded.Soap, "صَابُون", GoldAccent)
                    arabicText.contains("مِنْشَفَة") -> ManuscriptIconIllustration(Icons.Rounded.Dry, "مِنْشَفَة", ManuscriptDeepBrown)
                    else -> ManuscriptIconIllustration(Icons.Rounded.Bathroom, arabicText, EmeraldManuscript)
                }
            }

            // Prepositions
            cat.contains("edat") || name.contains("prep") -> {
                ManuscriptIconIllustration(Icons.AutoMirrored.Rounded.AltRoute, "حَرْفُ جَرّ", GoldAccent)
            }

            // Pronouns
            cat.contains("zamir") || name.contains("pronoun") -> {
                if (cat.contains("işaret")) {
                    ManuscriptIconIllustration(Icons.Rounded.TouchApp, arabicText, GoldAccent)
                } else {
                    ManuscriptIconIllustration(Icons.Rounded.Person, arabicText, EmeraldManuscript)
                }
            }

            // Ages & Personal Details
            cat.contains("yaş") || cat.contains("kişisel") || name.contains("age") -> {
                ManuscriptIconIllustration(Icons.Rounded.Badge, arabicText, GoldAccent)
            }

            // Numbers
            name.contains("number") || cat.contains("sayı") -> {
                ManuscriptIconIllustration(Icons.Rounded.FormatListNumbered, arabicText, ParchmentGold)
            }

            // House & Objects
            cat.contains("ev") || cat.contains("eşya") || name.contains("house") || name.contains("door") || name.contains("table") || name.contains("chair") || name.contains("window") || name.contains("phone") -> {
                when {
                    name.contains("door") || arabicText.contains("بَاب") -> ManuscriptIconIllustration(Icons.Rounded.DoorFront, "بَاب", ParchmentGold)
                    name.contains("window") || arabicText.contains("نَافِذَة") -> ManuscriptIconIllustration(Icons.Rounded.Window, "نَافِذَة", EmeraldManuscript)
                    name.contains("table") || arabicText.contains("طَاوِلَة") -> ManuscriptIconIllustration(Icons.Rounded.TableRestaurant, "طَاوِلَة", ManuscriptDeepBrown)
                    name.contains("chair") || arabicText.contains("كُرْسِيّ") -> ManuscriptIconIllustration(Icons.Rounded.Chair, "كُرْسِيّ", ManuscriptDeepBrown)
                    name.contains("phone") || arabicText.contains("هَاتِف") -> ManuscriptIconIllustration(Icons.Rounded.Call, "هَاتِف", ManuscriptDeepBrown)
                    arabicText.contains("سَرِير") -> ManuscriptIconIllustration(Icons.Rounded.Bed, "سَرِير", ManuscriptDeepBrown)
                    else -> ManuscriptIconIllustration(Icons.Rounded.Home, "بَيْت", ManuscriptDeepBrown)
                }
            }

            // Animals
            name.contains("lion") || arabicText.contains("أَسَد") -> AnimalIllustration("أسَد", "🦁")
            name.contains("cat") || arabicText.contains("قِطّ") -> AnimalIllustration("قِطّ", "🐱")
            name.contains("dog") || arabicText.contains("كَلْب") -> AnimalIllustration("كَلْب", "🐶")
            name.contains("camel") || arabicText.contains("جَمَل") -> AnimalIllustration("جَمَل", "🐪")
            name.contains("bird") || arabicText.contains("طَائِر") -> AnimalIllustration("طَائِر", "🐦")
            name.contains("fish") || arabicText.contains("سَمَك") -> AnimalIllustration("سَمَك", "🐟")
            name.contains("elephant") || arabicText.contains("فِيل") -> AnimalIllustration("فِيل", "🐘")
            name.contains("horse") || arabicText.contains("حِصَان") -> AnimalIllustration("حِصَان", "🐎")
            name.contains("pet") || cat.contains("hayvan") -> ManuscriptIconIllustration(Icons.Rounded.Pets, "حَيَوَان", ManuscriptDeepBrown)

            // Phrases / Greetings
            name.contains("phrase") || cat.contains("selam") || cat.contains("cümle") || cat.contains("günlük") -> {
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

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FlashcardIllustrationPreview() {
    ArapcakelimeogreniyorumTheme {
        Surface {
            FlashcardIllustration(
                imageName = "book",
                category = "Okul & Eğitim",
                arabicText = "كِتَاب",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
