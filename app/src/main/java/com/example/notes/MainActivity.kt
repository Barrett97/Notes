package com.example.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import androidx.navigation.NavController
import com.example.notes.adapter.NoteRecyclerAdapter

/*
Functionality
- Create, edit, and delete multiple multi-line, free-text notes.
- Notes should be displayed in groups by their priority (high, normal, low).
- Allow note priority and sort order to be changed by moving them from group to group.

Advanced Requirements
- Add ability to do a “live” search through notes by their content.
 */

class MainActivity : AppCompatActivity() {

    lateinit var navControl : NavController
    lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRecyclerAdapter: NoteRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

//        initRecyclerView()
//        getNotes()
    }

//    private fun initRecyclerView() {
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            noteRecyclerAdapter = NoteRecyclerAdapter()
//            adapter = noteRecyclerAdapter;
//        }
//    }
//
//    private fun getNotes() {
//        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
//        noteViewModel.liveNotes.observe(this, Observer { notes ->
//            notes?.let {
//                noteRecyclerAdapter.setNote(it)
//            }
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}