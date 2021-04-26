package vehiculo;

public abstract class Vehiculo {

    protected String patente;

    public Vehiculo(String patente) {
        this.patente = patente;
    }

    public String getPatente(){
        return this.patente;
    }
    public abstract int calculoTarifa(int minutos);
}
