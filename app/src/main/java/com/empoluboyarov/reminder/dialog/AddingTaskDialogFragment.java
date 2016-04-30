package com.empoluboyarov.reminder.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.empoluboyarov.reminder.R;

/**
 * Created by Evgeniy on 30.04.2016.
 */
public class AddingTaskDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_title);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View container = inflater.inflate(R.layout.dialog_task, null);

        TextInputLayout titTitle = (TextInputLayout) container.findViewById(R.id.dialogTaskTitle);
        TextInputLayout titDate = (TextInputLayout) container.findViewById(R.id.dialogTaskDate);
        TextInputLayout titTime = (TextInputLayout) container.findViewById(R.id.dialogTaskTime);

        EditText etTitle = titTitle.getEditText();
        EditText etDate = titDate.getEditText();
        EditText etTime = titTime.getEditText();

        titTitle.setHint(getResources().getString(R.string.task_title));
        titDate.setHint(getResources().getString(R.string.task_date));
        titTime.setHint(getResources().getString(R.string.task_time));

        builder.setView(container);




        return super.onCreateDialog(savedInstanceState);
    }
}
