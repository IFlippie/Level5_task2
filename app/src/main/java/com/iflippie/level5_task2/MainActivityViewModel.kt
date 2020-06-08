package com.iflippie.level5_task2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iflippie.level5_task2.database.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {


    private val gameRepository = GameRepository(application.applicationContext)
    val games = gameRepository.getAllGamesInOrder()

    fun deleteGames() {
        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).launch {
                gameRepository.deleteAllGames()
            }
        }
    }
}