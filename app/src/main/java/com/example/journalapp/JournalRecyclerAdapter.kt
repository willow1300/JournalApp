package com.example.journalapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.journalapp.databinding.JournalRawItemBinding

class JournalRecyclerAdapter(val context: Context, val journalList: List<Journal>): RecyclerView.Adapter<JournalRecyclerAdapter.MyViewHolder>() {

    private lateinit var binding: JournalRawItemBinding


    class MyViewHolder(private var binding: JournalRawItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(journal: Journal){
            binding.journal = journal
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
//        val view = LayoutInflater.from(context)
//            .inflate(R.layout.journal_raw_item, p0, false)

        binding = JournalRawItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int = journalList.size

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
        val journal = journalList[p1]

        holder.bind(journal)

    }
}