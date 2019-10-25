package VagaDeEmpregoServer;


import Callback.NotifyVagaDeEmprego.VagaDeEmpregoInterfaceCallback;
import Callback.NotifyVagaDeEmprego.VagaDeEmpregoServantCallback;

import java.util.Vector;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Esta classe e responsavel por implementar e servir os metodos decladaros na VagaDeEmpregoInterface
public class VagaDeEmpregoServant extends UnicastRemoteObject implements VagaDeEmpregoInterface {
    private Vector theList;
    private Vector interesseList;
    private Vector interesseCallbackList;

    public VagaDeEmpregoServant() throws RemoteException {
        theList = new Vector();
        interesseList = new Vector();
        interesseCallbackList = new Vector();
    }


    public void verificaInteresseVagaDeEmprego(VagaDeEmpregoObject v) throws RemoteException {
        VagaDeEmpregoInterfaceCallback notify = new VagaDeEmpregoServantCallback();
        for (int i = 0; i < interesseList.size(); i++) {
            String g = (String) interesseList.elementAt(i);
            VagaDeEmpregoInterfaceCallback c = (VagaDeEmpregoInterfaceCallback) interesseCallbackList.elementAt(i);
            if (v.area_da_vaga.equals(g)) {
                c.notifyVagaDeEmprego(v);
            }
        }
    }

    public void cadastrarVagaDeEmprego(VagaDeEmpregoObject v) throws RemoteException {
        System.out.println("\n\n|---------------------------------------------------|");
        System.out.println("Vaga de Emprego Cadastrada");
        System.out.println("        nome_empresa: " + v.nome_empresa);
        System.out.println("        area_da_vaga: " + v.area_da_vaga);
        System.out.println("        carga_horaria: " + v.carga_horaria);
        System.out.println("        cnpj:" + v.cnpj);
        System.out.println("        contato_empresa:" + v.contato_empresa);
        System.out.println("|---------------------------------------------------|\n\n");
        theList.addElement(v);
        verificaInteresseVagaDeEmprego(v);
    }

    public Vector consultarVagaDeEmprego(String area_da_vaga) throws RemoteException {
        Vector vagas = new Vector();
        for (int i = 0; i < theList.size(); i++) {
            VagaDeEmpregoObject g = (VagaDeEmpregoObject) theList.elementAt(i);
            if (area_da_vaga.equals(g.area_da_vaga)) {
                vagas.addElement(g);
            }
        }
        return vagas;
    }

    public Vector getVagasDeEmprego() throws RemoteException {
        return theList;
    }

    public void registraInteresseVagaDeEmprego(VagaDeEmpregoInterfaceCallback callback, String area_de_interesse) throws RemoteException {
        interesseList.addElement(area_de_interesse);
        interesseCallbackList.addElement(callback);
    }

    public boolean alterarVagaDeEmprego(String cnpj, VagaDeEmpregoObject v) throws RemoteException {
        for (int i = 0; i < theList.size(); i++) {
            VagaDeEmpregoObject g = (VagaDeEmpregoObject) theList.elementAt(i);
            if (g.cnpj.equals(cnpj)) {
                System.out.println("\n\n|---------------------------------------------------|");
                System.out.println("Vaga de Emprego Alterada");
                System.out.println("        nome_empresa: " + v.nome_empresa);
                System.out.println("        area_da_vaga: " + v.area_da_vaga);
                System.out.println("        carga_horaria: " + v.carga_horaria);
                System.out.println("        cnpj:" + v.cnpj);
                System.out.println("        contato_empresa:" + v.contato_empresa);
                System.out.println("|---------------------------------------------------|\n\n");
                theList.set(i, v);
                return true;
            }
        }
        return false;
    }
}
