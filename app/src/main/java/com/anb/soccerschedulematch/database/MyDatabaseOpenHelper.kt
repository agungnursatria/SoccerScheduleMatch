package com.anb.soccerschedulematch.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.anb.soccerschedulematch.model.match.Match
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Match.TABLE_MATCH, true,
                Match.ID_EVENT to TEXT + PRIMARY_KEY,
                Match.HOME_NAME to TEXT,
                Match.AWAY_NAME to TEXT,
                Match.HOME_SCORE to TEXT,
                Match.AWAY_SCORE to TEXT,
                Match.HOME_GOAL_DETAILS to TEXT,
                Match.HOME_RED_CARDS to TEXT,
                Match.HOME_YELLOW_CARDS to TEXT,
                Match.HOME_GOALKEEPER to TEXT,
                Match.HOME_DEFENDER to TEXT,
                Match.HOME_MIDFIELD to TEXT,
                Match.HOME_FORWARD to TEXT,
                Match.HOME_SUBTITUTE to TEXT,
                Match.AWAY_RED_CARDS to TEXT,
                Match.AWAY_YELLOW_CARDS to TEXT,
                Match.AWAY_GOAL_DETAILS to TEXT,
                Match.AWAY_GOALKEEPER to TEXT,
                Match.AWAY_DEFENDER to TEXT,
                Match.AWAY_MIDFIELD to TEXT,
                Match.AWAY_FORWARD to TEXT,
                Match.AWAY_SUBTITUTE to TEXT,
                Match.HOME_SHOTS to TEXT,
                Match.AWAY_SHOTS to TEXT,
                Match.DATE_EVENT to TEXT,
                Match.ID_HOME to TEXT,
                Match.ID_AWAY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Match.TABLE_MATCH, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)