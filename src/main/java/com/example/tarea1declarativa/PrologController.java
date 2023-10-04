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

    List<Integer> stops;

    private final Map<Integer, Intersection> intersections = new HashMap<>();

    public PrologController() {
        this.populateStopsId();
        this.populateIntersections();
    }


    private void populateStopsId(){

        if(!q1.hasSolution()){
            System.out.println("Error al consultar");
            return;
        }

        // route(1,4,Ruta).
        String route = "route(1,4,Ruta).";
        Query query = new Query(route);

        // Saving onSolution into list


        String[] pointsId = query.oneSolution().get("Ruta").toString()
                .replaceAll("^\\[|]$", "")
                .split(",\\s*");

        // Inserting into list
        this.stops = Arrays.stream(pointsId).map(Integer::parseInt).toList();

    }

    private void populateIntersections() {
        if (!q1.hasSolution()) {
            System.out.println("Error al consultar");
            return;
        }

        stops.forEach( (id) -> {
            String route = "coordenada(" + id + ",Lat,Lng).";
            Query query = new Query(route);
            int lat = Integer.parseInt(query.oneSolution().get("Lat").toString());
            int lng = Integer.parseInt(query.oneSolution().get("Lng").toString());
            Intersection intersection = new Intersection(id, lat, lng);
            intersections.put(id, intersection);
        });


    }
    public List<Integer> getStops() {
        return stops;
    }

    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }
}
