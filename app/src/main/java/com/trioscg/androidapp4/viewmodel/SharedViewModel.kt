package com.trioscg.androidapp4.viewmodel

import androidx.lifecycle.ViewModel
import com.trioscg.androidapp4.models.QuizQuestion

class SharedViewModel : ViewModel() {
    var selectedCategory: String = ""
    var currentQuestionIndex = 0
    var userAnswers: MutableList<Int> = mutableListOf()
    var questions: List<QuizQuestion> = listOf()

    fun getCurrentQuestion(): QuizQuestion? {
        return if (currentQuestionIndex < questions.size) questions[currentQuestionIndex] else null
    }

    fun isQuizFinished(): Boolean {
        return currentQuestionIndex >= questions.size
    }
}
