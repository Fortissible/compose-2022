package com.example.composechampions2k22submission.core.domain.model

data class Course (
    val isCompleted : Int,
    val state : String,
    val courseName : String
)

fun getCourseList(): List<Course> {
    return arrayListOf(
        Course(
            0,
            "Sedang Dipelajari",
            "Belajar Membuat Aplikasi Android dengan Jetpack Compose"
        ),
        Course(
            0,
            "Sedang Dipelajari",
            "Menjadi Android Developer Expert"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Meniti Karier sebagai Software Developer"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Belajar Dasar UX Design"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Belajar Dasar Git dengan GitHub"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Pengenalan ke Logika Pemrograman (Programming Logic 101)"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Belajar Prinsip Pemrograman SOLID"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Memulai Dasar Pemrograman untuk Menjadi Pengembang Software"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Memulai Pemrograman Dengan Kotlin"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Belajar Fundamental Aplikasi Android"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Belajar Pengembangan Aplikasi Android Intermediate"
        ),
        Course(
            1,
            "Selesai Dipelajari",
            "Simulasi Ujian Associate Android Developer"
        )
    )
}