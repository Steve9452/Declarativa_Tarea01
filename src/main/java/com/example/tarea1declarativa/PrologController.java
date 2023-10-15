package com.example.tarea1declarativa;


import org.jpl7.*;

import java.lang.Integer;
import java.lang.reflect.Array;
import java.util.*;

import com.example.tarea1declarativa.Intersection;

public class PrologController {
    private final String consult = "consult('src/main/java/com/example/tarea1declarativa/navigation.pl')";
    private final Query q1 = new Query(consult);
    List<Integer> stopsId;
    private final List<IntersectionRaster.PointCord> points = new ArrayList<>();

    public List<Integer> getStopsId() {
        return stopsId;
    }


    public List<IntersectionRaster.PointCord> getPoints(){
        return points;
    }


    public void populateStopsId(int from, int to ){

        if(!q1.hasSolution()){
            System.out.println("Error al consultar");
            return;
        }

        // route(1,4,Ruta).
        //String route = "route(1,4,Ruta).";
        String route = "rutaDesdeLugar("+from+","+to+",Ruta).";
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

        // using ruta_minima2() instead of ruta_minima()
        // =====
//        List<Integer> aux = new ArrayList<Integer>();
//        aux.add(from);
//        aux.addAll(stopsId);
//        stopsId = aux;
        // =====
    }

    public void populateIntersections() {
        if (!q1.hasSolution()) {
            System.out.println("Error al consultar");
            return;
        }
        points.clear();

        stopsId.forEach( (id) -> {
            String route = "coordenada(" + id + ",Lat,Lng).";
            Query query = new Query(route);
            double lat = Double.parseDouble(query.oneSolution().get("Lat").toString());
            double lng = Double.parseDouble(query.oneSolution().get("Lng").toString());
            IntersectionRaster.PointCord intersection = new IntersectionRaster.PointCord(id, lat, lng);
            points.add(intersection);
        });
    }

}
