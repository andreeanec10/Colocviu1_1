package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Process;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {
    private boolean isRunning = true;
    private Random random = new Random();
    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.arithmeticmean",

    };
    private Context context;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("[Processing Thread]", "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while(isRunning){
            sendMessage();
            sleep();

        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(actionTypes[random.nextInt(actionTypes.length)]);
        intent.putExtra( "message_b",
                new Date(System.currentTimeMillis()) + " ");
        context.sendBroadcast(intent);
    }
    public void stopThread() {
        isRunning = false;
    }
}
