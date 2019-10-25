package VagaDeEmpregoServer;

import Callback.NotifyCurriculo.NotifyCurriculoObject;
import Callback.NotifyVagaDeEmprego.NotifyVagaDeEmpregoObject;
import Callback.NotifyVagaDeEmprego.VagaDeEmpregoInterfaceCallback;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface VagaDeEmpregoInterface extends Remote {
    void cadastrarVagaDeEmprego(VagaDeEmpregoObject g) throws RemoteException;

    Vector consultarVagaDeEmprego(String area_da_vaga) throws RemoteException;

    boolean alterarVagaDeEmprego(String cnpj, VagaDeEmpregoObject v) throws RemoteException;

    Vector getVagasDeEmprego() throws RemoteException;

    void registraInteresseVagaDeEmprego(VagaDeEmpregoInterfaceCallback callback, NotifyVagaDeEmpregoObject v) throws RemoteException;

}
