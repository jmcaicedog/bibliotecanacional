package com.nexos.bibliotecanacional.model.datasource

import com.nexos.bibliotecanacional.model.data.BookDetailItem
import com.nexos.bibliotecanacional.model.data.BookListItem

interface BookDataSourceInterface {

    fun getBookDetailItem(isbn13: String): BookDetailItem

    fun getBookListItemsByKeyword(keyword: String, pageNum: Int): MutableList<BookListItem>

    fun getNewBookListItems(): MutableList<BookListItem>
}