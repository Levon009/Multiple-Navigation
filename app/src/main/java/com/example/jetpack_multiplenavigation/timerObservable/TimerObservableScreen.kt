package com.example.jetpack_multiplenavigation.timerObservable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jetpack_multiplenavigation.ui.theme.DeepBlue
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timerTask

@Composable
fun TimerObservableScreen() {
    val time = remember {
        mutableStateOf("")
    }
    val disposable = remember {
        mutableStateOf<Disposable?>(null)
    }
    LaunchedEffect(key1 = true, key2 = observeDate(time, disposable)) {
        time.value = observeDate(time, disposable)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) { paddingValues ->
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = time.value,
                color = DeepBlue,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            disposable.value?.dispose()
        }
    }
}

private fun observeDate(
    timeStr: MutableState<String>,
    disposable: MutableState<Disposable?>
) : String {
    val timerObservable: Observable<Date> = Observable.create { emitter: ObservableEmitter<Date> ->
        val timer = Timer()
        val task = timerTask {
            try {
                emitter.onNext(Date())
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
        timer.schedule(task, 0, 1000)
        emitter.setCancellable { timer.cancel() }
    }

    disposable.value = timerObservable.subscribe { date ->
        timeStr.value = date.toString().dropLastWhile {
            it != 'G'
        }.dropLast(1)
    }

    return timeStr.value
}