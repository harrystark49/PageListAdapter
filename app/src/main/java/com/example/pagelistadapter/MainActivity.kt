package com.example.pagelistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagelistadapter.RetrofitInstance.RetrofitInstance
import com.example.pagelistadapter.RetrofitInstance.Retroservice
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn

class MainActivity : AppCompatActivity() {
    lateinit var rva:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var s=RetrofitInstance.getRetrofitInstance().create(Retroservice::class.java)

        initRecyclerView()
        initviewmodel()
    }
         fun initRecyclerView(){
             recyclerView.apply {
                 layoutManager=LinearLayoutManager(this@MainActivity)
                 var decoration=DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)
                 addItemDecoration(decoration)
                 rva= RecyclerViewAdapter()
                 adapter=rva.withLoadStateHeaderAndFooter(
                     header = ReposLoadStateAdapter { rva.retry() },
                     footer = ReposLoadStateAdapter { rva.retry() }
                 )

             }


    }
    private fun initviewmodel(){
        var viewmodel=ViewModelProvider(this).get(viewmodel::class.java)
        lifecycleScope.launchWhenCreated {
            viewmodel.getListData().collectLatest {
                rva.submitData(it)
            }
        }
    }
}