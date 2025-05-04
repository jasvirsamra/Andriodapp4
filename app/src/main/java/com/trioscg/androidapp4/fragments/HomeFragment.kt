package com.trioscg.androidapp4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.trioscg.androidapp4.activities.MainActivity
import com.trioscg.androidapp4.databinding.FragmentHomeBinding
import com.trioscg.androidapp4.models.QuizQuestion
import com.trioscg.androidapp4.viewmodel.SharedViewModel

class HomeFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.buttonGeneral.setOnClickListener {
            navigateToQuiz("📚 General Knowledge")
        }

        binding.buttonScience.setOnClickListener {
            navigateToQuiz("🧪 Science")
        }

        binding.buttonHistory.setOnClickListener {
            navigateToQuiz("📜 History")
        }

        binding.buttonMovies.setOnClickListener {
            navigateToQuiz("🎬 Movies")
        }

        return binding.root
    }

    private fun navigateToQuiz(category: String) {
        viewModel.selectedCategory = category
        viewModel.currentQuestionIndex = 0
        viewModel.userAnswers.clear()
        viewModel.questions = emptyList<QuizQuestion>()
        (activity as MainActivity).loadFragment(QuizFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
