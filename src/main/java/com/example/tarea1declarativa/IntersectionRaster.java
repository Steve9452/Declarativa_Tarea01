package com.example.tarea1declarativa;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

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
            //File file = new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("coords.json")).getFile());
            //FileReader reader = new FileReader(file);


            Gson gson = new Gson();
            String json = "[\n" +
                    "{  \"id\": 1, \"latitude\": -89.224267304412422, \"longitude\" : 13.703150717800634   },\n" +
                    "{  \"id\": 2, \"latitude\": -89.227109209527967, \"longitude\" : 13.703067050713086   },\n" +
                    "{  \"id\": 3, \"latitude\": -89.228896165017289, \"longitude\" : 13.702962466811764   },\n" +
                    "{  \"id\": 4, \"latitude\": -89.230747709259219, \"longitude\" : 13.702774215672081   },\n" +
                    "{  \"id\": 5, \"latitude\": -89.232706901422205, \"longitude\" : 13.702753298869473   },\n" +
                    "{  \"id\": 6, \"latitude\": -89.232728431006436, \"longitude\" : 13.703652719700306   },\n" +
                    "{  \"id\": 7, \"latitude\": -89.230618531753976, \"longitude\" : 13.703736386579315   },\n" +
                    "{  \"id\": 8, \"latitude\": -89.22885310584887,  \"longitude\" :13.703945553646497 },\n" +
                    "{  \"id\": 9, \"latitude\": -89.230446295080313, \"longitude\" : 13.705828048871826   },\n" +
                    "{  \"id\": 10, \"latitude\": -89.232491605580137, \"longitude\" : 13.706037214076886   },\n" +
                    "{  \"id\": 11, \"latitude\": -89.232427016827501, \"longitude\" : 13.707940608884595   },\n" +
                    "{  \"id\": 12, \"latitude\": -89.235462688200926, \"longitude\" : 13.708170688530808   },\n" +
                    "{  \"id\": 13, \"latitude\": -89.235634924874589, \"longitude\" : 13.706371878017634   },\n" +
                    "{  \"id\": 14, \"latitude\": -89.236000927806131, \"longitude\" : 13.702355879266182   },\n" +
                    "{  \"id\": 15, \"latitude\": -89.235828691132468, \"longitude\" : 13.703924636948157   },\n" +
                    "{  \"id\": 16, \"latitude\": -89.240565199658363, \"longitude\" : 13.708798177329566   },\n" +
                    "{  \"id\": 17, \"latitude\": -89.2408881434215,  \"longitude\" : 13.705033219394245        },\n" +
                    "{  \"id\": 18, \"latitude\": -89.241017320926744, \"longitude\" : 13.702774215672081   },\n" +
                    "{  \"id\": 19, \"latitude\": -89.240328374232078, \"longitude\" : 13.710785214132702   },\n" +
                    "{  \"id\": 20, \"latitude\": -89.241943093047723, \"longitude\" : 13.708986423642306   },\n" +
                    "{  \"id\": 21, \"latitude\": -89.242222977642442, \"longitude\" : 13.706622875660329   },\n" +
                    "{  \"id\": 22, \"latitude\": -89.241900033879304, \"longitude\" : 13.70287879965719 },\n" +
                    "{  \"id\": 23, \"latitude\": -89.237529528284952, \"longitude\" : 13.710471472597236   },\n" +
                    "{  \"id\": 24, \"latitude\": -89.231996425143336, \"longitude\" : 13.710576053155627   },\n" +
                    "{  \"id\": 25, \"latitude\": -89.22680779534906,  \"longitude\" :13.707585030806259 },\n" +
                    "{  \"id\": 26, \"latitude\": -89.239015069595354, \"longitude\" : 13.714152680218579   },\n" +
                    "{  \"id\": 27, \"latitude\": -89.243817462245403, \"longitude\" : 13.710228256684607   },\n" +
                    "{  \"id\": 28, \"latitude\": -89.237789037883076, \"longitude\" : 13.708531222243993   },\n" +
                    "{  \"id\": 29, \"latitude\": -89.240845969546001, \"longitude\" : 13.706073255930214   },\n" +
                    "{  \"id\": 30, \"latitude\": -89.23893621026906,  \"longitude\" :13.704883980540865 },\n" +
                    "{  \"id\": 31, \"latitude\": -89.241691047035829, \"longitude\" : 13.710664495152757   },\n" +
                    "{  \"id\": 32, \"latitude\": -89.239495298448887, \"longitude\" : 13.710706459360988   },\n" +
                    "{  \"id\": 33, \"latitude\": -89.238861770987711, \"longitude\" : 13.710636519009769   },\n" +
                    "{  \"id\": 34, \"latitude\": -89.242950902782482, \"longitude\" : 13.709055861517729   },\n" +
                    "{  \"id\": 35, \"latitude\": -89.243951588204084, \"longitude\" : 13.709454523923759   },\n" +
                    "{  \"id\": 36, \"latitude\": -89.242182899835072, \"longitude\" : 13.705172071636358   },\n" +
                    "{  \"id\": 37, \"latitude\": -89.243514269039665, \"longitude\" : 13.703075907260443   },\n" +
                    "{  \"id\": 38, \"latitude\": -89.224365761566233, \"longitude\" : 13.70184556236074 },\n" +
                    "{  \"id\": 39, \"latitude\": -89.227272719505649, \"longitude\" : 13.701639414076908   },\n" +
                    "{  \"id\": 40, \"latitude\": -89.229055087877256, \"longitude\" : 13.701824947540496   },\n" +
                    "{  \"id\": 41, \"latitude\": -89.230816237577784, \"longitude\" : 13.70192802162364 },\n" +
                    "{  \"id\": 42, \"latitude\": -89.234699254387365, \"longitude\" : 13.702175399238731   },\n" +
                    "{  \"id\": 43, \"latitude\": -89.227049139141513, \"longitude\" : 13.704051355693124   },\n" +
                    "{  \"id\": 44, \"latitude\": -89.234567357105135, \"longitude\" : 13.70302610246727 },\n" +
                    "{  \"id\": 45, \"latitude\": -89.235902508641516, \"longitude\" : 13.703140894350506   },\n" +
                    "{  \"id\": 46, \"latitude\": -89.234520095103861, \"longitude\" : 13.70377798828317 },\n" +
                    "{  \"id\": 47, \"latitude\": -89.234425571101283, \"longitude\" : 13.704558569485499   },\n" +
                    "{  \"id\": 48, \"latitude\": -89.232656165295964, \"longitude\" : 13.704323803694539   },\n" +
                    "{  \"id\": 49, \"latitude\": -89.235712898156507, \"longitude\" : 13.70547267954106 },\n" +
                    "{  \"id\": 50, \"latitude\": -89.235769187855553, \"longitude\" : 13.704692206643857   },\n" +
                    "{  \"id\": 51, \"latitude\": -89.234312837304245, \"longitude\" : 13.705324020144435   },\n" +
                    "{  \"id\": 52, \"latitude\": -89.234261474227793, \"longitude\" : 13.70625549655934 },\n" +
                    "{  \"id\": 53, \"latitude\": -89.235520050554186, \"longitude\" : 13.707278475574922   },\n" +
                    "{  \"id\": 54, \"latitude\": -89.23419211351694,  \"longitude\" :13.707141229141557 },\n" +
                    "{  \"id\": 55, \"latitude\": -89.232421530800607, \"longitude\" : 13.706994832857584   },\n" +
                    "{  \"id\": 56, \"latitude\": -89.232610971932658, \"longitude\" : 13.705183156658334   },\n" +
                    "{  \"id\": 57, \"latitude\": -89.230605859783253, \"longitude\" : 13.704396989331084   },\n" +
                    "{  \"id\": 58, \"latitude\": -89.230527087520244, \"longitude\" : 13.70501618328673 },\n" +
                    "{  \"id\": 59, \"latitude\": -89.236537614177763, \"longitude\" : 13.707352277857714   },\n" +
                    "{  \"id\": 60, \"latitude\": -89.237846441315,  \"longitude\" :13.707483172680966 },\n" +
                    "{  \"id\": 61, \"latitude\": -89.23647987180405,  \"longitude\" :13.708333987254196 },\n" +
                    "{  \"id\": 62, \"latitude\": -89.234057538662441, \"longitude\" : 13.708073532618103   },\n" +
                    "{  \"id\": 63, \"latitude\": -89.235292367418026, \"longitude\" : 13.709389668560666   },\n" +
                    "{  \"id\": 64, \"latitude\": -89.23634126864151,  \"longitude\" :13.709499408971189 },\n" +
                    "{  \"id\": 65, \"latitude\": -89.237616087051592, \"longitude\" : 13.709671858084125   },\n" +
                    "{  \"id\": 66, \"latitude\": -89.23532464130183,  \"longitude\" :13.710032433092914 },\n" +
                    "{  \"id\": 67, \"latitude\": -89.236276720873917, \"longitude\" : 13.710361653269644   }\n" +
                    "]\n";

            List<PointCord> points = gson.fromJson(json, new TypeToken<List<PointCord>>() {}.getType());
            //System.out.println(points);
            SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);


            for ( PointCord point : Objects.requireNonNull(points)) {
                Point p = new Point(point.latitude, point.longitude, SpatialReferences.getWgs84());
                Graphic graphic = new Graphic(p, symbol);
                graphic.getAttributes().put("id", point.id);
                graphic.getAttributes().put("title", "Punto " + point.id);
                graphics.add(graphic);

            }

            return graphics ;
        }
        catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }

        return graphics;
    }

}
