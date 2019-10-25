import Callback.NotifyCurriculo.CurriculoInterfaceCallback;
import Callback.NotifyCurriculo.CurriculoServantCallback;
import Callback.NotifyCurriculo.NotifyCurriculoObject;
import CurriculoServer.CurriculoInterface;
import CurriculoServer.CurriculoObject;
import VagaDeEmpregoServer.VagaDeEmpregoInterface;
import VagaDeEmpregoServer.VagaDeEmpregoObject;

import java.rmi.registry.LocateRegistry;

import java.rmi.registry.Registry;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;
import java.util.Vector;


public class ClientVagaDeEmprego {
    public static void main(String[] args) {
        Scanner options = new Scanner(System.in);  // Create a Scanner object
        String option = "0";
        while (!option.equals("4")) {
            System.out.println("Digite 1 para CADASTRAR uma vaga de Emprego");
            System.out.println("Digite 2 para ALTERAR vaga de emprego cadastrada");
            System.out.println("Digite 3 para CONSULTAR os curriculos dos candidatos");
            System.out.println("Digite 4 para REGISTRAR interesse nos curriculos dos candidatos");
            System.out.println("Digite 5 para sair");

            option = options.nextLine();
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            try {
                if ((option.equals("1")) || (option.equals("2"))) {
                    VagaDeEmpregoInterface aVagaDeEmpregoInterface = null;
                    Registry r = LocateRegistry.getRegistry("localhost", 2001);
                    aVagaDeEmpregoInterface = (VagaDeEmpregoInterface) r.lookup("VagaDeEmpregoServer.VagaDeEmpregoInterface");
                    System.out.println("Conectado ao servidor de vagas de emprego");
                    System.out.println("CNPJ: ");
                    String cnpj = myObj.nextLine();  // Read user input
                    System.out.println("Nome da Empresa: ");
                    String nome_empresa = myObj.nextLine();  // Read user input
                    System.out.println("Area da vaga: ");
                    String area_da_vaga = myObj.nextLine();  // Read user input
                    System.out.println("Salario Pago: ");
                    String salario_pago = myObj.nextLine();  // Read user input
                    System.out.println("Carga horaria: ");
                    String carga_horaria = myObj.nextLine();  // Read user input
                    System.out.println("Contato Empresa: ");
                    String contato_empresa = myObj.nextLine();  // Read user input
                    VagaDeEmpregoObject g = new VagaDeEmpregoObject(area_da_vaga, salario_pago, nome_empresa, cnpj, contato_empresa, carga_horaria);
                    if (option.equals("1")) {
                        System.out.println("Vaga de Emprego Cadastrada");
                        aVagaDeEmpregoInterface.cadastrarVagaDeEmprego(g);
                        System.out.println("Vaga de Emprego Salvo");
                    } else {
                        System.out.println("Vaga de Emprego Alterada");
                        boolean operation = aVagaDeEmpregoInterface.alterarVagaDeEmprego(cnpj, g);
                        if (operation) {
                            System.out.println("SUCESSO: Vaga de Emprego salva");
                        } else {
                            System.out.println("FALHA: Vaga de Emprego não foi salva");
                        }
                    }
                    System.out.println("|-------------------------------------------------------------------------------|");
                    System.out.println("Listagem dos Curriculos: ");
                    Vector all_vagas = aVagaDeEmpregoInterface.getVagasDeEmprego();
                    for (int i = 0; i < all_vagas.size(); i++) {
                        VagaDeEmpregoObject v = (VagaDeEmpregoObject) all_vagas.elementAt(i);
                        System.out.println("\n|---------------------------------------------------|");
                        System.out.println("Vaga de Emprego Cadastrada " + i + ":");
                        System.out.println("        nome_empresa: " + v.nome_empresa);
                        System.out.println("        area_da_vaga: " + v.area_da_vaga);
                        System.out.println("        carga_horaria: " + v.carga_horaria);
                        System.out.println("        cnpj:" + v.cnpj);
                        System.out.println("        contato_empresa:" + v.contato_empresa);
                        System.out.println("|---------------------------------------------------|\n");
                    }
                    System.out.println("|-------------------------------------------------------------------------------|");

                } else if (option.equals("3") || option.equals("4")) {
                    Registry r = LocateRegistry.getRegistry("localhost", 2002);
                    CurriculoInterface aCurriculoInterface = null;
                    aCurriculoInterface = (CurriculoInterface) r.lookup("CurriculoServer.CurriculoInterface");
                    System.out.println("Conectado no servidor de curriculos");
                    if ( option.equals("3")) {
                        System.out.println("Digite a area em que deseja contratar: ");
                        String area_de_interesse = myObj.nextLine();  // Read user input
                        System.out.println("Digite o salario que deseja oferecer: ");
                        String salario_pretendido = myObj.nextLine();  // Read user input
                        Vector curriculos = aCurriculoInterface.consultarCurriculo(area_de_interesse, salario_pretendido);
                        if (curriculos.size() > 0) {
                            for (int i = 0; i < curriculos.size(); i++) {
                                CurriculoObject g = (CurriculoObject) curriculos.elementAt(i);
                                System.out.println("\n\n|---------------------------------------------------|");
                                System.out.println("Curriculos Encontrados");
                                System.out.println("        nome_candidato: " + g.nome_candidato);
                                System.out.println("        area_de_interesse: " + g.area_de_interesse);
                                System.out.println("        carga_horaria: " + g.carga_horaria);
                                System.out.println("        cpf:" + g.cpf);
                                System.out.println("        contato_candidato:" + g.contato_candidato);
                                System.out.println("|---------------------------------------------------|\n\n");
                            }
                        } else {
                            System.out.println("Não foi encontrado candidatos na area desejada com o salario oferecido");
                        }
                    }else if (option.equals("4")){
                        System.out.println("Digite qual area de interesse deseja receber Vagas de Emprego: ");
                        String area_de_interesse = myObj.nextLine();  // Read user input
                        CurriculoInterfaceCallback aCurriculoInterfaceCallback = new CurriculoServantCallback();
                        NotifyCurriculoObject c = new NotifyCurriculoObject(aCurriculoInterfaceCallback, area_de_interesse);
                        aCurriculoInterface.registraInteresseCurriculo(aCurriculoInterfaceCallback, c);
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


