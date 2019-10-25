package Callback.NotifyVagaDeEmprego;

import VagaDeEmpregoServer.VagaDeEmpregoObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VagaDeEmpregoInterfaceCallback extends Remote {
    void notifyVagaDeEmprego(VagaDeEmpregoObject v) throws RemoteException;
}