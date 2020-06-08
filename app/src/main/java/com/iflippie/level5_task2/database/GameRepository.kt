package com.iflippie.level5_task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.iflippie.level5_task2.model.Game

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames()
    }

    fun getAllGamesInOrder(): LiveData<List<Game>> {
        return gameDao.getAllGamesInDateOrder()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    // TODO: Delete all games
    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }
}