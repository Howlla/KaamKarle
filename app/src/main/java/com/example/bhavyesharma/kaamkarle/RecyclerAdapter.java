package com.example.bhavyesharma.kaamkarle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bhavyesharma on 15/07/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ReminderViewHolder> {
    private Context mContext;
    private ArrayList<Reminder> mReminderList;
    private ReminderClickListener mListener;

    public interface ReminderClickListener {
        void onItemClick(View view, int position);

        void onRemoveClicked(int position);
    }

    public RecyclerAdapter(Context context, ArrayList<Reminder> reminderList, ReminderClickListener listener) {
        mContext = context;
        mReminderList = reminderList;
        mListener = listener;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_reminder_unit, parent, false);
        return new ReminderViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        Reminder reminder = mReminderList.get(position);
        holder.titleTextView.setText(reminder.getTitle());
        holder.detailsTextView.setText(reminder.getDetails());
    }

    @Override
    public int getItemCount() {
        return mReminderList.size();
    }

    public static class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        TextView detailsTextView;
        Button removeButton;
        ReminderClickListener mReminderClickListener;

        public ReminderViewHolder(View itemView, ReminderClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            mReminderClickListener = listener;
            titleTextView = itemView.findViewById(R.id.title_text);
            detailsTextView = itemView.findViewById(R.id.details_text);
            removeButton = itemView.findViewById(R.id.remove_button);
            removeButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                if (id == R.id.reminder_layout) {
                    mReminderClickListener.onItemClick(view, position);
                } else if (id == R.id.remove_button) {
                    mReminderClickListener.onRemoveClicked(position);
                }
            }

        }

    }
}