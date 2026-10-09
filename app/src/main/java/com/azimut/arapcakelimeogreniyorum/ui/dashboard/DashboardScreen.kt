package com.azimut.arapcakelimeogreniyorum.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.MenuBook
import androidx.compose.material.icons.rounded.Assessment
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.Style
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.ui.components.ParchmentCard
import com.azimut.arapcakelimeogreniyorum.ui.navigation.AppBottomTab
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme
import com.azimut.arapcakelimeogreniyorum.ui.theme.EmeraldManuscript
import com.azimut.arapcakelimeogreniyorum.ui.theme.GoldAccent
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentCardBg
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGoldDark
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextLight
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextWhite

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onNavigateToTab: (AppBottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val stats = state.userStats

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Header Card with User Level, Streak, XP and Quick Quiz Launcher
        ParchmentCard(
            modifier = Modifier.fillMaxWidth(),
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                    fontFamily = FontFamily.Serif,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ManuscriptDeepBrown,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                Text(
                    text = "Arapça Kelime Ve Elif-Ba Öğrenimi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ManuscriptDeepBrown
                )

                Text(
                    text = "Geleneksel Yazma Eser Metodu ile Adım Adım Öğrenin",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = ManuscriptDeepBrown.copy(alpha = 0.85f),
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // User Level Title & Badges Container Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(ParchmentBeige)
                        .border(1.dp, ParchmentGold, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Rounded.EmojiEvents,
                                    contentDescription = null,
                                    tint = GoldAccent,
                                    modifier = Modifier.size(26.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "Seviye Unvanınız",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = ManuscriptDeepBrown.copy(alpha = 0.8f)
                                    )
                                    Text(
                                        text = stats.currentLevel.fullDisplayTitle,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EmeraldManuscript
                                    )
                                }
                            }

                            // Streak Badge on Dark Brown Background
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(ManuscriptDeepBrown)
                                    .border(1.dp, ParchmentGold, RoundedCornerShape(16.dp))
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Rounded.LocalFireDepartment,
                                        contentDescription = "Günlük Seri",
                                        tint = ParchmentTextGold,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${stats.currentStreak} Gün Seri",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ParchmentTextLight
                                    )
                                }
                            }
                        }

                        // XP Progress Bar
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Rounded.AutoAwesome,
                                        contentDescription = null,
                                        tint = EmeraldManuscript,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Toplam XP: ${stats.totalXp}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ManuscriptDeepBrown
                                    )
                                }
                                Text(
                                    text = "Sonraki Seviye: ${stats.xpForNextLevel} XP",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = ManuscriptDeepBrown.copy(alpha = 0.8f)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { stats.xpProgressInLevelFraction },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                color = EmeraldManuscript,
                                trackColor = ParchmentGold.copy(alpha = 0.4f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Quick Daily Practice Launcher Button ("Günün Testini Başlat")
                Button(
                    onClick = { onNavigateToTab(AppBottomTab.DAILY_QUIZ) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmeraldManuscript,
                        contentColor = ParchmentTextWhite
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "Başlat",
                        tint = ParchmentTextWhite
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Günün Testini Başlat (+10 XP)",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ParchmentTextWhite
                    )
                }
            }
        }

        // Quick Navigation Cards Section
        Text(
            text = "Öğrenim Bölümleri",
            style = MaterialTheme.typography.titleLarge,
            color = ManuscriptDeepBrown,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Elif-Ba Card
            QuickAccessCard(
                title = "Elif-Ba & Harekeler",
                subtitle = "28 Harf + Harekeler",
                icon = Icons.AutoMirrored.Rounded.MenuBook,
                badgeText = "28 Harf",
                onClick = { onNavigateToTab(AppBottomTab.ALPHABET) },
                modifier = Modifier.weight(1f)
            )

            // Flashcards Card
            QuickAccessCard(
                title = "Parchment Kartlar",
                subtitle = "Kelime & Anlam",
                icon = Icons.Rounded.Style,
                badgeText = "${state.vocabularyTotal} Kelime",
                onClick = { onNavigateToTab(AppBottomTab.FLASHCARDS) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Gamified Daily Quiz Launcher Card
            QuickAccessCard(
                title = "Günün Testi / Pratik",
                subtitle = "Oyunlaştırılmış soru seti & can sistemi",
                icon = Icons.Rounded.SportsEsports,
                badgeText = "+XP & Can",
                onClick = { onNavigateToTab(AppBottomTab.DAILY_QUIZ) },
                modifier = Modifier.weight(1f)
            )

            // Placement Test Section Card
            QuickAccessCard(
                title = "Seviye Tespit Sınavı",
                subtitle = "Genel seviye ölçümü (A1-B1)",
                icon = Icons.Rounded.Assessment,
                badgeText = "Sınav",
                onClick = { onNavigateToTab(AppBottomTab.PLACEMENT_TEST) },
                modifier = Modifier.weight(1f)
            )
        }

        // Persistent User Statistics Overview Card
        Text(
            text = "Öğrenim & Başarı Durumu",
            style = MaterialTheme.typography.titleLarge,
            color = ManuscriptDeepBrown,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )

        ParchmentCard(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatMetricTile(
                        label = "Tamamlanan Testler",
                        value = "${stats.completedQuizzesCount}",
                        icon = Icons.Rounded.SportsEsports,
                        color = EmeraldManuscript,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    StatMetricTile(
                        label = "En Yüksek Skor",
                        value = "%${stats.bestQuizScore}",
                        icon = Icons.Rounded.EmojiEvents,
                        color = GoldAccent,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Alphabet Progress
                ProgressStatRow(
                    label = "Elif-Ba Harfleri Tamamlanma",
                    current = state.alphabetCompleted,
                    total = state.alphabetTotal,
                    color = EmeraldManuscript
                )

                // Vocabulary Mastery
                ProgressStatRow(
                    label = "Öğrenilen Kelimeler (Uzmanlık 3+)",
                    current = state.vocabularyMastered,
                    total = state.vocabularyTotal,
                    color = ParchmentGoldDark
                )
            }
        }
    }
}

@Composable
private fun StatMetricTile(
    label: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(ParchmentBeige)
            .border(1.dp, ParchmentGold, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ManuscriptDeepBrown.copy(alpha = 0.85f)
                )
                Text(
                    text = value,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = ManuscriptDeepBrown
                )
            }
        }
    }
}

