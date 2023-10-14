package com.example.tarea1declarativa;


import org.jpl7.*;

import java.lang.Integer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.tarea1declarativa.Intersection;

public class PrologController {
    private final String consult = "consult('src/main/java/com/example/tarea1declarativa/navigation.pl')";
    private final Query q1 = new Query(consult);
    List<Integer> stopsId;
    private final HashMap<Integer, Intersection> intersections = new HashMap<>();


    public List<Integer> getStops() {
        return stopsId;
    }

    public HashMap<Integer, Intersection> getIntersections() {
        return intersections;
    }

    public void populateStopsId(int from, int to ){

        if(!q1.hasSolution()){
            System.out.println("Error al consultar");
            return;
        }

        // route(1,4,Ruta).
        //String route = "route(1,4,Ruta).";
        String route = "ruta_minima("+from+","+to+",Ruta).";
        Query query = new Query(route);

        // Saving onSolution into list

        //query.allSolutions();
        String[] pointsId = query.oneSolution().get("Ruta").toString()
                .replaceAll("^\\[|]$", "")
                .split(",\\s*");

//        String[] allPoints = Arrays.toString(query.allSolutions())
//                .replaceAll("^\\[|]$", "")
//                .split(",\\s*");
        System.out.println(Arrays.toString(pointsId));
        //System.out.println(Arrays.toString(allPoints));
        // Inserting into list
        this.stopsId = Arrays.stream(pointsId).map(Integer::parseInt).toList();

    }

    public void populateIntersections() {
        if (!q1.hasSolution()) {
            System.out.println("Error al consultar");
            return;
        }
        intersections.clear();

        stopsId.forEach( (id) -> {
            String route = "coordenada(" + id + ",Lat,Lng).";
            Query query = new Query(route);
            double lat = Double.parseDouble(query.oneSolution().get("Lat").toString());
            double lng = Double.parseDouble(query.oneSolution().get("Lng").toString());
            Intersection intersection = new Intersection(id, lat, lng);
            intersections.put(id, intersection);
        });

//        for (Map.Entry<Integer, Intersection> entry : intersections.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue().getLat() + " " + entry.getValue().getLon());
//        }
    }

}
