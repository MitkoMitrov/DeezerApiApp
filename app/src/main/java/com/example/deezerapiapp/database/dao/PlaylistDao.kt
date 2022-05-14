package com.example.deezerapiapp.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.deezerapiapp.models.Data
import com.example.deezerapiapp.models.Playlist

abstract class PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPLayList(playList: Playlist)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTracks(data: MutableList<Data>)


    fun insertPlayListWithTracks(playList: Playlist, tracks: MutableList<Data>){
        try{
            insertPLayList(playList)
            for(track in tracks){
                track.playListId = playList.id
            }
            insertTracks(tracks)

        }catch(ex: Exception){
            ex.printStackTrace()
        }
    }
}