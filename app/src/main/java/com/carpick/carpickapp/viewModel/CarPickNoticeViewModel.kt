package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import com.carpick.carpickapp.model.GetNoticeModel
import com.carpick.carpickapp.repository.NoticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class CarPickNoticeViewModel @Inject constructor(
    private val noticeRepository : NoticeRepository
) : ViewModel(){
    fun getNotice() : Flow<GetNoticeModel> {
        return noticeRepository.getNotice()
            .catch { it.printStackTrace() }
    }
}