package com.mustaq.rxjavacall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustaq.rxjavacall.adapter.AdapterPostView
import com.mustaq.rxjavacall.apiCall.IamApi
import com.mustaq.rxjavacall.model.Post
import com.mustaq.rxjavacall.networkCall.RetrofitClient.retrofitInstance
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var imApi: IamApi? = null
    var rxRecyclerView: RecyclerView? = null
    var mCompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rxRecyclerView = findViewById(R.id.rxRecyclerView)
        val retrofit = retrofitInstance
        imApi = retrofit!!.create(IamApi::class.java)
        rxRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getCall()
    }

    private fun getCall() {
        mCompositeDisposable.add(
                imApi!!.post.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { postViewModel -> displayData(postViewModel as List<Post>) }
        )
    }

    private fun displayData(list: List<Post>) {
        val adapterPostView = AdapterPostView(this, list)
        rxRecyclerView!!.adapter = adapterPostView
    }

    override fun onStop() {
        mCompositeDisposable.clear()
        super.onStop()
    }
}