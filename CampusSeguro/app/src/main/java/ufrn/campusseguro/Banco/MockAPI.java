package ufrn.campusseguro.Banco;

import ufrn.campusseguro.Model.RegistrosAssaltos;

/**
 * Created by Wesley Reuel on 06/12/2017.
 */

public class MockAPI {

    public void inserirMockDados(DBHandler dbHandler) {
        if(dbHandler.getAllRegistros().size() == 0){
            RegistrosAssaltos registrosAssaltos0 = new RegistrosAssaltos(1, 1, -5.836302, -35.209755, "2017-11-09 20:00", "Assalto");
            RegistrosAssaltos registrosAssaltos1 = new RegistrosAssaltos(2, 1, -5.8333001, -35.2058291, "2017-11-08 20:00", "Assalto");
            RegistrosAssaltos registrosAssaltos2 = new RegistrosAssaltos(3, 1, -5.8331747, -35.2046221, "2017-11-07 20:00", "Assalto");
            RegistrosAssaltos registrosAssaltos3 = new RegistrosAssaltos(4, 1, -5.833637, -35.204341, "2017-11-06 21:00", "Assalto");
            RegistrosAssaltos registrosAssaltos4 = new RegistrosAssaltos(5, 1, -5.833712, -35.203774, "2017-11-07 22:00", "Assalto");
            RegistrosAssaltos registrosAssaltos5 = new RegistrosAssaltos(6, 1, -5.833012, -35.205624, "2017-11-08 08:00", "Assalto");
            RegistrosAssaltos registrosAssaltos6 = new RegistrosAssaltos(7, 1, -5.835029, -35.204257, "2017-11-06 02:00", "Assalto");
            RegistrosAssaltos registrosAssaltos7 = new RegistrosAssaltos(8, 1, -5.833486, -35.205708, "2017-11-05 14:00", "Assalto");
            RegistrosAssaltos registrosAssaltos8 = new RegistrosAssaltos(9, 1, -5.834216, -35.205584, "2017-11-08 15:00", "Assalto");
            RegistrosAssaltos registrosAssaltos9 = new RegistrosAssaltos(10, 1, -5.833742, -35.202867, "2017-11-08 16:00", "Assalto");
            dbHandler.addRegistro(registrosAssaltos0);
            dbHandler.addRegistro(registrosAssaltos1);
            dbHandler.addRegistro(registrosAssaltos2);
            dbHandler.addRegistro(registrosAssaltos3);
            dbHandler.addRegistro(registrosAssaltos4);
            dbHandler.addRegistro(registrosAssaltos5);
            dbHandler.addRegistro(registrosAssaltos6);
            dbHandler.addRegistro(registrosAssaltos7);
            dbHandler.addRegistro(registrosAssaltos8);
            dbHandler.addRegistro(registrosAssaltos9);
        }
    }


}
