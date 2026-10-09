package com.azimut.arapcakelimeogreniyorum.ui.flashcards

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Flip
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.data.local.entity.VocabularyEntity
import com.azimut.arapcakelimeogreniyorum.ui.components.FlashcardIllustration
import com.azimut.arapcakelimeogreniyorum.ui.components.ParchmentCard
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme
import com.azimut.arapcakelimeogreniyorum.ui.theme.EmeraldManuscript
import com.azimut.arapcakelimeogreniyorum.ui.theme.GoldAccent
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentCardBg
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextLight
import com.azimut.arapcakelimeogreniyorum.ui.theme.RubyManuscript

@Composable
fun FlashcardScreen(
    viewModel: FlashcardViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Search Input
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            placeholder = { Text("Kelime veya Anlam Ara...", color = ManuscriptDeepBrown.copy(alpha = 0.6f), fontWeight = FontWeight.Medium) },
            leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null, tint = ManuscriptDeepBrown) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = ParchmentCardBg,
                unfocusedContainerColor = ParchmentCardBg,
                focusedBorderColor = ParchmentGold,
                unfocusedBorderColor = ParchmentBeige,
                focusedTextColor = ManuscriptDeepBrown,
                unfocusedTextColor = ManuscriptDeepBrown
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category Filter Chips Row
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(state.categories) { category ->
                val isSelected = category == state.selectedCategory
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) ManuscriptDeepBrown else ParchmentBeige)
                        .border(1.dp, ParchmentGold, RoundedCornerShape(16.dp))
                        .clickable { viewModel.selectCategory(category) }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = category,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) ParchmentTextLight else ManuscriptDeepBrown
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Progress Indicator Header
        if (state.cards.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Kategori: ${state.selectedCategory}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ManuscriptDeepBrown
                )
                Text(
                    text = "Kart ${state.currentIndex + 1} / ${state.cards.size}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldManuscript
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Parchment Flashcard Canvas Container
            state.currentCard?.let { card ->
                FlashcardItemCard(
                    card = card,
                    isFlipped = state.isFlipped,
                    onFlip = { viewModel.flipCard() },
                    onToggleFavorite = { viewModel.toggleFavorite(card) },
                    onUpdateMastery = { level -> viewModel.updateMasteryLevel(card.id, level) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Controls Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { viewModel.previousCard() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParchmentBeige,
                        contentColor = ManuscriptDeepBrown
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, ParchmentGold)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Önceki Kart",
                        tint = ManuscriptDeepBrown
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Önceki", fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                }

                Button(
                    onClick = { viewModel.flipCard() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ManuscriptDeepBrown,
                        contentColor = ParchmentTextLight
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Flip,
                        contentDescription = "Kartı Çevir",
                        tint = ParchmentTextGold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (state.isFlipped) "Ön Yüz" else "Çevir",
                        fontWeight = FontWeight.Bold,
                        color = ParchmentTextLight
                    )
                }

                Button(
                    onClick = { viewModel.nextCard() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ParchmentBeige,
                        contentColor = ManuscriptDeepBrown
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, ParchmentGold)
                ) {
                    Text("Sonraki", fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = "Sonraki Kart",
                        tint = ManuscriptDeepBrown
                    )
                }
            }
        } else {
            ParchmentCard(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Aramaya veya Kategoriye Uygun Kelime Bulunamadı.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun FlashcardItemCard(
    card: VocabularyEntity,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onToggleFavorite: () -> Unit,
    onUpdateMastery: (Int) -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "CardFlipAnimation"
    )

    ParchmentCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .clickable { onFlip() },
        elevation = 6.dp
    ) {
        if (rotation <= 90f) {
            // FRONT OF CARD
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header Bar inside Card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(ManuscriptDeepBrown, RoundedCornerShape(8.dp))
                            .border(1.dp, ParchmentGold, RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = card.category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ParchmentTextGold
                        )
                    }

                    IconButton(onClick = onToggleFavorite) {
                        Icon(
                            imageVector = if (card.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favori",
                            tint = if (card.isFavorite) RubyManuscript else ManuscriptDeepBrown
                        )
                    }
                }

                // Illustration Image
                FlashcardIllustration(
                    imageName = card.imageResourceName,
                    category = card.category,
                    arabicText = card.arabicText,
                    modifier = Modifier.fillMaxWidth(0.85f)
                )

                // Large Arabic Text
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = card.arabicText,
                        fontFamily = FontFamily.Serif,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "(Anlamını görmek için karta dokunun)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = ManuscriptDeepBrown.copy(alpha = 0.75f),
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .background(ManuscriptDeepBrown, RoundedCornerShape(12.dp))
                        .border(1.dp, ParchmentGold, RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Dokun ve Çevir 🔄",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ParchmentTextLight
                    )
                }
            }
        } else {
            // BACK OF CARD (Flipped)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationY = 180f },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header Bar inside Card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(ManuscriptDeepBrown, RoundedCornerShape(8.dp))
                            .border(1.dp, ParchmentGold, RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = card.category,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ParchmentTextGold
                        )
                    }

                    IconButton(onClick = onToggleFavorite) {
                        Icon(
                            imageVector = if (card.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favori",
                            tint = if (card.isFavorite) RubyManuscript else ManuscriptDeepBrown
                        )
                    }
                }

                // Large Arabic Text
                Text(
                    text = card.arabicText,
                    fontFamily = FontFamily.Serif,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = ManuscriptDeepBrown,
                    textAlign = TextAlign.Center
                )

                // Transliteration & Meaning Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(ParchmentBeige)
                        .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp))
                        .padding(14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Okunuşu: ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown
                            )
                            Text(
                                text = card.transliteration,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldManuscript
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Anlamı: ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown
                            )
                            Text(
                                text = card.turkishMeaning,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown
                            )
                        }

                        card.genderNote?.let { gender ->
                            Text(
                                text = "Not: $gender",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = ManuscriptDeepBrown.copy(alpha = 0.85f)
                            )
                        }

                        card.pluralArabic?.let { plural ->
                            Text(
                                text = "Çoğulu: $plural (${card.pluralTurkish ?: ""})",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown
                            )
                        }
                    }
                }

                // Mastery Rating Section
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "Öğrenme Dereceniz",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        for (star in 1..5) {
                            Icon(
                                imageVector = if (star <= card.masteryLevel) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                                contentDescription = "Star $star",
                                tint = GoldAccent,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { onUpdateMastery(star) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FlashcardScreenPreview() {
    ArapcakelimeogreniyorumTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FlashcardItemCard(
                    card = VocabularyEntity(
                        id = 1,
                        arabicText = "كِتَاب",
                        turkishMeaning = "Kitap",
                        transliteration = "Kitâb",
                        category = "Okul & Eğitim",
                        difficultyLevel = 1,
                        imageResourceName = "book",
                        isFavorite = true,
                        masteryLevel = 3
                    ),
                    isFlipped = false,
                    onFlip = {},
                    onToggleFavorite = {},
                    onUpdateMastery = {}
                )
            }
        }
    }
}
