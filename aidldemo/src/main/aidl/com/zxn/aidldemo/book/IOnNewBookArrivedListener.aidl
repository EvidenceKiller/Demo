// IOnNewBookArrivedListener.aidl
package com.zxn.aidldemo.book;

import com.zxn.aidldemo.book.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {

    void onNewBookArrived(in Book newBook);

}
