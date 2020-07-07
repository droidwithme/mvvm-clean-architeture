package com.moreyeah.test.presentation.ui.countrylist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moreyeah.test.R
import com.moreyeah.test.domain.mapper.Country
import java.util.*
import javax.inject.Inject


class CountryAdapter @Inject constructor(val mCountryList: MutableList<Country>, val mContext: Context) : RecyclerView.Adapter<CountryAdapter.MovieViewHolder>() {


    val mLayoutInflater = LayoutInflater.from(mContext);

    override fun getItemCount(): Int {
        return mCountryList.size
    }

    fun addItems(mList: ArrayList<Country>?) {
        if (mList != null) {
            clearItems()
            mCountryList.addAll(mList)
            notifyDataSetChanged()
        }
    }


    private fun clearItems() {
        mCountryList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_view_country, parent, false)
        return MovieViewHolder(view)
    }

    // stores and recycles views as they are scrolled off screen
    inner class MovieViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mCountryTextViewTime: TextView = itemView.findViewById(R.id.textViewCountryTime) as TextView
        var mCountryTextViewName: TextView = itemView.findViewById(R.id.textViewCountryName) as TextView

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val timeZone = TimeZone.getTimeZone(mCountryList[position].timeZone)
        val c = Calendar.getInstance(timeZone)
        holder.mCountryTextViewTime.text = getTime(c)
        holder.mCountryTextViewName.text = mCountryList[position].name
    }

    private fun getTime(c: Calendar): String {
        return String.format("%02d", c.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", c.get(Calendar.MINUTE)) + ":" + String.format("%02d", c.get(Calendar.SECOND))
    }
}
