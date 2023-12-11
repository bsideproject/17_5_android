package com.carpick.carpickapp.screen.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.carpick.carpickapp.ClickListener
import com.carpick.carpickapp.databinding.FragmentCarpickDetailQnaBinding
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.ui.adapter.AnswerLessAdapter
import com.carpick.carpickapp.util.setOnSingleClickListener


class CarpickDetailQnaFragment : BaseFragment<FragmentCarpickDetailQnaBinding>() {
    private var nowPage = 2
    private var totalPage = 10 // api 나오면 수정
    private var answerLessAdapter: AnswerLessAdapter? = null
    private var testResult = ArrayList<ArrayList<TestModel>>()
    private var testModel1 = ArrayList<TestModel>()
    private var testModel2 = ArrayList<TestModel>()
    private var testModel3 = ArrayList<TestModel>()

    private var pairTest = ArrayList<Pair<Int,Int>>()
    private var hashMap = HashMap<Int, Int>()

    private var answerPage = arrayListOf<Int>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        testModel1.add(TestModel(id=1, testData = "밟으면 나가는 빠른 가속!"))
        testModel1.add(TestModel(id=2, testData = "팝콘 터지는 배기음!"))

        testModel2.add(TestModel(id=3, testData = "시내 주행"))
        testModel2.add(TestModel(id=4, testData = "장거리 운행"))

        testModel3.add(TestModel(id=5, testData = "3페이지 첫번째"))
        testModel3.add(TestModel(id=6, testData = "3페이지 두번째"))

        testResult.add(testModel1)
        testResult.add(testModel2)
        testResult.add(testModel3)

        binding.run {
            answerLessAdapter = AnswerLessAdapter()
            rvAnswer.adapter = answerLessAdapter
            answerLessAdapter?.submitList(testModel1)

            roundProgressBar.progress = totalPage / nowPage
            tvNowQnaPos.text = "$nowPage "

            answerPage.add(nowPage)
            answerLessAdapter?.setClickListener(object : ClickListener {
                override fun click(item: TestModel) {
                    nowPage++

                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

//                    pairTest.add(Pair(nowPage-1, item.id))
                    hashMap[nowPage-1] = item.id

                    if(nowPage-2 < testResult.size) {
                        answerLessAdapter?.hashMapTest(hashMap, nowPage)
                        answerLessAdapter?.submitList(testResult[nowPage - 2])
                    }
                }
            })
        }
    }

    private fun initListener() {
        binding.run {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                if(nowPage > 2) {
                    nowPage--
                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    tvNowQnaPos.text = "$nowPage "

                    answerLessAdapter?.hashMapTest(hashMap, nowPage)
                    answerLessAdapter?.submitList(testResult[nowPage-2])


                }else {
                    val fragmentManager = requireActivity().supportFragmentManager

                    fragmentManager.popBackStackImmediate()
                }
            }


            btnNext.setOnSingleClickListener {
                if (nowPage < totalPage) {
                    nowPage++
                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerLessAdapter?.hashMapTest(hashMap, nowPage)
                    answerLessAdapter?.submitList(testResult[nowPage-2])
                }
            }

            btnPrev.setOnSingleClickListener {
                if (nowPage > 1) {
                    nowPage--
                    tvNowQnaPos.text = "$nowPage "

                    val progressBarValue = nowPage * 100 / totalPage
                    roundProgressBar.progress = progressBarValue

                    answerLessAdapter?.hashMapTest(hashMap, nowPage)
                    answerLessAdapter?.submitList(testResult[nowPage-2])
                }
            }
        }
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarpickDetailQnaBinding {
        return FragmentCarpickDetailQnaBinding.inflate(layoutInflater)
    }
}