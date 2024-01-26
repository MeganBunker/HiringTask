package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.String.format
import java.text.DateFormat.getDateInstance
import java.util.Locale

class FixturesAdapter(private val fixtures: List<MatchEntity>) :
    RecyclerView.Adapter<FixturesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fixture_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = fixtures[position]
        holder.itemView.apply {
            val formatter = getDateInstance()// modify format
            val date = format(Locale.ENGLISH, match.matchInfo?.date.toString(), formatter)
            findViewById<TextView>(R.id.Date).text = date
            findViewById<TextView>(R.id.Stadium_name).text =
                match.matchInfo?.venue?.longName
            if (match.liveData?.matchStatus == "Played") {
                findViewById<TextView>(R.id.matchStatus_text).text = match.liveData.matchStatus
                findViewById<ImageView>(R.id.matchStatus_icon).setImageResource(R.drawable.icon_match_status_played)
            } else {
                findViewById<TextView>(R.id.matchStatus_text).text = match.liveData?.matchStatus
                findViewById<ImageView>(R.id.matchStatus_icon).setImageResource(R.drawable.icon_match_status_fixture)
            }
        }
    }

    override fun getItemCount(): Int {
        return fixtures.size
    }
}