package com.azimut.arapcakelimeogreniyorum.ui.alphabet

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.azimut.arapcakelimeogreniyorum.data.local.entity.AlphabetEntity
import com.azimut.arapcakelimeogreniyorum.data.local.entity.DiacriticEntity
import com.azimut.arapcakelimeogreniyorum.ui.components.ParchmentCard
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme
import com.azimut.arapcakelimeogreniyorum.ui.theme.EmeraldManuscript
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentCardBg
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentCardBgDark
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentContainerDark
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGoldDark
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextLight

@Composable
fun AlphabetGuideScreen(
    viewModel: AlphabetViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val isDark = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Search & Tab Header Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = {
                    Text(
                        "Harf veya Hareke Ara...",
                        color = if (isDark) ParchmentTextLight.copy(alpha = 0.6f) else ManuscriptDeepBrown.copy(alpha = 0.6f),
                        fontWeight = FontWeight.Medium
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = null,
                        tint = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = if (isDark) ParchmentCardBgDark else ParchmentCardBg,
                    unfocusedContainerColor = if (isDark) ParchmentCardBgDark else ParchmentCardBg,
                    focusedBorderColor = ParchmentGold,
                    unfocusedBorderColor = if (isDark) ParchmentGoldDark else ParchmentBeige,
                    focusedTextColor = if (isDark) ParchmentTextLight else ManuscriptDeepBrown,
                    unfocusedTextColor = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                ),
                shape = RoundedCornerShape(10.dp)
            )

            // Tab Row Switcher
            TabRow(
                selectedTabIndex = state.selectedTab.ordinal,
                containerColor = if (isDark) ParchmentContainerDark else ParchmentBeige,
                contentColor = if (isDark) ParchmentTextLight else ManuscriptDeepBrown,
                indicator = { tabPositions ->
                    if (state.selectedTab.ordinal < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[state.selectedTab.ordinal]),
                            color = if (isDark) ParchmentTextGold else ManuscriptDeepBrown,
                            height = 3.dp
                        )
                    }
                }
            ) {
                Tab(
                    selected = state.selectedTab == AlphabetTab.LETTERS,
                    onClick = { viewModel.selectTab(AlphabetTab.LETTERS) },
                    text = {
                        Text(
                            text = "Elif-Ba Harfleri (28)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = if (state.selectedTab == AlphabetTab.LETTERS) {
                                if (isDark) ParchmentTextGold else ManuscriptDeepBrown
                            } else {
                                if (isDark) ParchmentTextLight.copy(alpha = 0.7f) else ManuscriptDeepBrown.copy(alpha = 0.7f)
                            }
                        )
                    }
                )
                Tab(
                    selected = state.selectedTab == AlphabetTab.DIACRITICS,
                    onClick = { viewModel.selectTab(AlphabetTab.DIACRITICS) },
                    text = {
                        Text(
                            text = "Harekeler",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = if (state.selectedTab == AlphabetTab.DIACRITICS) {
                                if (isDark) ParchmentTextGold else ManuscriptDeepBrown
                            } else {
                                if (isDark) ParchmentTextLight.copy(alpha = 0.7f) else ManuscriptDeepBrown.copy(alpha = 0.7f)
                            }
                        )
                    }
                )
            }
        }

        // Content Area
        when (state.selectedTab) {
            AlphabetTab.LETTERS -> {
                AlphabetGridSection(
                    letters = state.letters,
                    onLetterClick = { viewModel.selectLetter(it) }
                )
            }
            AlphabetTab.DIACRITICS -> {
                DiacriticsListSection(
                    diacritics = state.diacritics,
                    onDiacriticClick = { viewModel.selectDiacritic(it) }
                )
            }
        }
    }

    // Letter Detail Dialog
    state.selectedLetter?.let { letter ->
        LetterDetailDialog(
            letter = letter,
            onDismiss = { viewModel.selectLetter(null) },
            onMarkReviewed = {
                viewModel.markLetterReviewed(letter.id)
                viewModel.selectLetter(null)
            }
        )
    }

    // Diacritic Detail Dialog
    state.selectedDiacritic?.let { diacritic ->
        DiacriticDetailDialog(
            diacritic = diacritic,
            onDismiss = { viewModel.selectDiacritic(null) }
        )
    }
}

