package com.nexos.bibliotecanacional.view.booksearch

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexos.bibliotecanacional.R
import com.nexos.bibliotecanacional.model.data.BookListItem
import kotlinx.android.synthetic.main.activity_book_search.*
import kotlinx.android.synthetic.main.book_list_row_item.view.*

class BookSearchActivity : AppCompatActivity(), BookSearchContract.View {
    var presenter: BookSearchContract.Presenter? = null
    var currAdapter: SearchBooksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        supportActionBar?.title = "Biblioteca Nacional - Consulta"


        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        presenter = BookSearchPresenter(this)
        search_result_recycler_view.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        search_view.isIconified = false
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter?.loadBookItemsBySearch(query)
                search_view.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        search_result_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                // Check if end of scroll
                if (!search_result_recycler_view.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    presenter?.loadNextBookItems(false)
                }
            }
        })
    }


    override fun initSearchList(bookItemList: MutableList<BookListItem>) {
        currAdapter = SearchBooksAdapter(dataSet= bookItemList,context = this@BookSearchActivity)
        search_result_recycler_view.adapter = currAdapter
    }
    override fun updateInsertedSearchListItemRange(startPos:Int, size:Int) {
        search_result_recycler_view.post {
            currAdapter?.notifyItemRangeInserted(startPos, size)
        }
    }

    override fun updateInsertedSearchListItem(position: Int) {
        search_result_recycler_view.post {
            currAdapter?.notifyItemInserted(position)
        }
    }
    override fun updateRemovedSearchListItem(position: Int) {
        search_result_recycler_view.post {
            currAdapter?.notifyItemRemoved(position)
        }
    }

    override fun updateAllSearchListItem() {
        search_result_recycler_view.post {
            currAdapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.dispose()
    }

    override fun showInvalidQueryMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showProgressUi() {
        search_result_recycler_view.visibility = RecyclerView.INVISIBLE
        loading_progress_ui.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressUi() {
        loading_progress_ui.visibility = ProgressBar.INVISIBLE
        search_result_recycler_view.visibility = RecyclerView.VISIBLE
    }

    override fun showNoSearchResultUi() {
        search_result_recycler_view.visibility = RecyclerView.INVISIBLE
        loading_progress_ui.visibility = ProgressBar.INVISIBLE
        no_search_result_tv.visibility = TextView.VISIBLE
    }

    override fun hideNoSearchResultUi() {
        no_search_result_tv.visibility = TextView.INVISIBLE
    }



    override fun showErrorMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}