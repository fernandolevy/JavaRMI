package Callback.NotifyCurriculo;

import CurriculoServer.CurriculoObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurriculoInterfaceCallback extends Remote {
    void notifyCurriculo(CurriculoObject v) throws RemoteException;
}