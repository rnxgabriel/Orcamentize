package com.gabrielaltruist.orcamentize.data

import MaterialEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielaltruist.orcamentize.material_feature.data.local.MaterialDao

@Database(
    entities = [MaterialEntity::class],
    version = 1
)
abstract class OrcamentizeDatabase: RoomDatabase() {
    abstract fun materialDao(): MaterialDao
}