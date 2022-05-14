package com.example.deezerapiapp.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.deezerapiapp.models.Data
import com.example.deezerapiapp.models.Playlist

data class PlaylistWithTracks (
    @Embedded
    val playlist: Playlist,
    @Relation(
        parentColumn = "id",
        entityColumn = "playListId"
    )
    val tracks: MutableList<Data>
)