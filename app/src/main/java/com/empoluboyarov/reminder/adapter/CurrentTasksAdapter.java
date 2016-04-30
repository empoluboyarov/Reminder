package com.empoluboyarov.reminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.empoluboyarov.reminder.R;
import com.empoluboyarov.reminder.Utils;
import com.empoluboyarov.reminder.model.Item;
import com.empoluboyarov.reminder.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy on 30.04.2016.
 */
public class CurrentTasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items = new ArrayList<>();

    public static final int TYPE_TASK = 0;
    public static final int TYPE_SEPARATOR = 1;

    public Item getItem(int position) {
        return items.get(position);
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItem(int location, Item item) {
        items.add(location, item);
        notifyItemInserted(location);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE_TASK:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_task, viewGroup, false);
                TextView title = (TextView) view.findViewById(R.id.tvTaskTitle);
                TextView date = (TextView) view.findViewById(R.id.tvTaskDate);

                return new TaskViewHolder(view, title, date);

            case TYPE_SEPARATOR:
                return null;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
      Item item = items.get(position);
        if (item.isTask()){
            viewHolder.itemView.setEnabled(true);
            ModelTask task = (ModelTask) item;
            TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;

            taskViewHolder.title.setText(task.getTitle());
            if (task.getDate() != 0){
                taskViewHolder.date.setText(Utils.getFullDate(task.getDate()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isTask())
            return TYPE_TASK;
        else return TYPE_SEPARATOR;
    }

    private class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        public TaskViewHolder(View itemView, TextView title, TextView date) {
            super(itemView);
            this.title = title;
            this.date = date;
        }
    }
}