@Composable
private fun AlphabetGridSection(
    letters: List<AlphabetEntity>,
    onLetterClick: (AlphabetEntity) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(letters, key = { it.id }) { letter ->
            LetterGridCard(
                letter = letter,
                onClick = { onLetterClick(letter) }
            )
        }
    }
}

@Composable
private fun LetterGridCard(
    letter: AlphabetEntity,
    onClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    Card(
        modifier = Modifier
            .aspectRatio(0.85f)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isDark) ParchmentCardBgDark else ParchmentCardBg
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(if (isDark) ParchmentContainerDark else ManuscriptDeepBrown)
                    .border(1.dp, ParchmentGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${letter.orderIndex}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ParchmentTextLight
                )
            }

            Text(
                text = letter.letterArabic,
                fontFamily = FontFamily.Serif,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
            )

            Text(
                text = letter.nameTurkish,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
            )

            Text(
                text = letter.transliteration,
                fontSize = 11.sp,
                color = if (isDark) ParchmentTextGold else EmeraldManuscript,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DiacriticsListSection(
    diacritics: List<DiacriticEntity>,
    onDiacriticClick: (DiacriticEntity) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(diacritics, key = { it.id }) { diacritic ->
            DiacriticListCard(
                diacritic = diacritic,
                onClick = { onDiacriticClick(diacritic) }
            )
        }
    }
}

@Composable
private fun DiacriticListCard(
    diacritic: DiacriticEntity,
    onClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isDark) ParchmentCardBgDark else ParchmentCardBg
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(if (isDark) ParchmentContainerDark else ManuscriptDeepBrown)
                    .border(1.5.dp, ParchmentGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = diacritic.symbol,
                    fontFamily = FontFamily.Serif,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = ParchmentTextLight
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = diacritic.nameTurkish,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                )
                Text(
                    text = diacritic.explanation,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isDark) ParchmentTextLight.copy(alpha = 0.85f) else ManuscriptDeepBrown.copy(alpha = 0.85f),
                    maxLines = 2,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Örnek: ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) ParchmentTextGold else ManuscriptDeepBrown
                    )
                    Text(
                        text = "${diacritic.exampleWordArabic} (${diacritic.exampleWordTurkish})",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) ParchmentTextGold else EmeraldManuscript,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}

@Composable
private fun LetterDetailDialog(
    letter: AlphabetEntity,
    onDismiss: () -> Unit,
    onMarkReviewed: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismiss) {
        LetterDetailCardContent(
            letter = letter,
            onDismiss = onDismiss,
            onMarkReviewed = onMarkReviewed
        )
    }
}

@Composable
fun LetterDetailCardContent(
    letter: AlphabetEntity,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onMarkReviewed: () -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()

    ParchmentCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        backgroundColor = if (isDark) ParchmentCardBgDark else ParchmentCardBg
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${letter.orderIndex}. Harf: ${letter.nameTurkish}",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "Kapat",
                        tint = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                    )
                }
            }

            // Large Letter Display
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(if (isDark) ParchmentContainerDark else ManuscriptDeepBrown)
                    .border(2.dp, ParchmentGold, CircleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = letter.letterArabic,
                    fontFamily = FontFamily.Serif,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = ParchmentTextLight
                )
            }

            Text(
                text = "Okunuş Transkripsiyonu: ${letter.transliteration}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) ParchmentTextGold else EmeraldManuscript,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Letter Forms Table
            Text(
                text = "Harfin Kelimedeki Yazılış Formları",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) ParchmentTextGold else ManuscriptDeepBrown,
                modifier = Modifier.padding(top = 16.dp, bottom = 6.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isDark) ParchmentContainerDark else ParchmentBeige)
                    .border(1.dp, ParchmentGold, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                FormColumn("Yalın", letter.isolatedForm)
                FormColumn("Başta", letter.initialForm)
                FormColumn("Ortada", letter.medialForm)
                FormColumn("Sonda", letter.finalForm)
            }

            // Description
            Text(
                text = letter.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown,
                modifier = Modifier.padding(top = 12.dp)
            )

            // Example Word Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isDark) ParchmentContainerDark else ParchmentBeige)
                    .border(1.dp, ParchmentGold, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Örnek Kelime",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isDark) ParchmentTextGold else ManuscriptDeepBrown.copy(alpha = 0.8f)
                        )
                        Text(
                            text = letter.exampleWordTurkish,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                        )
                    }
                    Text(
                        text = letter.exampleWordArabic,
                        fontFamily = FontFamily.Serif,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                    )
                }
            }
        }
    }
}

