package com.trioscg.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.trioscg.androidapp4.databinding.FragmentQuizBinding
import com.trioscg.androidapp4.viewmodel.SharedViewModel
import com.trioscg.androidapp4.activities.MainActivity
import com.trioscg.androidapp4.models.QuizQuestion
import com.trioscg.androidapp4.fragments.ResultFragment

class QuizFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        if (viewModel.questions.isEmpty()) {
            viewModel.questions = loadQuestions(viewModel.selectedCategory)
        }

        showQuestion()

        binding.buttonNext.setOnClickListener {
            val selectedOption = binding.radioGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                val selectedIndex = binding.radioGroup.indexOfChild(binding.radioGroup.findViewById<RadioButton>(selectedOption))
                viewModel.userAnswers.add(selectedIndex)
                viewModel.currentQuestionIndex++
                if (viewModel.isQuizFinished()) {
                    (activity as MainActivity).loadFragment(ResultFragment())
                } else {
                    showQuestion()
                }
            }
        }

        return binding.root
    }

    private fun showQuestion() {
        val question = viewModel.getCurrentQuestion()
        binding.textQuestion.text = question?.question
        question?.options?.forEachIndexed { index, option ->
            (binding.radioGroup.getChildAt(index) as RadioButton).text = option
        }
        binding.radioGroup.clearCheck()
    }

    private fun loadQuestions(category: String): List<QuizQuestion> {
        return listOf(
            QuizQuestion("Capital of France?", listOf("Paris", "Berlin", "London", "Rome"), 0),
            QuizQuestion("5 + 3 = ?", listOf("6", "7", "8", "9"), 2),
            QuizQuestion("Water's formula?", listOf("H2O", "CO2", "NaCl", "O2"), 0),
            QuizQuestion("Largest planet?", listOf("Earth", "Mars", "Jupiter", "Venus"), 2),
            QuizQuestion("Python is a?", listOf("Reptile", "Programming Language", "Car", "Food"), 1),
            QuizQuestion("Inventor of Light Bulb?", listOf("Newton", "Einstein", "Edison", "Tesla"), 2),
            QuizQuestion("First US President?", listOf("Lincoln", "Jefferson", "Washington", "Obama"), 2),
            QuizQuestion("Result of 9 * 9?", listOf("81", "72", "90", "99"), 0),
            QuizQuestion("Moon is a?", listOf("Planet", "Satellite", "Star", "Rock"), 1),
            QuizQuestion("Which is not a browser?", listOf("Chrome", "Firefox", "Safari", "Java"), 3)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
