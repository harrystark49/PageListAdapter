package com.example.pagelistadapter

import Results
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.pagelistadapter.RetrofitInstance.RetrofitInstance
import com.example.pagelistadapter.RetrofitInstance.Retroservice
import java.util.concurrent.Flow

class viewmodel:ViewModel() {
    lateinit var retrofitinstance:Retroservice

    init {
        retrofitinstance = RetrofitInstance.getRetrofitInstance().create(Retroservice::class.java)

    }
    fun getListData():kotlinx.coroutines.flow.Flow<PagingData<Results>>{
        return Pager(config = PagingConfig(42,maxSize = 200),pagingSourceFactory = {pagingsource(retrofitinstance)}).flow.cachedIn(viewModelScope)
    }
}