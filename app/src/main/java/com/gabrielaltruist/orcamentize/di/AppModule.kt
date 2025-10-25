package com.gabrielaltruist.orcamentize.di

import android.app.Application
import androidx.room.Room
import com.gabrielaltruist.orcamentize.data.OrcamentizeDatabase
import com.gabrielaltruist.orcamentize.material_feature.data.local.MaterialDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOrcamentizeDatabase(app: Application): OrcamentizeDatabase {
        return Room.databaseBuilder(
            app,
            OrcamentizeDatabase::class.java,
            "orcamentize_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMaterialDao(db: OrcamentizeDatabase): MaterialDao {
        return db.materialDao()
    }
}
