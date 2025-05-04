package com.trioscg.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.trioscg.androidapp4.databinding.FragmentResultBinding
import com.trioscg.androidapp4.viewmodel.SharedViewModel

class ResultFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        val totalCorrect = viewModel.questions.zip(viewModel.userAnswers).count {
                (question, answer) -> question.correctAnswerIndex == answer
        }

        val resultText = StringBuilder("You got $totalCorrect/${viewModel.questions.size} correct!\n\n")
        viewModel.questions.forEachIndexed { i, question ->
            resultText.append("Q: ${question.question}\n")
            resultText.append("Your Answer: ${question.options[viewModel.userAnswers[i]]}\n")
            resultText.append("Correct Answer: ${question.options[question.correctAnswerIndex]}\n\n")
        }

        binding.textResult.text = resultText.toString()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
