package com.example.pagelistadapter

import Results
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagelistadapter.RetrofitInstance.Retroservice

class pagingsource(var apiservice:Retroservice):PagingSource<Int,Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try{
            var nextpage:Int=params.key?: pagenumone
            var responce=apiservice.getDataFromAPI(nextpage)
            var nextpagenumber:Int?=null
            if(responce.info.next!=null){
                var uri=Uri.parse(responce.info.next)
                var nextpagequery=uri.getQueryParameter("page")
                nextpagenumber=Integer.parseInt(nextpagequery)
            }
            LoadResult.Page(data = responce.results,prevKey = null,nextKey = nextpagenumber)
            

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
    companion object{
        var pagenumone=1
    }
}