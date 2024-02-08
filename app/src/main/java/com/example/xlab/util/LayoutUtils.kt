package com.LayoutUtil.utils // замените на ваш пакет
import com.example.xlab.R // импортируйте R вашего проекта

object LayoutUtils {
    fun getLayoutResIdByArticleId(articleId: Int): Int {
        return when (articleId) {
//            1 -> R.layout.article_1
//            2 -> R.layout.article_2
//            3 ->
//            4 ->
              21 -> R.layout.harmful_1
              22 -> R.layout.harmful_2
              23 -> R.layout.harmful_3
              24 -> R.layout.harmful_4
              25 -> R.layout.harmful_5
              26 -> R.layout.harmful_6
              27 -> R.layout.harmful_7
//            18
//            19
//            20
//              21 -> R.layout.harmful_1
//            22
//            23
//            24
//            25
//            26
//            27
//            28
//            29
//            30

            else -> R.layout.fragment_main_category
        }
    }
}
