package Callback.NotifyCurriculo;

import CurriculoServer.CurriculoObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurriculoInterfaceCallback extends Remote {
    //Este metodo notifica a Empresa quando um curriculo de seu interesse e cadastrado
    void notifyCurriculo(CurriculoObject v) throws RemoteException;
}