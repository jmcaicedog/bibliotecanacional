package com.nexos.bibliotecanacional.view.bookdetail

import com.nexos.bibliotecanacional.model.data.BookDetailItem

interface BookDetailContract {
    interface View {
        fun updateDetailBookItemView(bookDetailItem: BookDetailItem)
        fun showProgressUi()
        fun hideProgressUi()

        fun showErrorMessage(msg: String)
    }

    interface Presenter {
        fun loadDetailBookItem(isbn13: String)
        fun dispose()
    }
}