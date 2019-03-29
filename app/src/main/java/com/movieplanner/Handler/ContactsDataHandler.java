package com.movieplanner.Handler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactsDataHandler {

        private Context context;
        private Intent intent;

        public class ContactQueryException extends Exception
        {
            public ContactQueryException(String message)
            {
                super(message);
            }
        }


        public ContactsDataHandler(Context aContext, Intent anIntent)
        {
            this.context = aContext;
            this.intent = anIntent;
        }

        /**
          Retrieves the display Name of a contact

         @return Name of the contact referred to by the URI specified through the
                 intent
         @throws ContactQueryException
                      if querying the Contact Details Fails
         **/
        public String getContactName() throws ContactQueryException
        {
            Cursor cursor = null;
            String name = null;
            try
            {
                cursor = context.getContentResolver().query(intent.getData(), null,
                        null, null, null);
                if (cursor.moveToFirst())
                {
                    name = cursor.getString(cursor
                            .getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                }

            }
            catch (Exception e)
            {
                throw new ContactQueryException(e.getMessage());
            }
            finally
            {
                if (cursor != null)
                {
                    cursor.close();
                }
            }

            return name;
        }

        /*
          Retrieves the email of a contact

          @return Email of the contact referred to by the URI specified through the
                  intent
          @throws ContactQueryException
                      if querying the Contact Details Fails
         */
        public String getContactEmail() throws ContactQueryException
        {
            Cursor cursor = null;
            String email = null;
            try
            {
                cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?",
                        new String[] { intent.getData().getLastPathSegment() },
                        null);

                if (cursor.moveToFirst())
                {
                    email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                }

            }
            catch (Exception e)
            {
                throw new ContactQueryException(e.getMessage());
            }
            finally
            {
                if (cursor != null)
                {
                    cursor.close();
                }
            }

            return email;
        }
}
