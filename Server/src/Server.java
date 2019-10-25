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
            CurriculoInterface aCurriculoInterface = new CurriculoServant();
            Registry registryCurriculo = LocateRegistry.createRegistry(2002);
            registryCurriculo.rebind("CurriculoServer.CurriculoInterface", aCurriculoInterface);
            System.out.println("CurriculoServer server ready");
        } catch (Exception e) {
            System.out.println("Curriculo server main " + e.getMessage());
        }
        try {
            VagaDeEmpregoInterface aVagaDeEmpregoInterface = new VagaDeEmpregoServant();
            Registry registryVagaDeEmprego = LocateRegistry.createRegistry(2001);
            registryVagaDeEmprego.rebind("VagaDeEmpregoServer.VagaDeEmpregoInterface", aVagaDeEmpregoInterface);
            System.out.println("VagaDeEmprego server ready");
        } catch (Exception e) {
            System.out.println("VagaDeEmprego server main " + e.getMessage());
        }
    }
}