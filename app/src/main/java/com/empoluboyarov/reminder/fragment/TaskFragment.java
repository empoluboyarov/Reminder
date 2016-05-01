package com.empoluboyarov.reminder.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.empoluboyarov.reminder.adapter.CurrentTasksAdapter;
import com.empoluboyarov.reminder.model.ModelTask;

/**
 * Created by Evgeniy on 01.05.2016.
 */
public abstract class TaskFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected CurrentTasksAdapter adapter;

    public void addTask(ModelTask newTask) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            ModelTask task = (ModelTask) adapter.getItem(i);
            if (newTask.getDate() < task.getDate()) {
                position = i;
                break;
            }
        }
        if (position != -1)
            adapter.addItem(position, newTask);
        else adapter.addItem(newTask);
    }


}
