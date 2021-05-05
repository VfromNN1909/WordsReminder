package ru.vlasoff.wordreminder.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.vlasoff.wordreminder.databinding.FragmentAllWordsBinding
import ru.vlasoff.wordreminder.domain.entities.Word
import ru.vlasoff.wordreminder.presentation.adapters.AllWordsAdapter
import ru.vlasoff.wordreminder.presentation.viewmodels.AllWordsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AllWordsFragment : Fragment() {

    private val binding: FragmentAllWordsBinding by lazy {
        FragmentAllWordsBinding.inflate(layoutInflater)
    }

    private val viewModel: AllWordsViewModel by viewModels()

    private val allWordsAdapter = AllWordsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewAllWords.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = allWordsAdapter
        }

        subscribeUi(allWordsAdapter)

        binding.fabAddWord.setOnClickListener {
            showWordDialog()
        }

        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ) = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    viewModel.delete(
                        allWordsAdapter.getWordByPosition(viewHolder.adapterPosition)
                    )
                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewAllWords)
    }

    private fun subscribeUi(adapter: AllWordsAdapter) {
        viewModel.words.observe(viewLifecycleOwner) { words ->
            adapter.submitList(words)
        }
    }

    private fun showWordDialog() {
        val etEng = EditText(requireContext()).apply {
            hint = "English"
            marginBottom.plus(15)
        }
        val etRus = EditText(requireContext()).apply {
            hint = "Russian"
        }

        val ll = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
        }
        ll.addView(etEng)
        ll.addView(etRus)

        val dialog = AlertDialog.Builder(requireContext()).apply {
            setTitle("Добавить слово")
            setView(ll)
            setPositiveButton("Добавить") { dialog, _ ->
                val wordInEng = etEng.editableText.toString()
                val wordInRus = etRus.editableText.toString()
                val word = Word(wordInEng, wordInRus)
                if (wordInEng.isBlank() or wordInRus.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Все поля должны быть заполнены!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.insert(word)
                    dialog.dismiss()
                }

            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}