package hut.cwp.rxjavademo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    private AddStub addStub = new AddStub() {
        @Override
        public void add(int a, int b) throws RemoteException {

            Log.d(TAG, "add: a:"+a+" b:"+b +" = "+a+b);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return addStub;
    }
}
