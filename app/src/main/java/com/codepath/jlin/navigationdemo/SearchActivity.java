package com.codepath.jlin.navigationdemo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.jlin.navigationdemo.helper.DemoHelper;
import com.codepath.jlin.navigationdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ContactAdapter.Listener {

    List<Contact> mData = new ArrayList<>();
    ContactAdapter mAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mData = DemoHelper.createContactList();
        mAdapter = new ContactAdapter(this, this, R.layout.list_item, mData);
        mListView = (ListView) findViewById(R.id.lvContacts);
        mListView.setAdapter(mAdapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                finishActivityWithResult(position);
//            }
//        });
    }

    private void finishActivityWithResult(int position) {
        Contact contact = mData.get(position);
        Intent data = new Intent();
        data.putExtra("contact", contact);
        data.putExtra("code", MainActivity.REQUEST_CODE);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClickContact(int position) {
        finishActivityWithResult(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miAdd:
                Toast.makeText(this, "Add this feature", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
