package com.example.xlab.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.xlab.databinding.FragmentMainCategoryBinding

class MainCategoryFragment : Fragment() {
    private var _binding: FragmentMainCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MinesAdapter // Объявляем адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainCategoryBinding.inflate(inflater, container, false)

        // Инициализируем адаптер
        adapter = MinesAdapter(object : MinesAdapter.OnMineClickListener {
            override fun onMineClick(mine: Mine) {
                // Обработка нажатия на элемент
                // Здесь можно вызвать Activity или выполнить другие действия
            }
        })

        // Настраиваем RecyclerView
        binding.recycler.apply {
            layoutManager = GridLayoutManager(context, 2) // Устанавливаем LayoutManager
            adapter = this@MainCategoryFragment.adapter // Устанавливаем адаптер
        }

        // Устанавливаем категорию для адаптера
        adapter.setCategory(MinesAdapter.Category.ARTICLES)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
