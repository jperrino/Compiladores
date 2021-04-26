package garage;

import vehiculo.Vehiculo;

public class GarageService {

    private GarageDB garage;

    public GarageService(GarageDB garage) {
        this.garage = garage;
    }

    public String park(Integer piso, Integer lugar, Vehiculo vehiculo) {
        return garage.save(piso, lugar, vehiculo).getPatente();
    }

    public String unpark(Integer piso, Integer lugar) {
        try{
            return garage.save(piso, lugar, null).getPatente();
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public int calculateFee(Integer piso, Integer lugar, int minutos){
        return garage.getSpotConent(piso, lugar).calculoTarifa(minutos);
    }
}
