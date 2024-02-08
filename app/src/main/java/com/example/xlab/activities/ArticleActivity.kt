package com.example.xlab.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.LayoutUtil.utils.LayoutUtils
import com.example.xlab.R

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articleId = intent.getIntExtra("article_id", -1) // Используйте тот же ключ "article_id"
        val layoutResId = LayoutUtils.getLayoutResIdByArticleId(articleId)
        setContentView(layoutResId)
        // Другая инициализация...
    }
}