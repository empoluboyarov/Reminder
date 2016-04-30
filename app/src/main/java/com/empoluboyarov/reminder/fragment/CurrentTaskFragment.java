package com.empoluboyarov.reminder.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.empoluboyarov.reminder.R;
import com.empoluboyarov.reminder.adapter.CurrentTasksAdapter;
import com.empoluboyarov.reminder.model.ModelTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends Fragment {

    private RecyclerView rvCurrentTasks;
    private RecyclerView.LayoutManager layoutManager;

    private CurrentTasksAdapter adapter;

    public CurrentTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvCurrentTasks = (RecyclerView) rootView.findViewById(R.id.rvCurrentTasks);
        layoutManager = new LinearLayoutManager(getActivity());

        rvCurrentTasks.setLayoutManager(layoutManager);

        adapter = new CurrentTasksAdapter();
        rvCurrentTasks.setAdapter(adapter);

        return rootView;
    }

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
