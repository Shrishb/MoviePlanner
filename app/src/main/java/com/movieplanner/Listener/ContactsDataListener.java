package com.movieplanner.Listener;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;

public class ContactsDataListener implements View.OnClickListener {
    private Activity activity;

    public ContactsDataListener(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onClick(View view)
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        activity.startActivityForResult(
                contactPickerIntent, 1);
    }
}
