package com.bill.notifytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Bill on 2019/4/26.
 * Describe ：
 */
public class NotifyReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.bill.notifytest.NotifyReceiver";

    private NotifyInterface notifyInterface;

    public NotifyReceiver(NotifyInterface notifyInterface) {
        this.notifyInterface = notifyInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int key = intent.getIntExtra("key", 0);

        if (key == 1) {
            notifyInterface.play(true);
        } else if (key == 2) {
            notifyInterface.play(false);
        } else if (key == 3) {
            notifyInterface.close();
        }

    }
}