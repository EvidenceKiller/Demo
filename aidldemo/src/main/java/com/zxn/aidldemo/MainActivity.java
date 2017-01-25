package com.zxn.aidldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zxn.aidldemo.book.Book;
import com.zxn.aidldemo.book.BookManagerService;
import com.zxn.aidldemo.book.IBookManager;
import com.zxn.aidldemo.book.IOnNewBookArrivedListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ManiActivity";

    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.i(TAG, "Handler : current thread : " + Thread.currentThread());
                    Log.i(TAG, "receive new book : " + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                mRemoteBookManager = bookManager;
                List<Book> list = bookManager.getBookList();
                Log.i(TAG, "query book list, list type : " + list.getClass());
                Log.i(TAG, "query book list : " + list.toString());
                Book newBook = new Book(3, "Android Art Development");
                bookManager.addBook(newBook);
                List<Book> newList = bookManager.getBookList();
                Log.i(TAG, "query book newList : " + newList.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBookManager = null;
            Log.e(TAG, "binder died");
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.i(TAG, "IOnNewBookArrivedListener : current Thread : " + Thread.currentThread());
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (null != mRemoteBookManager && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(TAG, "unregister listener : " + mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
