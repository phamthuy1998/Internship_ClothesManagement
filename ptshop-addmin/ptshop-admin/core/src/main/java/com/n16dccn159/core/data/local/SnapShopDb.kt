package com.n16dccn159.core.data.local

import androidx.room.RoomDatabase

//@Database(
//    entities = [Categories::class],
//    version = 1,
//    exportSchema = false
//)

abstract class SnapShopDb: RoomDatabase() {
    abstract fun productDao(): ProductDao
}