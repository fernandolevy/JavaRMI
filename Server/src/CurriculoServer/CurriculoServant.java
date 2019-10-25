package CurriculoServer;

import Callback.NotifyCurriculo.CurriculoInterfaceCallback;
import Callback.NotifyCurriculo.NotifyCurriculoObject;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class CurriculoServant extends UnicastRemoteObject implements CurriculoInterface {
    private Vector theList;
    private Vector interesseList;
    private Vector interesseCallbackList;


    public CurriculoServant() throws RemoteException {
        theList = new Vector();
        interesseList = new Vector();
        interesseCallbackList = new Vector();
    }

    public void cadastrarCurriculo(CurriculoObject v) throws RemoteException {
        System.out.println("\n\n|---------------------------------------------------|");
        System.out.println("Curriculo Cadastrado");
        System.out.println("        nome_candidato: " + v.nome_candidato);
        System.out.println("        area_de_interesse: " + v.area_de_interesse);
        System.out.println("        carga_horaria: " + v.carga_horaria);
        System.out.println("        cpf: " + v.cpf);
        System.out.println("        contato_candidato: " + v.contato_candidato);
        System.out.println("|---------------------------------------------------|\n\n");
        theList.addElement(v);
        verificaInteresseCurriculo(v);
    }

    public void verificaInteresseCurriculo(CurriculoObject v) throws RemoteException {
        for (int i = 0; i < interesseList.size(); i++) {
            NotifyCurriculoObject g = (NotifyCurriculoObject) interesseList.elementAt(i);
            CurriculoInterfaceCallback c = (CurriculoInterfaceCallback) interesseCallbackList.elementAt(i);
            if (v.area_de_interesse.equals(g.area_de_interesse)) {
                c.notifyCurriculo(v);
            }
        }
    }

    public Vector consultarCurriculo(String area_de_interesse, String salario_pretendido) throws RemoteException {
        Vector curriculos = new Vector();
        for (int i = 0; i < theList.size(); i++) {
            CurriculoObject g = (CurriculoObject) theList.elementAt(i);
            if (area_de_interesse.equals(g.area_de_interesse) && (salario_pretendido.equals(g.salario_pretendido))) {
                curriculos.addElement(g);
            }
        }
        return curriculos;
    }


    public Vector getCurriculos() throws RemoteException {
        return theList;
    }


    public void registraInteresseCurriculo(CurriculoInterfaceCallback callback, NotifyCurriculoObject c) throws RemoteException {
        System.out.println("\n\n|---------------------------------------------------|");
        System.out.println("Registro de interesse em receber Curriculos");
        System.out.println("        area_de_interesse: " + c.area_de_interesse);
        System.out.println("|---------------------------------------------------|\n\n");
        interesseList.addElement(c);
        interesseCallbackList.addElement(callback);
    }

    public boolean alterarCurriculo(String cpf, CurriculoObject v) throws RemoteException {
        for (int i = 0; i < theList.size(); i++) {
            CurriculoObject g = (CurriculoObject) theList.elementAt(i);
            if (g.cpf.equals(cpf)) {
                System.out.println("\n\n|---------------------------------------------------|");
                System.out.println("Curriculo Alterado");
                System.out.println("        nome_candidato: " + v.nome_candidato);
                System.out.println("        area_de_interesse: " + v.area_de_interesse);
                System.out.println("        carga_horaria: " + v.carga_horaria);
                System.out.println("        cpf: " + v.cpf);
                System.out.println("        contato_candidato: " + v.contato_candidato);
                System.out.println("|---------------------------------------------------|\n\n");
                theList.set(i, v);
                return true;
            }
        }
        return false;
    }
}
