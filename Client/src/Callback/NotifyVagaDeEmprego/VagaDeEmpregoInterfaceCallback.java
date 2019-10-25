package Callback.NotifyVagaDeEmprego;

import VagaDeEmpregoServer.VagaDeEmpregoObject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VagaDeEmpregoInterfaceCallback extends Remote {
    //Este metodo notifica o Candidato quando uma vaga de emprego de seu interesse e cadastrado
    void notifyVagaDeEmprego(VagaDeEmpregoObject v) throws RemoteException;
}