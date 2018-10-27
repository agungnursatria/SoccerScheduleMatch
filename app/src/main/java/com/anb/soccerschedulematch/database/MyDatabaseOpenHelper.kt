package com.anb.soccerschedulematch.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.anb.soccerschedulematch.helper.Constant
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
        db.createTable(Constant.TABLE_MATCH, true,
                Constant.ID_EVENT to TEXT + PRIMARY_KEY,
                Constant.HOME_NAME to TEXT,
                Constant.AWAY_NAME to TEXT,
                Constant.HOME_SCORE to TEXT,
                Constant.AWAY_SCORE to TEXT,
                Constant.HOME_GOAL_DETAILS to TEXT,
                Constant.HOME_RED_CARDS to TEXT,
                Constant.HOME_YELLOW_CARDS to TEXT,
                Constant.HOME_GOALKEEPER to TEXT,
                Constant.HOME_DEFENDER to TEXT,
                Constant.HOME_MIDFIELD to TEXT,
                Constant.HOME_FORWARD to TEXT,
                Constant.HOME_SUBTITUTE to TEXT,
                Constant.AWAY_RED_CARDS to TEXT,
                Constant.AWAY_YELLOW_CARDS to TEXT,
                Constant.AWAY_GOAL_DETAILS to TEXT,
                Constant.AWAY_GOALKEEPER to TEXT,
                Constant.AWAY_DEFENDER to TEXT,
                Constant.AWAY_MIDFIELD to TEXT,
                Constant.AWAY_FORWARD to TEXT,
                Constant.AWAY_SUBTITUTE to TEXT,
                Constant.HOME_SHOTS to TEXT,
                Constant.AWAY_SHOTS to TEXT,
                Constant.DATE_EVENT to TEXT,
                Constant.ID_HOME to TEXT,
                Constant.ID_AWAY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Constant.TABLE_MATCH, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)