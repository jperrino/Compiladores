package vehiculo;

public class Camioneta extends Vehiculo {

    public Camioneta(String patente) {
        super(patente);
    }

    @Override
    public int calculoTarifa(int minutos) {
        int tiempo = (minutos/60);
        return tiempo*15;
    }
}
