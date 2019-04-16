package com.movieplanner.Controller.Listener;

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
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK);
        contactPickerIntent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
        activity.startActivityForResult(
                contactPickerIntent, 1);
    }
}
