package com.iflippie.level5_task2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iflippie.level5_task2.model.Game

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: Game)

    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM gameTable ORDER BY releaseYear, releaseMonth, releaseYear DESC")
    fun getAllGamesInDateOrder(): LiveData<List<Game>>

    @Query("DELETE FROM gameTable")
    suspend fun deleteAllGames()
}