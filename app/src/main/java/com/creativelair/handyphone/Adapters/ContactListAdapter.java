package com.creativelair.handyphone.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creativelair.handyphone.Helpers.Contacts;
import com.creativelair.handyphone.R;

import java.util.ArrayList;


public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MyViewHolder> {

    ArrayList<Contacts> contacts;
    private Context mContext;
    private ArrayList<Contacts> display;


    public ContactListAdapter(Context mContext, ArrayList<Contacts> contacts) {
        this.mContext = mContext;
        this.contacts = new ArrayList<>();
        this.display = new ArrayList<>();
        this.contacts.addAll(contacts);
        this.display.addAll(contacts);
    }

    public ArrayList<Contacts> getList() {
        return display;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return display.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Typeface RobotoBlack = Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Black.ttf");
        Typeface Roboto = Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Regular.ttf");
        Contacts contact = display.get(position);
        holder.name.setText(contact.getName());
        holder.name.setTypeface(Roboto);
        if (contact.getIcon() != null) {
            holder.img.setImageBitmap(contact.getIcon());
            holder.initial.setText("");
        } else {
            holder.img.setImageDrawable(new ColorDrawable(Color.parseColor("#303F9F")));
            holder.initial.setText("" + holder.name.getText().toString().toUpperCase().charAt(0));
            holder.initial.setTypeface(RobotoBlack);
        }


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        display.clear();

        if (query.isEmpty()) {
            display.addAll(contacts);
        } else {
            for (Contacts contact : contacts) {
                if (contact.getName().toLowerCase().contains(query)) {
                    display.add(contact);
                }
            }
        }
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, initial;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            img = (ImageView) view.findViewById(R.id.icon);
            initial = (TextView) view.findViewById(R.id.initial);
        }
    }



}
