package com.example.chefschoice.data.network.local.db
import android.content.Context

import com.example.chefschoice.data.model.MealTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//@Database(entities = [MealTable::class], version = 4)
//abstract class AppRoomDatabase : RoomDatabase(){
//
//    abstract fun iAppDao(): IAppDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppRoomDatabase? = null
//        private val DB_NAME = "chefs_choice_database"
//
//
//
//        fun getDatabase(
//            context: Context,
//            scope: CoroutineScope
//        ): AppRoomDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppRoomDatabase::class.java,
//                    DB_NAME
//                )
//                    // Wipes and rebuilds instead of migrating if no Migration object.
//                    // Migration is not part of this code lab.
//                    .fallbackToDestructiveMigration()
//                    .addCallback(WordDatabaseCallback(scope))
//                    .build()
//
//
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//
//        private class WordDatabaseCallback(
//            private val scope: CoroutineScope
//        ) : RoomDatabase.Callback() {
//            /**
//             * Override the onCreate method to populate the database.
//             */
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//
//                // If you want to keep the data through app restarts,
//                // comment out the following line.
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.iAppDao())
//                    }
//                }
//            }
//
//
//        }
//
//        /**
//         * Populate the database in a new coroutine.
//         * If you want to start with more words, just add them.
//         */
//        suspend fun populateDatabase(iAppDao: IAppDao) {
//
//            var meal = MealTable(0,
//                "1",
//                "Hello",
//                "Hello",
//                "Hello",
//                "Hello",
//                "Hello",
//                "Hello")
//            iAppDao.insertFavorite(meal)
//        }
//
//    }
//
//
//
//}