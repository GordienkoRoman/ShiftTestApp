package com.example.shifttestapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.shifttestapp.data.local.AppDataBase
import com.example.shifttestapp.data.local.UsersDao
import com.example.shifttestapp.di.components.AppScope
import com.example.shifttestapp.domain.model.RoomRepository
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {
    @AppScope
    @Provides
    fun provideAppDatabase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    }
    @AppScope
    @Provides
    fun provideUsersDao(appDataBase: AppDataBase): UsersDao = appDataBase.usersDao

    @AppScope
    @Provides
    fun provideRoomRepository(usersDao: UsersDao, context: Context):RoomRepository {
        return RoomRepository(usersDao = usersDao, context = context)
    }
}