package com.example.xlab.fragments.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.xlab.R
import com.example.xlab.databinding.MineVhBinding

data class Mine(
    @DrawableRes val image: Int,
    val text: String,
    val articleId: Int // Уникальный ID для каждой статьи
)



class MineVH(view: View) : RecyclerView.ViewHolder(view) {
    val binding = MineVhBinding.bind(itemView)
}

class MinesAdapter(private val onMineClickListener: OnMineClickListener) : RecyclerView.Adapter<MineVH>(){
    interface OnMineClickListener {
        fun onMineClick(mine: Mine)
    }
    enum class Category {
        ARTICLES, PESTS, BENEFICIAL
    }

    private val mines = listOf(
        Mine(R.drawable.testmine, "Art 1", 1),
        Mine(R.drawable.testmine, "Art 2", 2),
        Mine(R.drawable.testmine, "Art 3", 3),
        Mine(R.drawable.testmine, "Art 4", 4),
    )
    private val hmines = listOf(
        Mine(R.drawable.testmine, "HMine 1", 11),
        Mine(R.drawable.testmine, "HMine 2", 12),
        Mine(R.drawable.testmine, "HMine 3", 13),
        Mine(R.drawable.testmine, "HMine 4", 14),
        Mine(R.drawable.testmine, "HMine 5", 15),
        Mine(R.drawable.testmine, "HMine 6", 16),
        Mine(R.drawable.testmine, "HMine 7", 17),
        Mine(R.drawable.testmine, "HMine 8", 18),
        Mine(R.drawable.testmine, "HMine 9", 19),
        Mine(R.drawable.testmine, "HMine 10", 20),
    )

    private val bmines = listOf(
        Mine(R.drawable.harmful_1, "Steneotarsonemus panshini", 21),
        Mine(R.drawable.harmful_21, "Tarsonemus myceliophagus", 22),
        Mine(R.drawable.harmful_31, "Tyrophagus putrescentiae", 23),
        Mine(R.drawable.harmful_41, "Siteroptes ceralium", 24),
        Mine(R.drawable.harmful_51, "Brevipalpus ovobatus", 25),
        Mine(R.drawable.testmine, "Eriophyes pyri", 26),
        Mine(R.drawable.harmful_71, "Tetranychus urticae", 27),
        Mine(R.drawable.testmine, "BMine 8", 28),
        Mine(R.drawable.testmine, "BMine 9", 29),
        Mine(R.drawable.testmine, "BMine 10", 29),
    )

    private var currentList = mines
    fun setCategory(category: Category) {
        currentList = when (category) {
            Category.ARTICLES -> mines
            Category.BENEFICIAL -> hmines
            Category.PESTS -> bmines
        }
        notifyDataSetChanged() // Обязательно уведомляем адаптер о том, что данные изменились
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineVH {
        return MineVH(
            LayoutInflater.from(parent.context).inflate(R.layout.mine_vh, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: MineVH, position: Int) {
        val item = currentList[position]
        holder.binding.imageButton1.setImageDrawable(holder.binding.root.resources.getDrawable(item.image))
        holder.binding.textView1.text = item.text
        holder.binding.imageButton1.setOnClickListener {
            onMineClickListener.onMineClick(item)
        }
    }
}