package Callback.NotifyCurriculo;


import CurriculoServer.CurriculoObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Esta classe e responsavel por implementar e servir os metodos decladaros na CurriculoInterfaceCallback
public class CurriculoServantCallback extends UnicastRemoteObject implements CurriculoInterfaceCallback {
    //	constructors
    public CurriculoServantCallback() throws RemoteException {
    }


    public void notifyCurriculo(CurriculoObject v) throws RemoteException {
        //Retorna para o cliente o curriculo da area de seu interesse
        System.out.println("\n|---------------------------------------------------|");
        System.out.println("Curriculo de seu interesse");
        System.out.println("        nome_candidato: " + v.nome_candidato);
        System.out.println("        area_de_interesse: " + v.area_de_interesse);
        System.out.println("        carga_horaria: " + v.carga_horaria);
        System.out.println("        cpf: " + v.cpf);
        System.out.println("        contato_candidato: " + v.contato_candidato);
        System.out.println("|---------------------------------------------------|\n");
    }
}
