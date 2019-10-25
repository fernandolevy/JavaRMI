package CurriculoServer;

import Callback.NotifyCurriculo.CurriculoInterfaceCallback;
import Callback.NotifyCurriculo.NotifyCurriculoObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface CurriculoInterface extends Remote {
    void cadastrarCurriculo(CurriculoObject g) throws RemoteException;

    Vector consultarCurriculo(String area_de_interesse, String salario_pretendido) throws RemoteException;

    boolean alterarCurriculo(String cpf, CurriculoObject v) throws RemoteException;

    Vector getCurriculos() throws RemoteException;

    void registraInteresseCurriculo(CurriculoInterfaceCallback callback, NotifyCurriculoObject c) throws RemoteException;

}
