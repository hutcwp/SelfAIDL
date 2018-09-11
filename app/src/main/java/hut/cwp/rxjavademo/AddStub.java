package hut.cwp.rxjavademo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class AddStub extends Binder implements IAdd {

    public AddStub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IAdd asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }

        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if ((iin instanceof IAdd)) {
            return (IAdd) iin;
        }

        return new AddProxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        String descriptor = DESCRIPTOR;
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }
            case TRANSACTION_add: {
                data.enforceInterface(descriptor);
                int a = data.readInt();
                int b = data.readInt();
                this.add(a, b);
                reply.writeNoException();
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }

}
