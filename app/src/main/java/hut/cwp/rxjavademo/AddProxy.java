package hut.cwp.rxjavademo;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class AddProxy implements IAdd{

    private IBinder mRemote;

    public AddProxy(IBinder mRemote) {
        this.mRemote = mRemote;
    }

    public java.lang.String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public void add(int a, int b) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeInt(a);
            data.writeInt(b);
            mRemote.transact(TRANSACTION_add, data, reply, 0);
            reply.readException();
        } finally {
            reply.recycle();
            reply.recycle();
        }

    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }



}
