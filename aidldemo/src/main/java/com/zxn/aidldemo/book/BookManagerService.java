package com.zxn.aidldemo.book;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User : user
 * Date : 2017-01-23
 * Time : 16:34
 */

public class BookManagerService extends Service {

    private static final String TAG = "BookManagerService";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.i(TAG, "getBookList : current thread : " + Thread.currentThread());
            Log.i(TAG, "get book list : " + mBookList.toString());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.i(TAG, "add book : " + book.toString());
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (null != listener) {
                mListenerList.register(listener);
                Log.i(TAG, "register listener succeed");
            } else {
                Log.w(TAG, "listener already exists");
            }
            final int n = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.i(TAG, "registerListener size : " + n);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (null != listener && mListenerList.unregister(listener)) {
                Log.i(TAG, "unregister listener succeed");
            } else {
                Log.w(TAG, "not found, can not unregister");
            }
            final int n = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.i(TAG, "unregisterListener size : " + n);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Andorid"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServiceTask()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private class ServiceTask implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException re) {
                    re.printStackTrace();
                }
            }
        }
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        final int n = mListenerList.beginBroadcast();
        Log.i(TAG, "onNewBookArrived, notify listeners : " + n);
        for (int i = 0; i < n; i++) {
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            if (null != listener) {
                Log.i(TAG, "onNewBookArrived, notify listener : " + listener);
                Log.i(TAG, "onNewBookArrived : current thread : " + Thread.currentThread());
                listener.onNewBookArrived(book);
            }
            mListenerList.finishBroadcast();
        }
    }
}
