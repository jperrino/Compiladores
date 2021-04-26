import garage.DBFacker;
import garage.GarageDB;
import garage.GarageService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.jupiter.api.*;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class TestGarageService {

    private static final Logger logger = LoggerFactory.getLogger(TestGarageService.class);
    @Mock GarageDB garage;

    @DisplayName("Garage test Mock park auto")
    @Test
    void garageParkAuto()  {
        String patente="abc123";
        //Vehiculo v1 = (Vehiculo) dbFacker.save(1,1,new Auto("abc123"));
        Vehiculo v1 = new Auto("abc123");
        Mockito.lenient().when(garage.save(1,1,v1)).thenReturn(v1);
        GarageService garageService = new GarageService(garage);
        String result = garageService.park(1,1,v1);
        assertEquals(result, patente);
    }

    @DisplayName("Garage test Mock park camioneta")
    @Test
    void garageParkCamioneta()  {
        String patente="abc123";
        //Vehiculo v1 = (Vehiculo) dbFacker.save(1,1,new Auto("abc123"));
        Vehiculo v1 = new Camioneta("abc123");
        Mockito.lenient().when(garage.save(1,1,v1)).thenReturn(v1);
        GarageService garageService = new GarageService(garage);
        String result = garageService.park(1,1,v1);
        assertEquals(result, patente);
    }

    @DisplayName("Garage test Mock unpark")
    @Test
    void garageUnparkAuto()  {
        //String patente= null;
        //Vehiculo v1 = (Vehiculo) dbFacker.save(1,1,new Auto("abc123"));
        Mockito.lenient().when(garage.save(1,1,null)).thenReturn(null);
        GarageService garageService = new GarageService(garage);
        String result = garageService.unpark(1,1);
        assertNull(result);
    }

    @DisplayName("Garage test not ok")
    @Test
    void garageError(){
        String patente="abc123";
        Vehiculo v1 = new Auto("abc123");
        Mockito.lenient().when(garage.save(1,1,v1)).thenReturn(v1);
        GarageService garageService = new GarageService(garage);
        String result = garageService.park(1,1,v1);
        assertEquals(result, patente);
    }

    @DisplayName("Garage test Exception")
    @Test
    void garageException() {
        String patente="abc123";
        Vehiculo v1 = new Auto("abc123");
        Mockito.lenient().when(garage.save(1,1,v1)).thenThrow(new RuntimeException("Ouch!"));
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, () -> {
            GarageService garageService = new GarageService(garage);
            String result = garageService.park(1,1,v1);
            assertEquals(result, patente);
        });
        assertEquals(e.getMessage(),"Ouch!");
    }

    @DisplayName("Garage test Calculate Auto Fee")
    @Test
    void garageAutoFee() {
        //String patente="abc123";
        DateTime startTime = new DateTime(2021,4,25,11,5);
        DateTime endTime = new DateTime(2021,4,25,14,5);;
        Period p = new Period(startTime, endTime);
        int minutes = p.getMinutes() + p.getHours()*60 + p.getDays()*(24*60) ;
        int expectedFee = 30;
        Vehiculo v1 = new Auto("abc123");
        Mockito.lenient().when(garage.save(1,1,v1)).thenReturn(v1);
        Mockito.lenient().when(garage.getSpotConent(1,1)).thenReturn(v1);
        GarageService garageService = new GarageService(garage);
        garageService.park(1,1,v1);
        int fee = garageService.calculateFee(1,1,minutes);
        assertEquals(expectedFee, fee);
    }

    @DisplayName("Garage test Calculate Camioneta Fee")
    @Test
    void garageCaminetaFee() {
        DateTime startTime = new DateTime(2021,4,25,11,5);
        DateTime endTime = new DateTime(2021,4,25,14,5);;
        Period p = new Period(startTime, endTime);
        int minutes = p.getMinutes() + p.getHours()*60 + p.getDays()*(24*60) ;
        int expectedFee = 45;
        Vehiculo v1 = new Camioneta("abc123");
        Mockito.lenient().when(garage.save(1,1,v1)).thenReturn(v1);
        Mockito.lenient().when(garage.getSpotConent(1,1)).thenReturn(v1);
        GarageService garageService = new GarageService(garage);
        garageService.park(1,1,v1);
        int fee = garageService.calculateFee(1,1,minutes);
        assertEquals(expectedFee, fee);
    }

}