@Composable
private fun QuickAccessCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    badgeText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = ParchmentCardBg
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(ManuscriptDeepBrown)
                        .border(1.dp, ParchmentGold, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = ParchmentTextLight,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .background(ManuscriptDeepBrown, RoundedCornerShape(10.dp))
                        .border(1.dp, ParchmentGold, RoundedCornerShape(10.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = badgeText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ParchmentTextGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = ManuscriptDeepBrown
            )

            Text(
                text = subtitle,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = ManuscriptDeepBrown.copy(alpha = 0.8f),
                modifier = Modifier.padding(top = 2.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 6.dp)
            ) {
                Text(
                    text = "Aç",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldManuscript
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = EmeraldManuscript,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

@Composable
private fun ProgressStatRow(
    label: String,
    current: Int,
    total: Int,
    color: Color
) {
    val progress = if (total > 0) current.toFloat() / total.toFloat() else 0f

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ManuscriptDeepBrown
            )
            Text(
                text = "$current / $total",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = color,
            trackColor = ParchmentBeige
        )
    }
}

@Preview(showBackground = true, name = "Dashboard Screen Preview")
@Composable
fun DashboardScreenPreview() {
    ArapcakelimeogreniyorumTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ParchmentCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                        fontFamily = FontFamily.Serif,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                    Text(
                        text = "Arapça Kelime Ve Elif-Ba Öğrenimi",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                QuickAccessCard(
                    title = "Elif-Ba & Harekeler",
                    subtitle = "28 Harf + Harekeler",
                    icon = Icons.AutoMirrored.Rounded.MenuBook,
                    badgeText = "28 Harf",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                QuickAccessCard(
                    title = "Parchment Kartlar",
                    subtitle = "Kelime & Anlam",
                    icon = Icons.Rounded.Style,
                    badgeText = "50 Kelime",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
