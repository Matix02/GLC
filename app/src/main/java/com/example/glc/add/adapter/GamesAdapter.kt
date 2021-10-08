package com.example.glc.add.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.glc.add.model.GameItem
import com.example.glc.databinding.SingleAddCardBinding

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    inner class GameViewHolder(val binding: SingleAddCardBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<GameItem>() {
        override fun areItemsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameItem, newItem: GameItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var games: List<GameItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            SingleAddCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.apply {
            val game = games[position]
            titleTv.text = game.name
        }
    }

    override fun getItemCount() = games.size
}
