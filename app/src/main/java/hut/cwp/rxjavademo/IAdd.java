package hut.cwp.rxjavademo;

import android.os.IInterface;
import android.os.RemoteException;

public interface IAdd extends IInterface {

    String DESCRIPTOR = "hut.cwp.rxjavademo.AddStub";

    int TRANSACTION_add = android.os.IBinder.FIRST_CALL_TRANSACTION;

    void add(int a, int b) throws RemoteException;
}
