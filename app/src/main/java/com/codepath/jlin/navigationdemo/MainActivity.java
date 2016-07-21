package com.codepath.jlin.navigationdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.jlin.navigationdemo.model.Contact;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 12;
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 13;

    private TextView tvNoContact;
    private LinearLayout layoutNoContact;
    private ImageView ivSearch;
    private TextView tvSearch;
    private ImageView ivMenu;

    private TextView tvNumber;
    private TextView tvNotes;
    private ImageView ivCall;

    private BehaviorSubject<Contact> mCurrentContactSubject = BehaviorSubject.create();
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNoContact = (TextView) findViewById(R.id.tvNoContact);
        layoutNoContact = (LinearLayout) findViewById(R.id.layoutContact);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        tvNotes = (TextView) findViewById(R.id.tvNotes);
        ivCall = (ImageView) findViewById(R.id.ivCall);
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentContactSubject.getValue() != null) {
                    makeCall();
                }
            }
        });
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Too lazy to make a drawer", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeCall() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + mCurrentContactSubject.getValue().getPhoneNumber()));
            startActivity(callIntent);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    makeCall();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Contact contact = (Contact) data.getSerializableExtra("contact");
            if (contact != null) {
                mCurrentContactSubject.onNext(contact);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscription = mCurrentContactSubject.subscribe(new Action1<Contact>() {
            @Override
            public void call(Contact contact) {
                // Update page with new contact
                tvSearch.setText(contact.getName());
                hideNoContactView();
                showContact(contact);
            }
        });
    }

    @Override
    protected void onStop() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        super.onStop();
    }

    private void showContact(Contact contact) {
        layoutNoContact.setVisibility(View.VISIBLE);
        Log.d("Selected Contact: ", contact.getPhoneNumber());
        tvNumber.setText(contact.getPhoneNumber());
        tvNotes.setText(contact.getNotes());
    }

    private void hideContact() {
        layoutNoContact.setVisibility(View.GONE);
    }

    private void showNoContactView() {
        tvNoContact.setVisibility(View.VISIBLE);
    }

    private void hideNoContactView() {
        tvNoContact.setVisibility(View.GONE);
    }
}
