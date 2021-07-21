package com.example.glc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GamesAdapter(val games: List<Game>) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    class GameViewHolder(card: View) : RecyclerView.ViewHolder(card) {

        var titleTextView: TextView = card.findViewById(R.id.title_tv)
        val currentGameButton: Button = card.findViewById(R.id.current_add_btn)
        val pastGameButton: Button = card.findViewById(R.id.past_add_btn)
        val futureGameButton: Button = card.findViewById(R.id.future_add_btn)


    }



    override fun onBindViewHolder(holder: GameViewHolder, index: Int) {
        val game = games[index]
        holder.titleTextView.text = game.title

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_add_card, parent, false)

        return GameViewHolder(view)
    }



    override fun getItemCount() = games.size



}
