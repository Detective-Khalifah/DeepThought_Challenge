package com.blogspot.thengnet.khadeepthoughtchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.thengnet.khadeepthoughtchallenge.R
import com.blogspot.thengnet.khadeepthoughtchallenge.data.Datum

class DeepthoughtAdapter(val context: Context, val deepthoughts: List<Datum>) :
    RecyclerView.Adapter<DeepthoughtAdapter.DeepthoughtViewHolder>() {

    class DeepthoughtViewHolder(deepthoughtView: View) : RecyclerView.ViewHolder(deepthoughtView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeepthoughtViewHolder {
//        DataBindingUtil.inflate(inflater, R.layout.article, parent, false)
        return DeepthoughtViewHolder(LayoutInflater.from(context).inflate(R.layout.deepthought, parent, false))
    }

    override fun getItemCount(): Int = deepthoughts.size

    override fun onBindViewHolder(holder: DeepthoughtViewHolder, position: Int) {
        val deepthought = deepthoughts[position]
    }

}
