package com.nexos.bibliotecanacional.model.book

import com.nexos.bibliotecanacional.model.datasource.BookDataSourceFactory

object BookModelFactory {
    fun createInstance(): BookModelInterface {
        return BookModel(BookDataSourceFactory.createInstance())
    }
}