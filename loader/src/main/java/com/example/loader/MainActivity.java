package com.example.loader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CursorLoaderListFragment mFragment;

    private TextView mAdd;
    private TextView mRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = (CursorLoaderListFragment) getFragmentManager().findFragmentById(R.id.fragment_list);

        mAdd = (TextView) findViewById(R.id.tv_add);
        mAdd.setOnClickListener(this);
        mRemove = (TextView) findViewById(R.id.tv_remove);
        mRemove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_add:
                mFragment.add(getContentResolver());
                break;
            case R.id.tv_remove:
                mFragment.remove(getContentResolver());
                break;
            default:
                break;
        }
    }
}
