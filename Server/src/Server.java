import CurriculoServer.CurriculoInterface;
import CurriculoServer.CurriculoServant;
import VagaDeEmpregoServer.VagaDeEmpregoInterface;
import VagaDeEmpregoServer.VagaDeEmpregoServant;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import java.rmi.*;

public class Server {
    public static void main(String args[]) {
        try {
            //Inicializa a interface com sua implementação
            CurriculoInterface aCurriculoInterface = new CurriculoServant();
            //Registra a porta do servidor no java RMI
            Registry registryCurriculo = LocateRegistry.createRegistry(2002);
            //Inicializa o servidor de curriculos
            registryCurriculo.rebind("CurriculoServer.CurriculoInterface", aCurriculoInterface);
            System.out.println("CurriculoServer server ready");
        } catch (Exception e) {
            System.out.println("Curriculo server main " + e.getMessage());
        }
        try {
            //Inicializa a interface com sua implementação
            VagaDeEmpregoInterface aVagaDeEmpregoInterface = new VagaDeEmpregoServant();
            //Registra a porta do servidor no java RMI
            Registry registryVagaDeEmprego = LocateRegistry.createRegistry(2001);
            //Inicializa o servidor de vagas de emprego
            registryVagaDeEmprego.rebind("VagaDeEmpregoServer.VagaDeEmpregoInterface", aVagaDeEmpregoInterface);
            System.out.println("VagaDeEmprego server ready");
        } catch (Exception e) {
            System.out.println("VagaDeEmprego server main " + e.getMessage());
        }
    }
}