@Composable
private fun FormColumn(label: String, formText: String) {
    val isDark = isSystemInDarkTheme()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDark) ParchmentTextGold else ManuscriptDeepBrown.copy(alpha = 0.8f)
        )
        Text(
            text = formText,
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if (isDark) ParchmentTextLight else EmeraldManuscript
        )
    }
}

@Composable
private fun DiacriticDetailDialog(
    diacritic: DiacriticEntity,
    onDismiss: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    Dialog(onDismissRequest = onDismiss) {
        ParchmentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            backgroundColor = if (isDark) ParchmentCardBgDark else ParchmentCardBg
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = diacritic.nameTurkish,
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = "Kapat",
                            tint = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(if (isDark) ParchmentContainerDark else ManuscriptDeepBrown)
                        .border(2.dp, ParchmentGold, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = diacritic.symbol,
                        fontFamily = FontFamily.Serif,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = ParchmentTextLight
                    )
                }

                Text(
                    text = diacritic.explanation,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown,
                    modifier = Modifier.padding(top = 12.dp)
                )

                // Example Word Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isDark) ParchmentContainerDark else ParchmentBeige)
                        .border(1.dp, ParchmentGold, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Örnek Kelime",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isDark) ParchmentTextGold else ManuscriptDeepBrown.copy(alpha = 0.8f)
                            )
                            Text(
                                text = diacritic.exampleWordTurkish,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isDark) ParchmentTextLight else ManuscriptDeepBrown
                            )
                        }
                        Text(
                            text = diacritic.exampleWordArabic,
                            fontFamily = FontFamily.Serif,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isDark) ParchmentTextLight else EmeraldManuscript
                        )
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
fun AlphabetGuideScreenPreview() {
    ArapcakelimeogreniyorumTheme {
        Surface {
            val sampleLetters = listOf(
                AlphabetEntity(
                    id = 1,
                    orderIndex = 1,
                    letterArabic = "أ",
                    nameTurkish = "Elif",
                    transliteration = "Alif",
                    isolatedForm = "أ",
                    initialForm = "أ",
                    medialForm = "ـأ",
                    finalForm = "ـأ",
                    description = "Elif harfi boğazdan çıkar.",
                    exampleWordArabic = "أَرْنَب",
                    exampleWordTurkish = "Tavşan",
                    audioResName = "elif"
                ),
                AlphabetEntity(
                    id = 2,
                    orderIndex = 2,
                    letterArabic = "ب",
                    nameTurkish = "Be",
                    transliteration = "Bâ",
                    isolatedForm = "ب",
                    initialForm = "بـ",
                    medialForm = "ـبـ",
                    finalForm = "ـب",
                    description = "Dudak harfidir.",
                    exampleWordArabic = "بَيْت",
                    exampleWordTurkish = "Ev",
                    audioResName = "be"
                )
            )
            AlphabetGridSection(
                letters = sampleLetters,
                onLetterClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Letter Detail Dialog - Light")
@Preview(
    showBackground = true,
    name = "Letter Detail Dialog - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LetterDetailDialogPreview() {
    ArapcakelimeogreniyorumTheme {
        Surface {
            val sampleLetter = AlphabetEntity(
                id = 1,
                orderIndex = 1,
                letterArabic = "ا",
                nameTurkish = "Elif",
                transliteration = "Elif",
                isolatedForm = "ا",
                initialForm = "أَ",
                medialForm = "إِ",
                finalForm = "ـا",
                description = "Boğazın alt kısmından çıkarılan dik ve düz harf",
                exampleWordArabic = "أَكَلَ (Ekele)",
                exampleWordTurkish = "Yedi",
                audioResName = "elif"
            )
            LetterDetailCardContent(
                letter = sampleLetter,
                onDismiss = {}
            )
        }
    }
}
