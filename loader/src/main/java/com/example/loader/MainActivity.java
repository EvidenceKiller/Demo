package com.example.loader;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mAdd;
    private TextView mRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdd = (TextView) findViewById(R.id.tv_add);
        mAdd.setOnClickListener(this);
        mRemove = (TextView) findViewById(R.id.tv_remove);
        mRemove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_add:
                break;
            case R.id.tv_remove:
                
        }
    }
}
