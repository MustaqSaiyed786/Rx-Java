package com.mustaq.rxjavacall.apiCall

import com.mustaq.rxjavacall.model.Post
import io.reactivex.Observable
import retrofit2.http.GET


interface IamApi {

    @get:GET("posts")
    val post: Observable<List<Post?>>
}