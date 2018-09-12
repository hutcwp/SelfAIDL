package hut.cwp.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;

import static hut.cwp.rxjavademo.IAdd.DESCRIPTOR;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder obj) {
            if (obj == null) {
                return ;
            }

            IAdd add = null;
            // 如果obj是代理类BinderProxy,obj.queryLocalInterface(DESCRIPTOR)会直接返回null
            // 如果obj是Binder类，则会判断DESCRIPTOR字段是否相等，相等则返回attachInterface()方法中传入的对象，这里就是this
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if ((iin instanceof IAdd)) {
                add = (IAdd) iin;
            }

            add = new AddProxy(obj);
            try {
                add.add(12, 2);
                Log.d(TAG, "onServiceConnected: ");
            } catch (RemoteException e) {
                Log.e(TAG, "onServiceConnected: ", e);
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

}
