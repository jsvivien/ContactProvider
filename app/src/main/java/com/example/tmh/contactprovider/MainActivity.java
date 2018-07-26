package com.example.tmh.contactprovider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ContentResolver mResolver;
    private Cursor mCursor;
    private RecyclerView mRecyclerContact;
    private RecyclerView.LayoutManager mLayout;
    private ArrayList<Contact> mContacts;
    private ContactAdpter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerContact = (RecyclerView) findViewById(R.id.recycler_contact);
        setRecycler();
    }

    private void setRecycler() {
        mRecyclerContact.setHasFixedSize(true);
        mLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerContact.setLayoutManager(mLayout);
        mContacts = new ArrayList<>();
        mContacts = getContacts(getApplicationContext());
        mAdapter = new ContactAdpter(mContacts, getApplicationContext());
        mRecyclerContact.setAdapter(mAdapter);
    }

    private ArrayList<Contact> getContacts(Context mContext) {
        ArrayList<Contact> mContacts = new ArrayList<>();
        mResolver = mContext.getContentResolver();
        mCursor = mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null, null);
        int songColumn;
        Contact contact;
        if (mCursor != null && mCursor.getCount() > 0) {
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                contact = new Contact();

                songColumn = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                contact.setName(mCursor.getString(songColumn));

                songColumn = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                contact.setPhone(mCursor.getString(songColumn));

                mContacts.add(contact);
            }
        }

        return mContacts;
    }

}
