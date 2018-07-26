package com.example.tmh.contactprovider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdpter extends RecyclerView.Adapter<ContactAdpter.ViewHoder> {
    private ArrayList<Contact> mContacts;
    private Context mContext;
    public final static String PREF_NAME = "NAME";
    public final static String PREF_PHONE = "PHONE";

    public ContactAdpter(ArrayList<Contact> mContacts, Context mContext) {
        this.mContacts = mContacts;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        final Contact mContact = mContacts.get(position);
        holder.mTextName.setText(mContact.getName());
        holder.mTextPhone.setText(mContact.getPhone());
        holder.setOnClickItem(new OnClickItem() {
            @Override
            public void onItemClickRecycler(View view, int position, boolean isLongClick) {
                savePre(PREF_NAME, mContact.getName());
                savePre(PREF_PHONE, mContact.getPhone());
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    private void savePre(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextName;
        private TextView mTextPhone;
        private OnClickItem onClickItem;

        public void setOnClickItem(OnClickItem onClickItem) {
            this.onClickItem = onClickItem;
        }

        public ViewHoder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextPhone = (TextView) itemView.findViewById(R.id.text_phone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickItem.onItemClickRecycler(view, getAdapterPosition(), false);
        }
    }

    interface OnClickItem {
        void onItemClickRecycler(View view, int position, boolean isLongClick);
    }
}
