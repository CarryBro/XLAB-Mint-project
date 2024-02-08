package com.example.xlab.fragments.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.LayoutUtil.utils.LayoutUtils
import com.example.xlab.activities.ArticleActivity
import com.example.xlab.databinding.FragmentHarmfulCategoryBinding

class HarmfulCategory : Fragment(), MinesAdapter.OnMineClickListener {
    private var _binding: FragmentHarmfulCategoryBinding? = null
    private val binding get() = _binding!!
    private val adapter = MinesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHarmfulCategoryBinding.inflate(inflater, container, false)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(context, 2)

        // Устанавливаем список hmines для HarmfulCategory
        adapter.setCategory(MinesAdapter.Category.PESTS)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Освобождаем ссылку на binding
    }

    override fun onMineClick(mine: Mine) {
        val layoutResId = LayoutUtils.getLayoutResIdByArticleId(mine.articleId)
        startActivity(Intent(context, ArticleActivity::class.java).apply {
            putExtra("article_id", mine.articleId) // Убедитесь, что ключ "article_id" используется консистентно
        })
    }
}
