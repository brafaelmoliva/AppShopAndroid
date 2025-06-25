package com.example.appshop.presentation.screens.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appshop.R
import com.example.appshop.presentation.screens.onboarding.components.*
import com.example.appshop.presentation.theme.ui.GreenButton
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingContent(
    viewModel: OnboardingViewModel = viewModel(),
    onNextClick: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(R.drawable.img_1, "Start Your Journey with Grocery", "Discover a wide range of fresh items."),
        OnboardingPage(R.drawable.img_2, "Fast Delivery", "Get your groceries delivered in no time."),
        OnboardingPage(R.drawable.img_3, "Easy Payments", "Pay easily with multiple methods.")
    )

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        viewModel.setCurrentPage(pagerState.currentPage)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPager(page = pages[page])
        }

        PageIndicator(size = pages.size, currentPage = pagerState.currentPage)

        Button(
            onClick = {
                if (pagerState.currentPage < pages.lastIndex) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onNextClick()
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenButton,
                contentColor = Color.White
            )
        ) {
            Text(text = if (pagerState.currentPage < pages.lastIndex) "Next" else "Get Started")
        }
    }
}
