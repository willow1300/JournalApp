package com.example.journalapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.journalapp.databinding.ActivityJournalListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

class JournalList : AppCompatActivity() {
    private lateinit var binding: ActivityJournalListBinding

    //Firebase references
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var user: FirebaseUser
    var db = FirebaseFirestore.getInstance()
    lateinit var storageReference: StorageReference
    var collectionReference: CollectionReference =db.collection("Journal")

    private lateinit var journalList: List<Journal>
    private lateinit var adapter: JournalRecyclerAdapter

    lateinit var noPostTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journal_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //initialize firebase auth
        firebaseAuth = Firebase.auth
        user = firebaseAuth.currentUser!!

        //Recycler view
        binding.journalRecycleView.setHasFixedSize(true)
        binding.journalRecycleView.layoutManager = LinearLayoutManager(this)

        //initialize journal list
        journalList = arrayListOf()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> if (user != null && firebaseAuth != null){
                val intent = Intent(this@JournalList, AddJournal::class.java)
                startActivity(intent)

            }

            R.id.action_sign_out ->{
                if (user != null && firebaseAuth != null){
                    firebaseAuth.signOut()
                    val intent = Intent(this@JournalList, MainActivity::class.java)
                    startActivity(intent)

                }
            }
        }

        return super.onOptionsItemSelected(item)

    }

}