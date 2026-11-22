package com.example.safedrunk.model
object BlockPolicy {

    val SYSTEM_ALLOWED: Set<String> = setOf(
        "android",
        "com.android.systemui",
        "com.android.incallui",
        "com.android.server.telecom",
        "com.android.emergency",
        "com.samsung.android.incallui"
    )

    fun isBlocked(packageName: String, allowedPackages: Set<String>): Boolean =
        packageName !in allowedPackages && packageName !in SYSTEM_ALLOWED
}
