package com.tech.wavesfood.common

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.tech.wavesfood.R
import com.tech.wavesfood.ui.theme.GreenColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerSlider() {
    val pagerState = rememberPagerState(initialPage = 0)

    val sliderList = listOf(
//        "https://picsum.photos/id/237/500/800",
//        "https://picsum.photos/id/244/500/800",
//        "https://picsum.photos/id/239/500/800",
//        "https://picsum.photos/id/243/500/800",
//        "https://picsum.photos/id/236/500/800",
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3,
    )
    LaunchedEffect(key1 = Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            HorizontalPager(
                count = sliderList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 15.dp),
                modifier = Modifier
                    .height(200.dp)
                    .width(550.dp)

            ) { page ->
                Card(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .graphicsLayer {
                            val pagerOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.50f,
                                stop = 1f,
                                fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                            )
                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    //through coil dependency
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(sliderList[page])
//                        .crossfade(true)
//                        .scale(Scale.FILL)
//                        .build(),
//                    contentDescription = null,
//                    placeholder =,
//                    error =
//                )
                    Image(painter = painterResource(id = sliderList[page]), contentDescription = "")
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp),
            activeColor = GreenColor
        )
    }
}
