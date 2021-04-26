package garage;

import vehiculo.Vehiculo;

import java.util.HashMap;
import java.util.Map;

public class DBFacker implements GarageDB {

    Map<Integer, Map<Integer, Object>> objects = new HashMap<Integer, Map<Integer, Object>>() {{
        for(int i = 1; i<=4; i++){
            put(i, new HashMap<Integer,Object>(){{
                for(int j = 1; j<=20; j++){
                    put(j,null);
                }
            }});
        }
    }};

    @Override
    public Vehiculo save(Integer piso, Integer lugar, Vehiculo vehiculo) {
        if (!objects.containsKey(piso)) {
            throw new RuntimeException("No existe ese piso");
        }

        if (!objects.get(piso).containsKey(lugar)) {
            throw new RuntimeException("No existe ese lugar");
        }

        if (objects.get(piso).get(lugar) != null) {
            throw new RuntimeException("Ya existe un vehiculo en esa posicion. " + searchForSpot());
        }
        objects.get(piso).put(lugar,vehiculo);
        return vehiculo;
    }

    @Override
    public Vehiculo getSpotConent(Integer piso, Integer lugar) {
        return (Vehiculo) objects.get(piso).get(lugar);
    }


    @Override
    public String searchForSpot(){
        String message = null;
        for (Map.Entry<Integer, Map<Integer, Object>> set1 : objects.entrySet()) {
            for(Map.Entry<Integer,Object> set2 : set1.getValue().entrySet()) {
                if(set2.getValue() == null){
                    message =  "Posicion libre en piso: " + set1.getKey() + ", lugar: " + set2.getKey();
                    break;
                }
            }
        }
        return message;
    }
}
;