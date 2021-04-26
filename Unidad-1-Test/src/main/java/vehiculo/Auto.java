package vehiculo;

public class Auto extends Vehiculo {

    public Auto(String patente) {
        super(patente);
    }

    @Override
    public int calculoTarifa(int minutos) {
        int tiempo = (minutos/60);
        return tiempo*10;
    }
}