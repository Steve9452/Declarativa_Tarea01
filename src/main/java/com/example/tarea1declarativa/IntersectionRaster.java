package com.example.tarea1declarativa;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class IntersectionRaster {

    public static class PointCord {
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
    public static List<Graphic> getPointsRaster() {


        List<Graphic> graphics = new ArrayList<>();
        try {
            File file = new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("coords.json")).getFile());
            FileReader reader = new FileReader(file);


            Gson gson = new Gson();
            String json = "[\n" +
                    "  {\"id\": 1, \"latitude\": -89.224267304412422, \"longitude\": 13.703150717800634},\n" +
                    "  {\"id\": 2, \"latitude\": -89.227109209527967, \"longitude\": 13.703067050713086},\n" +
                    "  {\"id\": 3, \"latitude\": -89.228896165017289, \"longitude\": 13.702962466811764},\n" +
                    "  {\"id\": 4, \"latitude\": -89.230747709259219, \"longitude\": 13.702774215672081},\n" +
                    "  {\"id\": 5, \"latitude\": -89.232706901422205, \"longitude\": 13.702753298869473},\n" +
                    "  {\"id\": 6, \"latitude\": -89.232728431006436, \"longitude\": 13.703652719700306},\n" +
                    "  {\"id\": 7, \"latitude\": -89.230618531753976, \"longitude\": 13.703736386579315},\n" +
                    "  {\"id\": 8, \"latitude\": -89.22885310584887, \"longitude\": 13.703945553646497},\n" +
                    "  {\"id\": 9, \"latitude\": -89.230446295080313, \"longitude\": 13.705828048871826},\n" +
                    "  {\"id\": 10, \"latitude\": -89.232491605580137, \"longitude\": 13.706037214076886},\n" +
                    "  {\"id\": 11, \"latitude\": -89.232427016827501, \"longitude\": 13.707940608884595},\n" +
                    "  {\"id\": 12, \"latitude\": -89.235462688200926, \"longitude\": 13.708170688530808},\n" +
                    "  {\"id\": 13, \"latitude\": -89.235634924874589, \"longitude\": 13.706371878017634},\n" +
                    "  {\"id\": 14, \"latitude\": -89.236000927806131, \"longitude\": 13.702355879266182},\n" +
                    "  {\"id\": 15, \"latitude\": -89.235828691132468, \"longitude\": 13.703924636948157},\n" +
                    "  {\"id\": 16, \"latitude\": -89.240565199658363, \"longitude\": 13.708798177329566},\n" +
                    "  {\"id\": 17, \"latitude\": -89.2408881434215, \"longitude\": 13.705033219394245},\n" +
                    "  {\"id\": 18, \"latitude\": -89.241017320926744, \"longitude\": 13.702774215672081},\n" +
                    "  {\"id\": 19, \"latitude\": -89.240328374232078, \"longitude\": 13.710785214132702},\n" +
                    "  {\"id\": 20, \"latitude\": -89.241943093047723, \"longitude\": 13.708986423642306},\n" +
                    "  {\"id\": 21, \"latitude\": -89.242222977642442, \"longitude\": 13.706622875660329},\n" +
                    "  {\"id\": 22, \"latitude\": -89.241900033879304, \"longitude\": 13.70287879965719},\n" +
                    "  {\"id\": 23, \"latitude\": -89.237529528284952, \"longitude\": 13.710471472597236},\n" +
                    "  {\"id\": 24, \"latitude\": -89.231996425143336, \"longitude\": 13.710576053155627},\n" +
                    "  {\"id\": 25, \"latitude\": -89.22680779534906, \"longitude\": 13.707585030806259},\n" +
                    "  {\"id\": 26, \"latitude\": -89.239015069595354, \"longitude\": 13.714152680218579},\n" +
                    "  {\"id\": 27, \"latitude\": -89.243817462245403, \"longitude\": 13.710228256684607},\n" +
                    "  {\"id\": 28, \"latitude\": -89.237789037883076, \"longitude\": 13.708531222243993},\n" +
                    "  {\"id\": 29, \"latitude\": -89.240845969546001, \"longitude\": 13.706073255930214},\n" +
                    "  {\"id\": 30, \"latitude\": -89.23893621026906, \"longitude\": 13.704883980540865},\n" +
                    "  {\"id\": 31, \"latitude\": -89.241691047035829, \"longitude\": 13.710664495152757},\n" +
                    "  {\"id\": 32, \"latitude\": -89.239495298448887, \"longitude\": 13.710706459360988},\n" +
                    "  {\"id\": 33, \"latitude\": -89.238861770987711, \"longitude\": 13.710636519009769},\n" +
                    "  {\"id\": 34, \"latitude\": -89.242950902782482, \"longitude\": 13.709055861517729},\n" +
                    "  {\"id\": 35, \"latitude\": -89.243951588204084, \"longitude\": 13.709454523923759},\n" +
                    "  {\"id\": 36, \"latitude\": -89.242182899835072, \"longitude\": 13.705172071636358},\n" +
                    "  {\"id\": 37, \"latitude\": -89.243514269039665, \"longitude\": 13.703075907260443}\n" +
                    "]";

            List<PointCord> points = gson.fromJson(reader, new TypeToken<List<PointCord>>() {}.getType());


            SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);


            for ( PointCord point : Objects.requireNonNull(points)) {
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

}
