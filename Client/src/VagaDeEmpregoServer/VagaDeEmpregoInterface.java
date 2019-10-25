package VagaDeEmpregoServer;

import Callback.NotifyVagaDeEmprego.VagaDeEmpregoInterfaceCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface VagaDeEmpregoInterface extends Remote {

    //Está função é responsavel por cadastrar as vagas de emprego
    void cadastrarVagaDeEmprego(VagaDeEmpregoObject g) throws RemoteException;

    //Está função é responsável por consultar as vagas de emprego apartir da busca dos candidatos
    Vector consultarVagaDeEmprego(String area_da_vaga) throws RemoteException;

    //Está função é responsável por alterar as vagas de emprego apartir da entrada da empresa que a cadastrou
    boolean alterarVagaDeEmprego(String cnpj, VagaDeEmpregoObject v) throws RemoteException;

    //Está função é responsável por printar as vagas de emprego após as funções de cadastrar e alterar serem executadas
    Vector getVagasDeEmprego() throws RemoteException;

    //Está função é responsável por registrar interesse nas vagas de emprego apartir do registro do candidato
    void registraInteresseVagaDeEmprego(VagaDeEmpregoInterfaceCallback callback, String area_de_interesse) throws RemoteException;

}
