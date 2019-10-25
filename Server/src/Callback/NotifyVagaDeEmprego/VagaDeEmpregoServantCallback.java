package Callback.NotifyVagaDeEmprego;

import VagaDeEmpregoServer.VagaDeEmpregoObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

//Esta classe e responsavel por implementar e servir os metodos decladaros na VagaDeEmpregoInterfaceCallback
public class VagaDeEmpregoServantCallback extends UnicastRemoteObject implements VagaDeEmpregoInterfaceCallback {
    //	constructors
    public VagaDeEmpregoServantCallback() throws RemoteException {
    }

    public void notifyVagaDeEmprego(VagaDeEmpregoObject v) throws RemoteException {
        //Retorna para o cliente a vaga de emprego da area de seu interesse
        System.out.println("\n|---------------------------------------------------|");
        System.out.println("Vaga de Emprego de seu interesse");
        System.out.println("        nome_empresa: " + v.nome_empresa);
        System.out.println("        area_da_vaga: " + v.area_da_vaga);
        System.out.println("        carga_horaria: " + v.carga_horaria);
        System.out.println("        cnpj:" + v.cnpj);
        System.out.println("        contato_empresa:" + v.contato_empresa);
        System.out.println("|---------------------------------------------------|\n");
    }
}
