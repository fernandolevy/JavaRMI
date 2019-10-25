package CurriculoServer;

import java.io.Serializable;

public class CurriculoObject implements Serializable {
    public String area_de_interesse;
    public String salario_pretendido;
    public String carga_horaria;
    public String contato_candidato;
    public String nome_candidato;
    public String cpf;

    //	constructors
    public CurriculoObject() {
    }

    public CurriculoObject(String aArea_de_interesse, String aSalario_pretendido, String aNome, String aCpf, String aContato_candidato, String aCarga_horaria) {
        area_de_interesse = aArea_de_interesse;
        carga_horaria = aCarga_horaria;
        contato_candidato = aContato_candidato;
        salario_pretendido = aSalario_pretendido;
        nome_candidato = aNome;
        cpf = aCpf;
    }

}