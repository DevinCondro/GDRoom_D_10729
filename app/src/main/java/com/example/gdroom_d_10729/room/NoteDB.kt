package com.example.gdroom_d_10729.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB: RoomDatabase(), NoteDao {
    abstract fun noteDao() : NoteDao

    companion object{

        @Volatile private var instance : NoteDao? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "note12345.db"
            ).build()
    }
}