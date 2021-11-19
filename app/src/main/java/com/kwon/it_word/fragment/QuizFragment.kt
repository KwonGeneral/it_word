package com.kwon.it_word.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kwon.it_word.R
import com.kwon.it_word.adapter.QuizAdapter
import com.kwon.it_word.data.QuizData
import com.kwon.it_word.view.QuizDataVM
import kotlinx.android.synthetic.main.fragment_quiz.*

class QuizFragment : Fragment() {
    private var quizDataList: MutableList<QuizData> = mutableListOf()

    private val quizAdapter: QuizAdapter by lazy {
        QuizAdapter(requireContext())
    }

    companion object {
        fun newInstance(): QuizFragment {
            return QuizFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        QuizDataVM.getInstance(requireContext())?.let {
            it.quizReadData.observe(viewLifecycleOwner, { data ->
                for(k in data) {
                    if(!k.view_status) {
                        with(quiz_recycler) {
                            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//                          addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))  --> 구분선
                            adapter = quizAdapter
                        }
                        quizDataList.add(QuizData(k.id, k.answer, k.question_1, k.question_2, k.question_3, k.question_4, view_status = true, correct_status = false))
                    }
                    quizAdapter.submitList(data)
                }
            })
        }

        QuizDataVM.getInstance(requireContext())?.apply {
//            onQuizCreate("Quiz", "커즈", "퀴즈", "퀸즈", "컴즈")
            onQuizRead()
            quiz_send_message_btn.setOnClickListener {
                onQuizCreate("랜덤 문제", "허허", "히히", "호호", "하하")
                onQuizRead()
            }
        }
    }

    override fun onStop() {
        QuizDataVM.getInstance(requireContext())?.let {
            for(n in quizDataList) {
                it.onQuizUpdate(n)
            }
        }
        super.onStop()
    }
}