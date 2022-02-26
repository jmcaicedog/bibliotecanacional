package com.nexos.bibliotecanacional.view.bookdetail

import com.nexos.bibliotecanacional.model.book.BookModelFactory
import com.nexos.bibliotecanacional.model.book.BookModelInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BookDetailPresenter(private val view: BookDetailContract.View): BookDetailContract.Presenter {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val bookModel: BookModelInterface = BookModelFactory.createInstance()

    override fun loadDetailBookItem(isbn13: String) {
        view.showProgressUi()
        val disposable = bookModel.getBookDetailItem(isbn13)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete {
                view.showErrorMessage("Error: $it")
                true
            }
            .doFinally{
                view.hideProgressUi()
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                if(it != null) view.updateDetailBookItemView(it)
            }
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}