package com.example.safedrunk.model
enum class LockDuration(val label: String, val minutes: Int) {
    MIN_30("30m", 30),
    H_1("1h", 60),
    H_2("2h", 120),
    H_2_5("2.5h", 150);

    val millis: Long get() = minutes * 60_000L
}
