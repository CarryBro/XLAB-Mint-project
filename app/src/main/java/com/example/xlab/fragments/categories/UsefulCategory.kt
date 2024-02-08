package com.example.xlab.fragments.categories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.LayoutUtil.utils.LayoutUtils
import com.example.xlab.databinding.FragmentMainCategoryBinding
 import com.example.xlab.activities.ArticleActivity

class UsefulCategoryFragment : Fragment() {
    private var _binding: FragmentMainCategoryBinding? = null
    private val binding get() = _binding!!

    private val adapter = MinesAdapter(object : MinesAdapter.OnMineClickListener {
        override fun onMineClick(mine: Mine) {
            // Открываем статью при нажатии на элемент
            val layoutResId = LayoutUtils.getLayoutResIdByArticleId(mine.articleId)
            startActivity(Intent(context, ArticleActivity::class.java).apply {
                putExtra("layout_res_id", layoutResId)
            })
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainCategoryBinding.inflate(inflater, container, false)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(context, 2)

        // Устанавливаем список bmines для UsefulCategory
        adapter.setCategory(MinesAdapter.Category.BENEFICIAL)

        // Принудительное обновление разметки RecyclerView
        binding.recycler.post {
            binding.recycler.requestLayout()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Освобождаем ссылку на binding для предотвращения утечек памяти
    }
}
