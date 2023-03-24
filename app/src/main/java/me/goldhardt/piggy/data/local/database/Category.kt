package me.goldhardt.piggy.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class Category(
    val name: String,
    val emoji: String,
    val color: CategoryColor,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category ORDER BY name ASC")
    fun getCategories(): Flow<List<Category>>

    @Insert
    suspend fun insertCategory(category: Category)
}