package Callback.NotifyCurriculo;

import java.io.Serializable;

public class NotifyCurriculoObject implements Serializable {
    public String area_de_interesse;
    CurriculoInterfaceCallback callback;

    //	constructors
    public NotifyCurriculoObject() {
    }

    public NotifyCurriculoObject(CurriculoInterfaceCallback aCallback, String aArea_de_interesse) {
        callback = aCallback;
        area_de_interesse = aArea_de_interesse;
    }

}