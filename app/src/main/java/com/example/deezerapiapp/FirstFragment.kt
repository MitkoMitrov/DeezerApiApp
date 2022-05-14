package com.example.deezerapiapp

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deezerapiapp.adapters.TracksAdapter
import com.example.deezerapiapp.api.DeezerApi
import com.example.deezerapiapp.api.DeezerApiClient
import com.example.deezerapiapp.databinding.FragmentFirstBinding
import com.example.deezerapiapp.models.Data
import com.example.deezerapiapp.models.Playlist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var deezerApiClient: DeezerApi

    private lateinit var textViewPlaylistName: TextView
    private lateinit var imageViewPlaylist: ImageView
    private lateinit var trackRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: TracksAdapter

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        deezerApiClient = DeezerApiClient.getDeezerApi()!! // in any case this object will not be nullable


        val playlistId: EditText = view.findViewById<EditText>(R.id.editTextPlaylist)
        textViewPlaylistName = view.findViewById(R.id.textViewPlaylistName)
        imageViewPlaylist  = view.findViewById(R.id.imageViewPlaylist)

        trackRecyclerView = view.findViewById(R.id.allTracksRecyclerView)

        trackRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = TracksAdapter(mutableListOf<Data>())

        trackRecyclerView.setHasFixedSize(true)

        trackRecyclerView.adapter = recyclerViewAdapter


        playlistId.setOnEditorActionListener{v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                val listId: String = playlistId.text.toString()
                searchPlaylistById(listId)
                true
            }else{
                Toast.makeText(activity, "Errror!", Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    private fun searchPlaylistById(id: String) {
        deezerApiClient.getPlaylistById(id).enqueue(object : Callback<Playlist>{
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                displayData(response.body())
                if(response.code() == 200){
                    Toast.makeText(activity, "Success!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                Toast.makeText(activity, "Error happened!", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun displayData(data: Playlist?) {
        textViewPlaylistName.text = data?.title
        Glide.with(this).load(data?.picture).into(imageViewPlaylist)
        recyclerViewAdapter.updateData(data?.tracks!!.data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}