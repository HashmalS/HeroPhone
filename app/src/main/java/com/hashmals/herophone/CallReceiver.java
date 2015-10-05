package com.hashmals.herophone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telecom.Call;

import java.util.Date;

public class CallReceiver extends PhonecallReceiver {
    public static String name;
    public static Context mContext;
    public static String mNumber;

    @Override
    protected void onIncomingCallStarted(Context context, String number, Date start) {
        mContext = context;
        mNumber = number;
        context.startActivity(new Intent(context, CallActivity.class)
                .putExtra("number", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onIncomingCallEnded(Context context, String number, Date start, Date end) {
        ((Activity)context).finish();
    }

    public static String getName() {
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            name = cursor.getString(nameIdx);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return name;
    }

    public static String getNumber() {
        return mNumber;
    }
}
