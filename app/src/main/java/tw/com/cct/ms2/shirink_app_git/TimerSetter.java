package tw.com.cct.ms2.shirink_app_git;

import android.app.TimePickerDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimerSetter implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    private EditText mEditText;
    private Calendar mCalendar;
    private SimpleDateFormat mFormat;
    private int out_hour;
    private int out_minute;

    public TimerSetter(EditText editText){
        this.mEditText = editText;
        mEditText.setOnFocusChangeListener(this);
        mEditText.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus){
            showPicker(view);
        }
    }

    @Override
    public void onClick(View view) {
        showPicker(view);
    }

    private void showPicker(View view) {
        if (mCalendar == null)
            mCalendar = Calendar.getInstance();

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        out_hour=hour;
        int minute = mCalendar.get(Calendar.MINUTE);
        out_minute=minute;
        new TimePickerDialog(view.getContext(), this, hour, minute, true).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);

        if (mFormat == null)
            mFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        out_hour=hourOfDay;
        out_minute=minute;

        this.mEditText.setText(mFormat.format(mCalendar.getTime()));
    }

    public int getOut_hour() {
        return out_hour;
    }

    public int getOut_minute() {
        return out_minute;
    }
}