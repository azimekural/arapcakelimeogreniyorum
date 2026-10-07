package com.azimut.arapcakelimeogreniyorum.ui.placement

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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Assessment
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.azimut.arapcakelimeogreniyorum.data.local.entity.QuizQuestionEntity
import com.azimut.arapcakelimeogreniyorum.ui.components.ParchmentCard
import com.azimut.arapcakelimeogreniyorum.ui.theme.EmeraldManuscript
import com.azimut.arapcakelimeogreniyorum.ui.theme.ManuscriptDeepBrown
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentBeige
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentCardBg
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextGold
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextLight
import com.azimut.arapcakelimeogreniyorum.ui.theme.ParchmentTextWhite

@Composable
fun PlacementTestScreen(
    viewModel: PlacementTestViewModel,
    onNavigateToFlashcards: () -> Unit,
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
        if (state.isSubmitted) {
            // TEST RESULT SCREEN
            TestResultSection(
                score = state.score,
                totalQuestions = state.totalQuestions,
                evaluatedLevel = state.evaluatedLevel,
                onRetake = { viewModel.retakeTest() },
                onGoToFlashcards = onNavigateToFlashcards
            )
        } else if (state.questions.isNotEmpty()) {
            // ASSESSMENT QUESTION FLOW
            val currentQIndex = state.currentQuestionIndex
            val currentQ = state.currentQuestion

            // Progress Bar & Counter
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Seviye Tespit Sınavı",
                    style = MaterialTheme.typography.titleLarge,
                    color = ManuscriptDeepBrown,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Soru ${currentQIndex + 1} / ${state.totalQuestions}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = EmeraldManuscript
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            val progressFraction = (currentQIndex + 1).toFloat() / state.totalQuestions.toFloat()
            LinearProgressIndicator(
                progress = { progressFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = EmeraldManuscript,
                trackColor = ParchmentBeige
            )

            Spacer(modifier = Modifier.height(16.dp))

            currentQ?.let { question ->
                QuestionCard(
                    question = question,
                    selectedOptionIndex = state.userAnswers[currentQIndex],
                    onSelectOption = { optionIndex ->
                        viewModel.selectOption(currentQIndex, optionIndex)
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Control Action Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentQIndex > 0) {
                    Button(
                        onClick = { viewModel.previousQuestion() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ParchmentBeige,
                            contentColor = ManuscriptDeepBrown
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, ParchmentGold)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Önceki",
                            tint = ManuscriptDeepBrown
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Önceki", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ManuscriptDeepBrown)
                    }
                } else {
                    Spacer(modifier = Modifier.width(8.dp))
                }

                if (state.isLastQuestion) {
                    Button(
                        onClick = { viewModel.submitTest() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldManuscript,
                            contentColor = ParchmentTextWhite
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = "Sınavı Tamamla",
                            tint = ParchmentTextWhite
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Sınavı Tamamla", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ParchmentTextWhite)
                    }
                } else {
                    Button(
                        onClick = { viewModel.nextQuestion() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ManuscriptDeepBrown,
                            contentColor = ParchmentTextLight
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Sonraki", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = ParchmentTextLight)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = "Sonraki",
                            tint = ParchmentTextLight
                        )
                    }
                }
            }
        } else {
            ParchmentCard(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Sınav soruları yükleniyor...",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown
                    )
                }
            }
        }
    }
}

@Composable
private fun QuestionCard(
    question: QuizQuestionEntity,
    selectedOptionIndex: Int?,
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
            // Difficulty Badge on Dark Brown
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
                    val difficultyText = when (question.difficultyLevel) {
                        1 -> "Kolay"
                        2 -> "Orta"
                        3 -> "Zor"
                        else -> "Genel"
                    }
                    Text(
                        text = "Zorluk: $difficultyText",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ParchmentTextGold
                    )
                }
            }

            // Optional Arabic Question Text
            question.questionArabic?.let { arabicQ ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(ParchmentBeige)
                        .border(1.dp, ParchmentGold, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = arabicQ,
                        fontFamily = FontFamily.Serif,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Turkish Question Text
            Text(
                text = question.questionText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = ManuscriptDeepBrown
            )

            // 4 Option Buttons
            val options = listOf(
                question.optionA,
                question.optionB,
                question.optionC,
                question.optionD
            )

            options.forEachIndexed { index, optionText ->
                val isSelected = selectedOptionIndex == index
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onSelectOption(index) }
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) ManuscriptDeepBrown else ParchmentGold.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) ParchmentBeige else ParchmentCardBg
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { onSelectOption(index) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = ManuscriptDeepBrown,
                                unselectedColor = ParchmentGold
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = optionText,
                            fontSize = 15.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = ManuscriptDeepBrown
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TestResultSection(
    score: Int,
    totalQuestions: Int,
    evaluatedLevel: String,
    onRetake: () -> Unit,
    onGoToFlashcards: () -> Unit
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
                text = "Seviye Tespit Sınavı Tamamlandı!",
                style = MaterialTheme.typography.titleLarge,
                color = ManuscriptDeepBrown,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // Evaluated Level Banner Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ParchmentBeige)
                    .border(1.5.dp, ParchmentGold, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Tümleyen Başarı Seviyeniz",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ManuscriptDeepBrown.copy(alpha = 0.85f)
                    )
                    Text(
                        text = evaluatedLevel,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldManuscript,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "Sınav Başarı Puanı: %$score",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ManuscriptDeepBrown,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }

            Text(
                text = "Seviyeniz veri tabanına kaydedildi. Öğrenim yolculuğunuza kelime kartları ve Elif-Ba kılavuzu ile devam edebilirsiniz.",
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
                    onClick = onRetake,
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
                        contentDescription = "Tekrar Et",
                        tint = ParchmentTextGold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Tekrar Çöz", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ParchmentTextLight)
                }

                Button(
                    onClick = onGoToFlashcards,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = EmeraldManuscript,
                        contentColor = ParchmentTextWhite
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Assessment,
                        contentDescription = "Kartlara Git",
                        tint = ParchmentTextWhite
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Kartlara Başla", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ParchmentTextWhite)
                }
            }
        }
    }
}
