package com.azimut.arapcakelimeogreniyorum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azimut.arapcakelimeogreniyorum.data.local.AppDatabase
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepository
import com.azimut.arapcakelimeogreniyorum.data.repository.ArabicLearningRepositoryImpl
import com.azimut.arapcakelimeogreniyorum.ui.MainViewModel
import com.azimut.arapcakelimeogreniyorum.ui.alphabet.AlphabetGuideScreen
import com.azimut.arapcakelimeogreniyorum.ui.alphabet.AlphabetViewModel
import com.azimut.arapcakelimeogreniyorum.ui.dashboard.DashboardScreen
import com.azimut.arapcakelimeogreniyorum.ui.dashboard.DashboardViewModel
import com.azimut.arapcakelimeogreniyorum.ui.flashcards.FlashcardScreen
import com.azimut.arapcakelimeogreniyorum.ui.flashcards.FlashcardViewModel
import com.azimut.arapcakelimeogreniyorum.ui.navigation.AppBottomBar
import com.azimut.arapcakelimeogreniyorum.ui.navigation.AppBottomTab
import com.azimut.arapcakelimeogreniyorum.ui.navigation.AppTopBar
import com.azimut.arapcakelimeogreniyorum.ui.placement.PlacementTestScreen
import com.azimut.arapcakelimeogreniyorum.ui.placement.PlacementTestViewModel
import com.azimut.arapcakelimeogreniyorum.ui.quiz.DailyQuizScreen
import com.azimut.arapcakelimeogreniyorum.ui.quiz.DailyQuizViewModel
import com.azimut.arapcakelimeogreniyorum.ui.theme.ArapcakelimeogreniyorumTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getInstance(applicationContext)
        val repository = ArabicLearningRepositoryImpl(database)

        setContent {
            ArapcakelimeogreniyorumTheme {
                MainAppContent(repository = repository)
            }
        }
    }
}

@Composable
fun MainAppContent(repository: ArabicLearningRepository) {
    val mainViewModel: MainViewModel = viewModel(
        factory = MainViewModel.Factory(repository)
    )
    val dashboardViewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModel.Factory(repository)
    )
    val alphabetViewModel: AlphabetViewModel = viewModel(
        factory = AlphabetViewModel.Factory(repository)
    )
    val flashcardViewModel: FlashcardViewModel = viewModel(
        factory = FlashcardViewModel.Factory(repository)
    )
    val dailyQuizViewModel: DailyQuizViewModel = viewModel(
        factory = DailyQuizViewModel.Factory(repository)
    )
    val placementTestViewModel: PlacementTestViewModel = viewModel(
        factory = PlacementTestViewModel.Factory(repository)
    )

    val currentTab by mainViewModel.currentTab.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = currentTab.title
            )
        },
        bottomBar = {
            AppBottomBar(
                currentTab = currentTab,
                onTabSelected = { tab -> mainViewModel.selectTab(tab) }
            )
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        when (currentTab) {
            AppBottomTab.DASHBOARD -> {
                DashboardScreen(
                    viewModel = dashboardViewModel,
                    onNavigateToTab = { tab -> mainViewModel.selectTab(tab) },
                    modifier = contentModifier
                )
            }
            AppBottomTab.ALPHABET -> {
                AlphabetGuideScreen(
                    viewModel = alphabetViewModel,
                    modifier = contentModifier
                )
            }
            AppBottomTab.FLASHCARDS -> {
                FlashcardScreen(
                    viewModel = flashcardViewModel,
                    modifier = contentModifier
                )
            }
            AppBottomTab.DAILY_QUIZ -> {
                DailyQuizScreen(
                    viewModel = dailyQuizViewModel,
                    onReturnToDashboard = { mainViewModel.selectTab(AppBottomTab.DASHBOARD) },
                    modifier = contentModifier
                )
            }
            AppBottomTab.PLACEMENT_TEST -> {
                PlacementTestScreen(
                    viewModel = placementTestViewModel,
                    onNavigateToFlashcards = { mainViewModel.selectTab(AppBottomTab.FLASHCARDS) },
                    modifier = contentModifier
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,dpi=420")
@Composable
fun MainAppPreview() {
    ArapcakelimeogreniyorumTheme {
        // Preview placeholder container
    }
}
