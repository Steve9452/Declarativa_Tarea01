package com.example.tarea1declarativa;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class IntersectionRaster {
    public static List<Graphic> getPointsRaster() {


        List<Graphic> graphics = new ArrayList<>();
        try {
            File file = new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("coords.json")).getFile());
            FileReader reader = new FileReader(file);


            Gson gson = new Gson();
            List<PointCord> points = gson.fromJson(reader, List.class);


            SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);
            for (
                    PointCord point : Objects.requireNonNull(points)) {
                Point p = new Point(point.longitude, point.latitude, SpatialReferences.getWgs84());

                Graphic graphic = new Graphic(p, symbol);
                graphic.getAttributes().put("id", point.id);
                graphics.add(graphic);
            }


            return graphics ;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return graphics;
    }
    static class PointCord {
        @SerializedName("id")
        public int id;
        @SerializedName("longitude")
        public double longitude;
        @SerializedName("latitude")
        public double latitude;
        public PointCord(int id, double longitude, double latitude) {
            this.id = id;
            this.longitude = longitude;
            this.latitude = latitude;
        }
        @Override
        public String toString() {
            return String.format("(id: %d, latitude: %f, longitude: %f)", id, latitude, longitude);
        }
    }
}
