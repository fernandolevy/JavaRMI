package CurriculoServer;

import Callback.NotifyCurriculo.CurriculoInterfaceCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface CurriculoInterface extends Remote {
    //Está função é responsavel por cadastrar os curriculos dos candidatos
    void cadastrarCurriculo(CurriculoObject g) throws RemoteException;

    //Está função é responsável por consultar os curriculos dos candidatos apartir da busca da empresa
    Vector consultarCurriculo(String area_de_interesse, String salario_pretendido) throws RemoteException;

    //Está função é responsável por alterar os curriculos dos candidatos apartir da entrada do funcionario que a cadastrou
    boolean alterarCurriculo(String cpf, CurriculoObject v) throws RemoteException;

    //Está função é responsável por printar os curriculos após as funções de cadastrar e alterar serem executadas
    Vector getCurriculos() throws RemoteException;

    //Está função é responsável por registrar interesse nos curriculos dos candidatos apartir do registro da empresa
    void registraInteresseCurriculo(CurriculoInterfaceCallback callback, String area_de_interesse) throws RemoteException;

}
