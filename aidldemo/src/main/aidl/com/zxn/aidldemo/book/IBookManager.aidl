// IBookManager.aidl
package com.zxn.aidldemo.book;

// Declare any non-default types here with import statements
import com.zxn.aidldemo.book.Book;
import com.zxn.aidldemo.book.IOnNewBookArrivedListener;

interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(IOnNewBookArrivedListener listener);

    void unregisterListener(IOnNewBookArrivedListener listener);
}
