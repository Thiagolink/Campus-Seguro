package ufrn.campusseguro.Banco;

import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by tt200 on 07/12/2017.
 */

public class MockEstatisticas {

    public DataPoint[] inserirDados (int dado){


        switch(dado){
            case 1:
                return new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6)
                };
            case 2:
                return new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 4),
                        new DataPoint(2, 2),
                        new DataPoint(3, 1),
                        new DataPoint(4, 5)
                };
            case 3:
                return new DataPoint[] {
                        new DataPoint(0, 0),
                        new DataPoint(1, 3),
                        new DataPoint(2, 1),
                        new DataPoint(3, 0),
                        new DataPoint(4, 4)
                };
            case 4:
                return new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 2),
                        new DataPoint(2, 0),
                        new DataPoint(3, 4),
                        new DataPoint(4, 3)
                };
            case 5:
                return new DataPoint[] {
                        new DataPoint(0, 1),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, 6),
                        new DataPoint(5, 2),
                        new DataPoint(6, 10),
                        new DataPoint(7, 12),
                        new DataPoint(8, 23),
                        new DataPoint(9, 25),
                        new DataPoint(10, 12),
                        new DataPoint(11, 22),
                        new DataPoint(12, 2),
                        new DataPoint(13, 51),
                        new DataPoint(14, 21),
                        new DataPoint(15, 14),
                        new DataPoint(16, 6),
                        new DataPoint(17, 7),
                        new DataPoint(18, 9),
                        new DataPoint(19, 6),
                        new DataPoint(20, 14),
                        new DataPoint(21, 24),
                        new DataPoint(22, 18),
                        new DataPoint(23, 62),
                        new DataPoint(24, 0)
                };
        }


        return new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 2),
                new DataPoint(2, 0),
                new DataPoint(3, -1),
                new DataPoint(4, 3)
        };
    }
}
