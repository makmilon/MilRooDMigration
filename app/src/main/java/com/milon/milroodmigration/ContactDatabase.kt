package com.milon.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {

        //for database migration
       val migration_1_2=object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

        //for create database instance single ton pattern
        @Volatile
        private var INSTANCE: ContactDatabase?=null

        fun getDatabase(context: Context):ContactDatabase{

          if (INSTANCE==null){
              synchronized(this){

              }
              INSTANCE= Room.databaseBuilder(context.applicationContext,
                  ContactDatabase::class.java,
                  "contactDB")
                  .build()
          }
            return INSTANCE!!
        }
    }

}