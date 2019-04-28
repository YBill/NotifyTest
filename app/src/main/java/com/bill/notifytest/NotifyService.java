package com.bill.notifytest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.bill.module_notification.NotifyManager;

/**
 * Created by Bill on 2019/4/26.
 * Describe ：
 */
public class NotifyService extends Service implements NotifyInterface {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notifyManager = new NotifyManager(this);
        NotifyReceiver notifyReceiver = new NotifyReceiver(this);
        this.registerReceiver(notifyReceiver, new IntentFilter(NotifyReceiver.ACTION));

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotify();
        return super.onStartCommand(intent, flags, startId);
    }

    private NotifyManager notifyManager;
    private int notifyId;
    private Notification notification;

    private void createNotify() {
        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.layout_notify);
        remoteView.setOnClickPendingIntent(R.id.btn_play, getPendingIntent(1));
        remoteView.setOnClickPendingIntent(R.id.btn_close, getPendingIntent(3));

        NotificationCompat.Builder mBuilder = notifyManager.getDefaultBuilder(Constants.CHANNEL_CHAT);
        mBuilder
                .setContent(remoteView)
                .setOngoing(true)
                .setAutoCancel(false);
        notification = mBuilder.build();

        notifyId = 100;
        startForeground(notifyId, notification);
    }

    private PendingIntent getPendingIntent(int key) {
        Intent intent = new Intent(NotifyReceiver.ACTION);
        intent.putExtra("key", key);
        return PendingIntent.getBroadcast(this, notifyManager.getRandomId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void play(boolean isPlay) {
        RemoteViews remoteView = notification.contentView;
        remoteView.setOnClickPendingIntent(R.id.btn_play, getPendingIntent(isPlay ? 2 : 1));
        remoteView.setImageViewResource(R.id.btn_play, isPlay ? R.mipmap.res_7icon_147 : R.mipmap.res_7icon_146);
        notifyManager.notifyNotify(notifyId, notification);
    }

    @Override
    public void close() {
//        notifyManager.cancelNotify(notifyId);
        stopForeground(true);
    }
}
