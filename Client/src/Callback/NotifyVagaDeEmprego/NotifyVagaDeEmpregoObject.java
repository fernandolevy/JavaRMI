package Callback.NotifyVagaDeEmprego;

import java.io.Serializable;

public class NotifyVagaDeEmpregoObject implements Serializable {
    public String area_de_interesse;
    VagaDeEmpregoInterfaceCallback callback;

    //	constructors
    public NotifyVagaDeEmpregoObject() {
    }

    public NotifyVagaDeEmpregoObject(VagaDeEmpregoInterfaceCallback aCallback, String aArea_de_interesse) {
        callback = aCallback;
        area_de_interesse = aArea_de_interesse;

    }

}