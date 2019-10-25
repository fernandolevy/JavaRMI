package CurriculoServer;

import Callback.NotifyCurriculo.CurriculoInterfaceCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

//Esta classe e responsavel por implementar e servir os metodos decladaros na CurriculoInterface
public class CurriculoServant extends UnicastRemoteObject implements CurriculoInterface {
    //Vetor global com os curriculos
    private Vector theList;
    //Vetor global com as areas de interesse
    private Vector interesseList;
    //Vetor global com a referencia dos clientes que registraram interesse em curriculos
    private Vector interesseCallbackList;

    //Inicializa vetor
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
        //Salva objeto curriculo no vetor global da classe
        theList.addElement(v);
        //Verifica se curriculo cadastrado casa com uma dos curriculos de interesse contido no vetor de Interesses registrados
        verificaInteresseCurriculo(v);
    }

    public void verificaInteresseCurriculo(CurriculoObject v) throws RemoteException {
        //Percorre vetor de objetos curriculos e verifica se casa com a area de interesse registrada pelo metodo registraInteresseCurriculo
        for (int i = 0; i < interesseList.size(); i++) {
            //salva area de interesse registrada no vetor
            String g = (String) interesseList.elementAt(i);
            //Salva a interface do callback salvo no registro de interesse
            CurriculoInterfaceCallback c = (CurriculoInterfaceCallback) interesseCallbackList.elementAt(i);
            //Caso a area de interesse registrada case com area de interesse cadastrada do Curriculo
            if (v.area_de_interesse.equals(g)) {
                //Notifica cliente com o curriculo de seu interesse
                c.notifyCurriculo(v);
            }
        }
    }

    public Vector consultarCurriculo(String area_de_interesse, String salario_pretendido) throws RemoteException {
        Vector curriculos = new Vector();
        //Percorre vetor de objetos curriculos ate encontrar objeto com a area de interesse e salario pretendido enviado pela Empresa
        for (int i = 0; i < theList.size(); i++) {
            CurriculoObject g = (CurriculoObject) theList.elementAt(i);
            if (area_de_interesse.equals(g.area_de_interesse) && (salario_pretendido.equals(g.salario_pretendido))) {
                //Salvar em um vetor de objetos curriculos
                curriculos.addElement(g);
            }
        }
        //Retorna curriculo encontrado
        return curriculos;
    }


    public Vector getCurriculos() throws RemoteException {
        //retorna Vector global com os curriculos
        return theList;
    }


    public void registraInteresseCurriculo(CurriculoInterfaceCallback callback, String area_de_interesse) throws RemoteException {
        //Salva o interesse em um vetor de strings
        interesseList.addElement(area_de_interesse);
        //Salva o callback em um vetor das Interfaces do callback
        interesseCallbackList.addElement(callback);
    }

    public boolean alterarCurriculo(String cpf, CurriculoObject v) throws RemoteException {
        //Atualiza vaga de emprego alterada apartir do cpf e percorre o vetor de objetos dos Curriculos
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
                //Atualiza o curriculo no vetor global
                theList.set(i, v);
                return true;
            }
        }
        return false;
    }

}
