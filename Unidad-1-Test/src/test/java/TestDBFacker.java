import garage.DBFacker;
import garage.GarageDB;
import garage.GarageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vehiculo.Auto;
import vehiculo.Camioneta;
import vehiculo.Vehiculo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDBFacker {

    @DisplayName("Garage test Mock save auto")
    @Test
    void saveAuto()  {
        DBFacker dbFacker = new DBFacker();
        Vehiculo v1 = (Vehiculo) dbFacker.save(1,1,new Auto("abc123"));
        assertTrue(v1.getClass().equals(Auto.class));
        assertEquals(v1.getPatente(),"abc123");
    }

    @DisplayName("Garage test Mock save camioneta")
    @Test
    void saveCamioneta()  {
        DBFacker dbFacker = new DBFacker();
        Vehiculo v1 = (Vehiculo) dbFacker.save(1,1,new Camioneta("abc123"));
        assertTrue(v1.getClass().equals(Camioneta.class));
        assertEquals(v1.getPatente(),"abc123");
    }

    @DisplayName("Garage test Mock save dos vehiculos")
    @Test
    void saveDosVehiculos()  {
        DBFacker dbFacker = new DBFacker();
        //Object o = dbFacker.save(1,1,"abc123");
        Vehiculo v1 = (Vehiculo) dbFacker.save(1,1,new Auto("abc123"));
        assertEquals(v1.getPatente(),"abc123");
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, () -> {
            //Object o2 = dbFacker.save(1,1,"def456");
            Vehiculo v2 = (Vehiculo) dbFacker.save(1,1,new Camioneta("def456"));
        });
        assertEquals(e.getMessage(),"Ya existe un vehiculo en esa posicion. Posicion libre en piso: 4, lugar: 1");

    }

    @DisplayName("Garage test Mock not ok")
    @Test
    void saveWithErrorPiso()  {
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, () -> {
            DBFacker dbFacker = new DBFacker();
            Vehiculo v1 = (Vehiculo) dbFacker.save(125,1,new Auto("abc123"));
            //Object o = dbFacker.save(125,1,"abc123");
        });
        assertEquals(e.getMessage(),"No existe ese piso");

    }

    @DisplayName("Garage test Mock not ok 2")
    @Test
    void saveWithErrorPosicion()  {
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, () -> {
            DBFacker dbFacker = new DBFacker();
            Vehiculo v1 = (Vehiculo) dbFacker.save(1,125,new Auto("abc123"));
            //Object o = dbFacker.save(1,125,"abc123");
        });
        assertEquals(e.getMessage(),"No existe ese lugar");

    }


}
