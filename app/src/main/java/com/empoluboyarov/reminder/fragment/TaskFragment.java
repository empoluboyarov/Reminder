package com.empoluboyarov.reminder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.empoluboyarov.reminder.MainActivity;
import com.empoluboyarov.reminder.adapter.TaskAdapter;
import com.empoluboyarov.reminder.model.ModelTask;

/**
 * Created by Evgeniy on 01.05.2016.
 */
public abstract class TaskFragment extends Fragment {

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected TaskAdapter adapter;

    public MainActivity activity;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null)
            activity = (MainActivity) getActivity();

        addTaskFromDB();
    }

    public void addTask(ModelTask newTask, boolean saveToDB) {
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

        if (saveToDB)
            activity.dbHelper.saveTask(newTask);
    }

    public abstract void moveTask(ModelTask task);

    public abstract void addTaskFromDB();


}
