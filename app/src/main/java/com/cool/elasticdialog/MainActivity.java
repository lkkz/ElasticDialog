package com.cool.elasticdialog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cool.elasticlibrary.ElasticDialog;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private ElasticDialog elasticDialog;
    private String[] titles = { "科大讯飞", "QQ音乐", "网易云音乐", "美图秀秀",
            "360手机卫士", "QQ影音","百度输入法", "微信", "最美应用", "支付宝", "蘑菇街"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        initDialog();
        initRecyclerView();
    }

    private void initDialog() {
        if(elasticDialog== null) {
            elasticDialog = new ElasticDialog(this);
            elasticDialog.layout(R.layout.dialog_elastic)
                    .arcColor(Color.WHITE)
                    .duration(1000)
                    .arcHight(40);
            mRecyclerView = elasticDialog.findViewById(R.id.recyclerview);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ElasticAdapter elasticAdapter = new ElasticAdapter(this,titles);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(elasticAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.action_screen){
            elasticDialog.show();
        }
        return false;
    }
}
