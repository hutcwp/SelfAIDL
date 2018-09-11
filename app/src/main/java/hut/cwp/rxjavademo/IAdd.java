package hut.cwp.rxjavademo;

import android.os.IInterface;
import android.os.RemoteException;

public interface IAdd extends IInterface {

    // Binder唯一标识
    String DESCRIPTOR = "hut.cwp.rxjavademo.AddStub";

    //用来表示方法0..n
    int TRANSACTION_add = android.os.IBinder.FIRST_CALL_TRANSACTION + 0;

    void add(int a, int b) throws RemoteException;
}
