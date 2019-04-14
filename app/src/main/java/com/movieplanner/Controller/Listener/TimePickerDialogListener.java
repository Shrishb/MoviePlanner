package com.movieplanner.Controller.Listener;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class TimePickerDialogListener
        implements TimePickerDialog.OnTimeSetListener, View.OnClickListener
{
    private Context context;
    private EditText timeField;
    private Calendar time;
    private DateFormat formatter = new SimpleDateFormat("h:mm a",
            Locale.getDefault());

    public TimePickerDialogListener(Context context, EditText timeField)
    {
        this.context = context;
        this.timeField = timeField;
        this.timeField.setOnClickListener(this);
        this.time = Calendar.getInstance();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute)
    {
        time.set(Calendar.HOUR_OF_DAY, hourOfDay);
        time.set(Calendar.MINUTE, minute);

        timeField.setText(formatter.format(time.getTime()));
    }

    @Override
    public void onClick(View view)
    {
        new TimePickerDialog(
                context, this, time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE), false)
                .show();
    }
}
