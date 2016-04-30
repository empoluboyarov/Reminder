package com.empoluboyarov.reminder.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.empoluboyarov.reminder.R;
import com.empoluboyarov.reminder.Utils;

import java.util.Calendar;

/**
 * Created by Evgeniy on 30.04.2016.
 */
public class AddingTaskDialogFragment extends DialogFragment {

    private AddingTaskListener addingTaskListener;

    public interface AddingTaskListener{
        void onTaskAdded();
        void onTaskAddingCancel();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            addingTaskListener = (AddingTaskListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement AddingTaskListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_title);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View container = inflater.inflate(R.layout.dialog_task, null);

        final TextInputLayout titTitle = (TextInputLayout) container.findViewById(R.id.dialogTaskTitle);
        TextInputLayout titDate = (TextInputLayout) container.findViewById(R.id.dialogTaskDate);
        TextInputLayout titTime = (TextInputLayout) container.findViewById(R.id.dialogTaskTime);

        final EditText etTitle = titTitle.getEditText();
        final EditText etDate = titDate.getEditText();
        final EditText etTime = titTime.getEditText();

        titTitle.setHint(getResources().getString(R.string.task_title));
        titDate.setHint(getResources().getString(R.string.task_date));
        titTime.setHint(getResources().getString(R.string.task_time));

        builder.setView(container);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate.length() == 0)
                    etDate.setText(" ");

                DialogFragment datePickerFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        etDate.setText(Utils.getData(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etDate.setText(null);
                    }
                };
                datePickerFragment.show(getFragmentManager(), "DatePickerFragment");
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTime.length() == 0)
                    etTime.setText(" ");
                DialogFragment timePickerFragment = new TimePickerFragment() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, hourOfDay, minute);
                        etTime.setText(Utils.getTime(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etTime.setText(null);
                    }
                };
                timePickerFragment.show(getFragmentManager(),"TimePickerFragment");
            }
        });

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addingTaskListener.onTaskAdded();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addingTaskListener.onTaskAddingCancel();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                if (etTitle.length() == 0){
                    positiveButton.setEnabled(false);
                    titTitle.setError(getResources().getString(R.string.dialog_error_empty_title));
                }
                etTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0){
                            positiveButton.setEnabled(false);
                            titTitle.setError(getResources().getString(R.string.dialog_error_empty_title));
                        }else {
                            positiveButton.setEnabled(true);
                            titTitle.setErrorEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        return alertDialog;
    }
}
