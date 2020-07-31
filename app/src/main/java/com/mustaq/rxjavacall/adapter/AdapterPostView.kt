package com.mustaq.rxjavacall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mustaq.rxjavacall.R
import com.mustaq.rxjavacall.model.Post


class AdapterPostView(val context: Context, val postList: List<Post>) :
    RecyclerView.Adapter<AdapterPostView.PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val postView =
            LayoutInflater.from(context).inflate(R.layout.item_row_user, parent, false)
        return PostHolder(postView)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<AppCompatTextView>(R.id.tvTitle)
        val tvContent = itemView.findViewById<AppCompatTextView>(R.id.tvContent)
        val tvAuthor = itemView.findViewById<AppCompatTextView>(R.id.tvAuthor)
        fun bind(position: Int) {
            tvTitle.text = "User Id:-" + postList[position].id.toString()
            tvContent.text = "Content:-" + postList[position].body
            tvAuthor.text = "Title:-" + postList[position].title
        }
    }
}