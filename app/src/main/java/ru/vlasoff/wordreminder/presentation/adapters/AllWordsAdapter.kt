package ru.vlasoff.wordreminder.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vlasoff.wordreminder.databinding.RecyclerviewWordItemBinding
import ru.vlasoff.wordreminder.domain.entities.Word


class AllWordsAdapter : ListAdapter<Word, AllWordsAdapter.ViewHolder>(Diffcallback) {

    inner class ViewHolder(private val binding: RecyclerviewWordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(
            RecyclerviewWordItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

        fun bind(word: Word) {
            binding.tvWordInEng.text = word.wordInEng
            binding.tvWordInRus.text = word.wordInRus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object Diffcallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return (oldItem.wordInEng == newItem.wordInEng) and (oldItem.wordInRus == newItem.wordInRus)
        }

    }

    fun getWordByPosition(position: Int): Word = getItem(position)
}