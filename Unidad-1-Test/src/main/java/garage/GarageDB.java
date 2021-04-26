package garage;

import vehiculo.Vehiculo;

public interface GarageDB {
    public Vehiculo save(Integer piso, Integer lugar, Vehiculo vehiculo);
    public Vehiculo getSpotConent(Integer piso, Integer lugar);
    public String searchForSpot();
}
