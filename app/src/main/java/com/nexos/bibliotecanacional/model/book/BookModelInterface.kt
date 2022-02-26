package com.nexos.bibliotecanacional.model.book

import com.nexos.bibliotecanacional.model.data.BookDetailItem
import com.nexos.bibliotecanacional.model.data.BookListItem
import io.reactivex.rxjava3.core.Flowable

interface BookModelInterface {

    fun getNewBookListItems(): Flowable<MutableList<BookListItem>>

    fun getBookDetailItem(isbn13: String): Flowable<BookDetailItem>

    fun getBookListByKeywords(keywords: List<String>, startPage:Int, minNeedItemCnt:Int): Flowable<Pair<Int, MutableList<BookListItem>>>

    fun getBookListByIncludeKeywordsAndExcludeKeywords(includeKeywords: List<String>, excludeKeywords:List<String>,
                                                       startPage:Int, minNeedItemCnt: Int): Flowable<Pair<Int, MutableList<BookListItem>>>
}