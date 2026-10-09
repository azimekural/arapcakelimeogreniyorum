package com.azimut.arapcakelimeogreniyorum.ui.quiz

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.SentimentVeryDissatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.data.domain.UserProgressStats
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.ui.components.FlashcardIllustration
import com.azimut.arapcakelimeogreniyorum.ui.components.ParchmentCard
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme
import com.azimut.arapcakelimeogreniyorum.ui.theme.EmeraldManuscript
import com.azimut.arapcakelimeogreniyorum.ui.theme.GoldAccent
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentCardBg
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentContainer
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextLight
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextWhite
import com.azimut.arapcakelimeogreniyorum.ui.theme.RubyManuscript

@Composable
fun DailyQuizScreen(
    viewModel: DailyQuizViewModel,
    onReturnToDashboard: () -> Unit,
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (state.quizStatus) {
            QuizStatus.SUCCESS -> {
                QuizCelebrationSection(
                    score = state.score,
                    totalQuestions = state.questions.size,
                    totalXpGained = state.totalXpGained,
                    userStats = state.userStats,
                    onPlayAgain = { viewModel.restartQuiz() },
                    onReturnToDashboard = onReturnToDashboard
                )
            }
            QuizStatus.GAME_OVER -> {
                GameOverSection(
                    score = state.score,
                    totalQuestions = state.questions.size,
                    onTryAgain = { viewModel.restartQuiz() },
                    onReturnToDashboard = onReturnToDashboard
                )
            }
            QuizStatus.IN_PROGRESS -> {
                // Game Header Bar: Hearts, Combo Streak, XP Counter
                QuizHeaderBar(
                    heartsRemaining = state.heartsRemaining,
                    comboStreak = state.comboStreak,
                    xpGained = state.totalXpGained,
                    selectedDifficulty = state.selectedDifficulty,
                    onSelectDifficulty = { diff -> viewModel.setDifficulty(diff) }
                )

                if (state.questions.isNotEmpty()) {
                    val currentQIndex = state.currentQuestionIndex
                    val currentQ = state.currentQuestion

                    // Progress Bar & Question Counter
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Günün Testi",
                            style = MaterialTheme.typography.titleMedium,
                            color = ManuscriptDeepBrown,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Soru ${currentQIndex + 1} / ${state.questions.size}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldManuscript
                        )
                    }

                    val progressFraction = (currentQIndex + 1).toFloat() / state.questions.size.toFloat()
                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = ParchmentGold,
                        trackColor = ParchmentBeige
                    )

                    currentQ?.let { question ->
                        InteractiveQuestionCard(
                            question = question,
                            questionType = state.questionType,
                            selectedOptionIndex = state.selectedOptionIndex,
                            isSubmitted = state.isAnswerSubmitted,
                            isCorrect = state.isCorrect,
                            onSelectOption = { optionIndex ->
                                viewModel.selectOption(optionIndex)
                            }
                        )
                    }

                    // Next / Complete Button when answer submitted
                    AnimatedVisibility(
                        visible = state.isAnswerSubmitted,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Button(
                            onClick = { viewModel.nextQuestion() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (state.isLastQuestion) EmeraldManuscript else ManuscriptDeepBrown,
                                contentColor = ParchmentTextLight
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (state.isLastQuestion) "Testi Tamamla" else "Sonraki Soruya Geç",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = ParchmentTextLight
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = null,
                                tint = ParchmentTextLight
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
                                text = "Pratik soruları yükleniyor...",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizHeaderBar(
    heartsRemaining: Int,
    comboStreak: Int,
    xpGained: Int,
    selectedDifficulty: Int,
    onSelectDifficulty: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = ParchmentCardBg),
        border = BorderStroke(1.dp, ParchmentGold),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hearts / Lives Display
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(3) { index ->
                        val isAlive = index < heartsRemaining
                        Icon(
                            imageVector = if (isAlive) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Can",
                            tint = if (isAlive) RubyManuscript else ManuscriptDeepBrown.copy(alpha = 0.35f),
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 4.dp)
                        )
                    }
                }

                // Combo Streak Badge on Dark Brown
                if (comboStreak >= 2) {
                    Box(
                        modifier = Modifier
                            .background(ManuscriptDeepBrown, RoundedCornerShape(10.dp))
                            .border(1.dp, GoldAccent, RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.LocalFireDepartment,
                                contentDescription = null,
                                tint = ParchmentTextGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Combo x$comboStreak (+5 XP)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = ParchmentTextGold
                            )
                        }
                    }
                }

                // XP Earned Badge on Emerald
                Box(
                    modifier = Modifier
                        .background(EmeraldManuscript, RoundedCornerShape(10.dp))
                        .border(1.dp, ParchmentGold, RoundedCornerShape(10.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            tint = ParchmentTextGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "+$xpGained XP",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ParchmentTextWhite
                        )
                    }
                }
            }

            // Difficulty Chips Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Zorluk:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ManuscriptDeepBrown
                )

                val difficulties = listOf(
                    1 to "Kolay",
                    2 to "Orta",
                    3 to "Zor"
                )

                difficulties.forEach { (level, label) ->
                    val isSelected = selectedDifficulty == level
                    FilterChip(
                        selected = isSelected,
                        onClick = { onSelectDifficulty(level) },
                        label = {
                            Text(
                                text = label,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) ParchmentTextLight else ManuscriptDeepBrown
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = ManuscriptDeepBrown,
                            selectedLabelColor = ParchmentTextLight,
                            containerColor = ParchmentBeige,
                            labelColor = ManuscriptDeepBrown
                        ),
                        modifier = Modifier.height(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun InteractiveQuestionCard(
    question: QuizQuestionEntity,
    questionType: QuestionType,
    selectedOptionIndex: Int?,
    isSubmitted: Boolean,
    isCorrect: Boolean?,
    onSelectOption: (Int) -> Unit
) {
    ParchmentCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Visual Card / Illustration or Prompt Header based on QuestionType
            when (questionType) {
                QuestionType.VISUAL_MATCHING -> {
                    Text(
                        text = "Görseldeki kavramın Arapça karşılığını seçin:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                    FlashcardIllustration(
                        imageName = question.questionArabic,
                        category = question.category,
                        arabicText = question.questionArabic ?: question.optionA,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                QuestionType.ARABIC_TO_TURKISH -> {
                    Text(
                        text = "Arapça kelimenin Türkçe karşılığını seçin:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ManuscriptDeepBrown.copy(alpha = 0.85f)
                    )
                    question.questionArabic?.let { arabicText ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(ParchmentBeige)
                                .border(1.5.dp, ParchmentGold, RoundedCornerShape(10.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = arabicText,
                                fontFamily = FontFamily.Serif,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Text(
                        text = question.questionText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                }
                QuestionType.TURKISH_TO_ARABIC -> {
                    Text(
                        text = "Türkçe kelimenin Arapça karşılığını seçin:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ManuscriptDeepBrown.copy(alpha = 0.85f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(ParchmentBeige)
                            .border(1.5.dp, ParchmentGold, RoundedCornerShape(10.dp))
                            .padding(14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = question.questionText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = ManuscriptDeepBrown,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                QuestionType.DIACRITIC_TRANSLITERATION -> {
                    Text(
                        text = "Eksik harf / hareke / transliterasyon eşleşmesi:",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ManuscriptDeepBrown.copy(alpha = 0.85f)
                    )
                    question.questionArabic?.let { arabicText ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(ParchmentBeige)
                                .border(1.5.dp, ParchmentGold, RoundedCornerShape(10.dp))
                                .padding(14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = arabicText,
                                fontFamily = FontFamily.Serif,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = ManuscriptDeepBrown,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Text(
                        text = question.questionText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                }
            }

            // 4 Option Buttons with Visual Feedback
            val options = listOf(
                question.optionA,
                question.optionB,
                question.optionC,
                question.optionD
            )

            options.forEachIndexed { index, optionText ->
                val isSelected = selectedOptionIndex == index
                val isCorrectOption = index == question.correctOptionIndex

                val borderColor = when {
                    isSubmitted && isCorrectOption -> EmeraldManuscript
                    isSubmitted && isSelected && !isCorrectOption -> RubyManuscript
                    isSelected -> ManuscriptDeepBrown
                    else -> ParchmentGold.copy(alpha = 0.6f)
                }

                val containerColor = when {
                    isSubmitted && isCorrectOption -> EmeraldManuscript.copy(alpha = 0.15f)
                    isSubmitted && isSelected && !isCorrectOption -> RubyManuscript.copy(alpha = 0.15f)
                    isSelected -> ParchmentBeige
                    else -> ParchmentCardBg
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(enabled = !isSubmitted) { onSelectOption(index) }
                        .border(
                            width = if (isSelected || (isSubmitted && isCorrectOption)) 2.dp else 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(containerColor = containerColor)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { if (!isSubmitted) onSelectOption(index) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = ManuscriptDeepBrown,
                                    unselectedColor = ParchmentGold
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = optionText,
                                fontFamily = if (questionType == QuestionType.TURKISH_TO_ARABIC) FontFamily.Serif else FontFamily.Default,
                                fontSize = if (questionType == QuestionType.TURKISH_TO_ARABIC) 18.sp else 15.sp,
                                fontWeight = if (isSelected || (isSubmitted && isCorrectOption)) FontWeight.Bold else FontWeight.Medium,
                                color = ManuscriptDeepBrown
                            )
                        }

                        if (isSubmitted) {
                            if (isCorrectOption) {
                                Icon(
                                    imageVector = Icons.Rounded.CheckCircle,
                                    contentDescription = "Doğru",
                                    tint = EmeraldManuscript,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else if (isSelected && !isCorrectOption) {
                                Icon(
                                    imageVector = Icons.Rounded.Cancel,
                                    contentDescription = "Yanlış",
                                    tint = RubyManuscript,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Animated Instant Feedback Banner Card
            if (isSubmitted) {
                val correct = isCorrect == true
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (correct) EmeraldManuscript.copy(alpha = 0.15f) else RubyManuscript.copy(alpha = 0.15f))
                        .border(1.dp, if (correct) EmeraldManuscript else RubyManuscript, RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (correct) Icons.Rounded.CheckCircle else Icons.Rounded.Cancel,
                                contentDescription = null,
                                tint = if (correct) EmeraldManuscript else RubyManuscript,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (correct) "Harika! Doğru Cevap (+10 XP)" else "Yanlış Cevap! (-1 Can)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (correct) EmeraldManuscript else RubyManuscript
                            )
                        }
                        question.explanation?.let { explanationText ->
                            Text(
                                text = explanationText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = ManuscriptDeepBrown,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizCelebrationSection(
    score: Int,
    totalQuestions: Int,
    totalXpGained: Int,
    userStats: UserProgressStats,
    onPlayAgain: () -> Unit,
    onReturnToDashboard: () -> Unit
) {
    val scorePercent = if (totalQuestions > 0) (score * 100) / totalQuestions else 0

    ParchmentCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(ManuscriptDeepBrown)
                    .border(2.dp, ParchmentGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.EmojiEvents,
                    contentDescription = null,
                    tint = ParchmentTextGold,
                    modifier = Modifier.size(48.dp)
                )
            }

            Text(
                text = "Tebrikler! Günlük Test Tamamlandı! 🎉",
                style = MaterialTheme.typography.titleLarge,
                color = ManuscriptDeepBrown,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // Results Card Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ParchmentBeige)
                    .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Başarı Puanı:", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                        Text("%$scorePercent ($score/$totalQuestions)", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EmeraldManuscript)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Kazanılan XP:", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                        Text("+$totalXpGained XP", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = EmeraldManuscript)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Günlük Seri:", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                        Text("🔥 ${userStats.currentStreak} Gün Seri", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Mevcut Seviye Unvanı:", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ManuscriptDeepBrown)
                        Text(userStats.currentLevel.fullDisplayTitle, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EmeraldManuscript)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onPlayAgain,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ManuscriptDeepBrown,
                        contentColor = ParchmentTextLight
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, ParchmentGold),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Refresh,
                        contentDescription = "Tekrar Çöz",
                        tint = ParchmentTextGold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Tekrar Çöz", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ParchmentTextLight)
                }

                Button(
                    onClick = onReturnToDashboard,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmeraldManuscript,
                        contentColor = ParchmentTextWhite
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Ana Sayfa",
                        tint = ParchmentTextWhite
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ana Sayfa", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ParchmentTextWhite)
                }
            }
        }
    }
}

@Composable
private fun GameOverSection(
    score: Int,
    totalQuestions: Int,
    onTryAgain: () -> Unit,
    onReturnToDashboard: () -> Unit
) {
    ParchmentCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(ManuscriptDeepBrown)
                    .border(2.dp, RubyManuscript, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.SentimentVeryDissatisfied,
                    contentDescription = null,
                    tint = RubyManuscript,
                    modifier = Modifier.size(48.dp)
                )
            }

            Text(
                text = "Canlarınız Bitti! 💔",
                style = MaterialTheme.typography.titleLarge,
                color = RubyManuscript,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Doğru Sayısı: $score / $totalQuestions\nÜzülmeyin! Doğru cevapları gözden geçirip tekrar deneyerek kelimeleri pekiştirebilirsiniz.",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = ManuscriptDeepBrown,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onTryAgain,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmeraldManuscript,
                        contentColor = ParchmentTextWhite
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Refresh,
                        contentDescription = "Yeniden Dene",
                        tint = ParchmentTextWhite
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Yeniden Dene", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ParchmentTextWhite)
                }

                Button(
                    onClick = onReturnToDashboard,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ManuscriptDeepBrown,
                        contentColor = ParchmentTextLight
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, ParchmentGold),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = "Ana Sayfa",
                        tint = ParchmentTextGold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ana Sayfa", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ParchmentTextLight)
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
fun DailyQuizScreenPreview() {
    ArapcakelimeogreniyorumTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QuizHeaderBar(
                    heartsRemaining = 3,
                    comboStreak = 3,
                    xpGained = 30,
                    selectedDifficulty = 1,
                    onSelectDifficulty = {}
                )
                InteractiveQuestionCard(
                    question = QuizQuestionEntity(
                        id = 1,
                        quizType = "DAILY",
                        category = "Kelime",
                        difficultyLevel = 1,
                        questionText = "'كِتَاب' kelimesinin Türkçe anlamı nedir?",
                        questionArabic = "كِتَاب",
                        optionA = "Kitap",
                        optionB = "Kalem",
                        optionC = "Defter",
                        optionD = "Masa",
                        correctOptionIndex = 0,
                        explanation = "'Kitab' Türkçe'ye de geçmiş Arapça bir kelimedir."
                    ),
                    questionType = QuestionType.ARABIC_TO_TURKISH,
                    selectedOptionIndex = 0,
                    isSubmitted = true,
                    isCorrect = true,
                    onSelectOption = {}
                )
            }
        }
    }
}
