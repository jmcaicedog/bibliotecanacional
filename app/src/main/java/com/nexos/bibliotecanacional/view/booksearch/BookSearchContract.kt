package com.nexos.bibliotecanacional.view.booksearch

import com.nexos.bibliotecanacional.model.data.BookListItem

interface BookSearchContract {
    interface View {
        fun initSearchList(bookItemList: MutableList<BookListItem>)
        fun updateInsertedSearchListItemRange(startPos:Int, size:Int)
        fun updateInsertedSearchListItem(position: Int)
        fun updateRemovedSearchListItem(position: Int)
        fun updateAllSearchListItem()
        fun showInvalidQueryMessage(msg: String)
        fun showProgressUi()
        fun hideProgressUi()
        fun showNoSearchResultUi()
        fun hideNoSearchResultUi()

        fun showErrorMessage(msg: String)
    }
    interface Presenter {
        fun loadBookItemsBySearch(queryText: String): Boolean
        fun loadNextBookItems(initialLoad: Boolean): Boolean
        fun dispose()
    }
}