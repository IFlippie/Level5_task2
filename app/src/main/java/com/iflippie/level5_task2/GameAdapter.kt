package com.iflippie.level5_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iflippie.level5_task2.model.Game
import kotlinx.android.synthetic.main.game_item.view.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    private var games: List<Game> = emptyList()

    fun setNewList(newGames: List<Game>) {
        games = newGames
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvGameName.text = game.title
            itemView.tvPlatform.text = game.platforms
            val month = Conversions.fromIntToMonth(game.releaseMonth)
            itemView.tvReleaseDate.text = String.format(itemView.context.getString(R.string.release_date), game.releaseDay, month, game.releaseYear)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        )
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }
}