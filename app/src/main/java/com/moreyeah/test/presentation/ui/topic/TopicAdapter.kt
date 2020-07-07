package com.moreyeah.test.presentation.ui.topic

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moreyeah.test.R
import com.moreyeah.test.domain.mapper.Phrase
import javax.inject.Inject


class TopicAdapter @Inject constructor(val mTopicList: MutableList<Phrase>, val mContext: Context) : RecyclerView.Adapter<TopicAdapter.MovieViewHolder>() {


    val mLayoutInflater = LayoutInflater.from(mContext);
    private lateinit var listener: OnTopicClickListener

    override fun getItemCount(): Int {
        return mTopicList.size
    }

    fun setListener(mCallback: OnTopicClickListener) {
        listener = mCallback
    }

    fun addItems(mList: ArrayList<Phrase>?) {
        if (mList != null) {
            clearItems()
            mTopicList.addAll(mList)
            notifyDataSetChanged()
        }
    }


    private fun clearItems() {
        mTopicList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_topic_view, parent, false)
        return MovieViewHolder(view)
    }

    // stores and recycles views as they are scrolled off screen
    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myImgView: ImageView
        var myTitleView: TextView

        init {
            myImgView = itemView.findViewById(R.id.topicImag) as ImageView
            myTitleView = itemView.findViewById(R.id.textViewItemTitle) as TextView
        }

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val topicPoster = mTopicList[position].imageUrl
        if (topicPoster != null)
            Glide.with(mContext)
                    .load(Uri.parse(topicPoster))
                    .into(holder.myImgView)
        holder.myTitleView.text = mTopicList[position].name
        holder.myImgView.setOnClickListener {
            listener.onItemClick()
        }
    }

    interface OnTopicClickListener {
        fun onItemClick()
    }

}
