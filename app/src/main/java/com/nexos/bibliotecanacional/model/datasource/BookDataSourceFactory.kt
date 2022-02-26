package com.nexos.bibliotecanacional.model.datasource

object BookDataSourceFactory {
    fun createInstance(): BookDataSourceInterface {
        return BookHttpDataSource()
    }
}