package com.example.tmh.contactprovider;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView mName, mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mName = (TextView) findViewById(R.id.text_nameDetails);
        mName.setText(loadPre(ContactAdpter.PREF_NAME));
        mPhone = (TextView) findViewById(R.id.text_phoneDetails);
        mPhone.setText(loadPre(ContactAdpter.PREF_PHONE));
    }

    private String loadPre(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String value = preferences.getString(key, null);
        return value;
    }
}
