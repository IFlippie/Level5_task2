package com.iflippie.level5_task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.iflippie.level5_task2.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Game::class], version = 2, exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java, DATABASE_NAME
                        ).fallbackToDestructiveMigration()
                            .addCallback(object: RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database?.gameDao()?.insertGame(Game("Modern Warfare", "PC, PS4", 1, 10, 2019))
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
