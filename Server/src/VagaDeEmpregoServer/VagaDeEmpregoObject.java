package VagaDeEmpregoServer;

import java.io.Serializable;

//Este objeto armazena as vagas de emprego cadastradas
public class VagaDeEmpregoObject implements Serializable {
    public String area_da_vaga;
    public String salario_pago;
    public String contato_empresa;
    public String nome_empresa;
    public String cnpj;
    public String carga_horaria;

    //	constructors
    public VagaDeEmpregoObject() {
    }

    public VagaDeEmpregoObject(String aArea_da_vaga, String aSalario_pago, String aNome_empresa, String aCnpj, String aContato_empresa, String aCarga_horaria) {
        area_da_vaga = aArea_da_vaga;
        salario_pago = aSalario_pago;
        nome_empresa = aNome_empresa;
        contato_empresa = aContato_empresa;
        carga_horaria = aCarga_horaria;
        cnpj = aCnpj;
    }

}