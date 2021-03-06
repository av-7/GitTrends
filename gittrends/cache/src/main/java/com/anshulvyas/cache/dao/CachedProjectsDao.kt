package com.anshulvyas.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.anshulvyas.cache.db.ProjectConstants.DELETE_PROJECTS
import com.anshulvyas.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import com.anshulvyas.cache.db.ProjectConstants.QUERY_PROJECTS
import com.anshulvyas.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import com.anshulvyas.cache.model.CachedProject
import io.reactivex.Flowable

@Dao
abstract class CachedProjectsDao {

    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects (projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    // Get list of projects which are bookmarked in our cache
    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkedStatus(isBookmarkProject: Boolean,
                                     projectId: String)

}