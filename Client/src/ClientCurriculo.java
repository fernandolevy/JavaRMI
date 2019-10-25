import Callback.NotifyVagaDeEmprego.NotifyVagaDeEmpregoObject;
import Callback.NotifyVagaDeEmprego.VagaDeEmpregoInterfaceCallback;
import Callback.NotifyVagaDeEmprego.VagaDeEmpregoServantCallback;
import CurriculoServer.CurriculoInterface;
import CurriculoServer.CurriculoObject;
import VagaDeEmpregoServer.VagaDeEmpregoInterface;
import VagaDeEmpregoServer.VagaDeEmpregoObject;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;  // Import the Scanner class

import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;


public class ClientCurriculo {
    public static void main(String[] args) {
        Scanner options = new Scanner(System.in);  // Create a Scanner object
        String option = "0";
        while (!option.equals("4")) {
            System.out.println("Digite 1 para CADASTRAR Curriculo");
            System.out.println("Digite 2 para ALTERAR curriculo cadastrado");
            System.out.println("Digite 3 para CONSULTAR as vagas de emprego");
            System.out.println("Digite 4 para REGISTRAR interesse nas vagas de emprego");
            System.out.println("Digite 5 para sair");
            option = options.nextLine();
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            try {
                if ((option.equals("1")) || (option.equals("2"))) {
                    CurriculoInterface aCurriculoInterface = null;
                    Registry r = LocateRegistry.getRegistry("localhost", 2002);
                    System.out.println("Conectado no servidor de curriculos");
                    aCurriculoInterface = (CurriculoInterface) r.lookup("CurriculoServer.CurriculoInterface");
                    System.out.println("CPF: ");
                    String cpf = myObj.nextLine();  // Read user input
                    System.out.println("Nome: ");
                    String nome_candidato = myObj.nextLine();  // Read user input
                    System.out.println("Area de interesse: ");
                    String area_de_interesse = myObj.nextLine();  // Read user input
                    System.out.println("Salario pretendido: ");
                    String salario_pretendido = myObj.nextLine();  // Read user input
                    System.out.println("Carga horaria: ");
                    String carga_horaria = myObj.nextLine();  // Read user input
                    System.out.println("Contato candidato: ");
                    String contato_candidato = myObj.nextLine();  // Read user input
                    CurriculoObject g = new CurriculoObject(area_de_interesse, salario_pretendido, nome_candidato, cpf, contato_candidato, carga_horaria);
                    if (option.equals("1")) {
                        System.out.println("Curriculo Cadastrado");
                        aCurriculoInterface.cadastrarCurriculo(g);
                        System.out.println("Curriculo salvo");
                    } else {
                        System.out.println("Curriculo Alterado");
                        Boolean operation = aCurriculoInterface.alterarCurriculo(cpf, g);
                        if (operation) {
                            System.out.println("SUCESSO: Curriculo salvo");
                        } else {
                            System.out.println("FALHA: Curriculo não foi salvo");
                        }
                    }
                    System.out.println("|-------------------------------------------------------------------------------|");
                    System.out.println("Listagem dos Curriculos: ");
                    Vector all_curriculos = aCurriculoInterface.getCurriculos();
                    for (int i = 0; i < all_curriculos.size(); i++) {
                        CurriculoObject v = (CurriculoObject) all_curriculos.elementAt(i);
                        System.out.println("\n|---------------------------------------------------|");
                        System.out.println("Curriculo Cadastrado " + i + ":");
                        System.out.println("        nome_candidato: " + v.nome_candidato);
                        System.out.println("        area_de_interesse: " + v.area_de_interesse);
                        System.out.println("        carga_horaria: " + v.carga_horaria);
                        System.out.println("        cpf: " + v.cpf);
                        System.out.println("        contato_candidato: " + v.contato_candidato);
                        System.out.println("|---------------------------------------------------|\n");
                    }
                    System.out.println("|-------------------------------------------------------------------------------|");

                } else if (option.equals("3") || option.equals("4")) {
                    Registry r = LocateRegistry.getRegistry("localhost", 2001);
                    VagaDeEmpregoInterface aVagaDeEmpregoInterface = null;
                    aVagaDeEmpregoInterface = (VagaDeEmpregoInterface) r.lookup("VagaDeEmpregoServer.VagaDeEmpregoInterface");
                    System.out.println("Conectado no servidor de vagas de emprego");
                    if (option.equals("3")) {
                        System.out.println("Digite a area em que deseja trabalhar: ");
                        String area_de_interesse = myObj.nextLine();  // Read user input
                        Vector vagas = aVagaDeEmpregoInterface.consultarVagaDeEmprego(area_de_interesse);
                        if (vagas.size() > 0) {
                            for (int i = 0; i < vagas.size(); i++) {
                                VagaDeEmpregoObject g = (VagaDeEmpregoObject) vagas.elementAt(i);
                                System.out.println("\n\n|---------------------------------------------------|");
                                System.out.println("Vaga de Emprego Encontradas");
                                System.out.println("        nome_empresa: " + g.nome_empresa);
                                System.out.println("        area_da_vaga: " + g.area_da_vaga);
                                System.out.println("        carga_horaria: " + g.carga_horaria);
                                System.out.println("        cnpj:" + g.cnpj);
                                System.out.println("        contato_empresa:" + g.contato_empresa);
                                System.out.println("|---------------------------------------------------|\n\n");
                            }
                        } else {
                            System.out.println("Não foi encontrado vagas de emprego na area desejada");
                        }
                    }else if (option.equals("4")){
                        System.out.println("Digite qual area de interesse deseja receber Vagas de Emprego: ");
                        String area_de_interesse = myObj.nextLine();  // Read user input
                        VagaDeEmpregoInterfaceCallback aVagaDeEmpregoInterfaceCallback = new VagaDeEmpregoServantCallback();
                        NotifyVagaDeEmpregoObject c = new NotifyVagaDeEmpregoObject(aVagaDeEmpregoInterfaceCallback, area_de_interesse);
                        aVagaDeEmpregoInterface.registraInteresseVagaDeEmprego(aVagaDeEmpregoInterfaceCallback, c);
                    }
                }
            } catch (RemoteException e) {
                System.out.println("allShapes: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Lookup: " + e.getMessage());
            }
        }
    }
}


