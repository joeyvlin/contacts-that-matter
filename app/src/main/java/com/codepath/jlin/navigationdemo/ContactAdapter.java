package com.codepath.jlin.navigationdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.jlin.navigationdemo.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Listener mListener;

    public static interface Listener {
        void onClickContact(int position);
    }

    public ContactAdapter(Context context, Listener listener, int resource, List<Contact> contacts) {
        super(context, resource, contacts);
        mListener = listener;
    }

    private static class ViewHolder {
        TextView name;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(contact.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClickContact(position);
                }
            }
        });
        return convertView;
    }

}

