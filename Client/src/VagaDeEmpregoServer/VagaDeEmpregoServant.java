package VagaDeEmpregoServer;


import Callback.NotifyVagaDeEmprego.VagaDeEmpregoInterfaceCallback;
import Callback.NotifyVagaDeEmprego.VagaDeEmpregoServantCallback;

import java.util.Vector;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Esta classe e responsavel por implementar e servir os metodos decladaros na VagaDeEmpregoInterface
public class VagaDeEmpregoServant extends UnicastRemoteObject implements VagaDeEmpregoInterface {
    //Vetor global com as vagas de emprego
    private Vector theList;
    //Vetor global com as areas de interesse
    private Vector interesseList;
    //Vetor global com a referencia dos clientes que registraram interesse em vagas de emprego
    private Vector interesseCallbackList;

    //Inicializa vetor
    public VagaDeEmpregoServant() throws RemoteException {
        theList = new Vector();
        interesseList = new Vector();
        interesseCallbackList = new Vector();
    }


    public void verificaInteresseVagaDeEmprego(VagaDeEmpregoObject v) throws RemoteException {
        //Percorre vetor de objetos vaga de emprego e verifica se casa com a area de interesse registrada pelo metodo registraInteresseVagaDeEmprego
        VagaDeEmpregoInterfaceCallback notify = new VagaDeEmpregoServantCallback();
        for (int i = 0; i < interesseList.size(); i++) {
            //salva area de interesse registrada no vetor
            String g = (String) interesseList.elementAt(i);
            //Salva a interface do callback salvo no registro de interesse
            VagaDeEmpregoInterfaceCallback c = (VagaDeEmpregoInterfaceCallback) interesseCallbackList.elementAt(i);
            //Caso a area de interesse registrada case com area de interesse cadastrada da Vaga de Emprego
            if (v.area_da_vaga.equals(g)) {
                //Notifica cliente com a vaga de emprego de seu interesse
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
        //Salva na lista global o curriculo cadastrado
        theList.addElement(v);
        //Verifica se a area de interesse da Vagas De Emprego cadastrada casa com uma das aread de interesse contidas no vetor de Interesses registrados
        verificaInteresseVagaDeEmprego(v);
    }

    public Vector consultarVagaDeEmprego(String area_da_vaga) throws RemoteException {
        //Percorre vetor de objetos Vagas de emprego ate encontrar objeto com a area da vaga de emprego enviado pelo Candidato
        Vector vagas = new Vector();
        for (int i = 0; i < theList.size(); i++) {
            VagaDeEmpregoObject g = (VagaDeEmpregoObject) theList.elementAt(i);
            if (area_da_vaga.equals(g.area_da_vaga)) {
                //Salvar em um vetor de objetos vaga de emprego
                vagas.addElement(g);
            }
        }
        //retorna vaga de emprego
        return vagas;
    }

    public Vector getVagasDeEmprego() throws RemoteException {
        //retorna Vector global com as Vagas De Emprego
        return theList;
    }

    public void registraInteresseVagaDeEmprego(VagaDeEmpregoInterfaceCallback callback, String area_de_interesse) throws RemoteException {
        //Salva o interesse em um vetor de strings
        interesseList.addElement(area_de_interesse);
        //Salva o callback em um vetor das Interfaces do callback
        interesseCallbackList.addElement(callback);
    }

    public boolean alterarVagaDeEmprego(String cnpj, VagaDeEmpregoObject v) throws RemoteException {
        //Atualiza vaga de emprego alterada apartir do cnpj e percorre o vetor de objetos das vagas de emprego
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
                //Atualiza no vetor global
                theList.set(i, v);
                return true;
            }
        }
        return false;
    }
}
