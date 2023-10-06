package com.example.tarea1declarativa;


import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import java.util.List;
import java.util.Objects;

public class IntersectionRaster {
    private static List<Point> intersections;

    public static List<Point> getIntersections() {
        return intersections;
    }

    public static void setIntersections(List<Point> intersections) {
//                coordenada(1,-89.224267304412422, 13.703150717800634 ).
//                coordenada(2,-89.227109209527967, 13.703067050713086 ).
//                coordenada(3,-89.228896165017289, 13.702962466811764 ).
//                coordenada(4,-89.230747709259219, 13.702774215672081 ).
//                coordenada(5,-89.232706901422205, 13.702753298869473 ).
//                coordenada(6,-89.232728431006436, 13.703652719700306 ).
//                coordenada(7,-89.230618531753976, 13.703736386579315 ).
//                coordenada(8,-89.22885310584887, 13.703945553646497 ).
//                coordenada(9,-89.230446295080313, 13.705828048871826 ).
//                coordenada(10,-89.232491605580137, 13.706037214076886 ).
//                coordenada(11,-89.232427016827501, 13.707940608884595 ).
//                coordenada(12,-89.235462688200926, 13.708170688530808 ).
//                coordenada(13,-89.235634924874589, 13.706371878017634 ).
//                coordenada(14,-89.236000927806131, 13.702355879266182 ).
//                coordenada(15,-89.235828691132468, 13.703924636948157 ).
//                coordenada(16,-89.240565199658363, 13.708798177329566 ).
//                coordenada(17,-89.2408881434215, 13.705033219394245 ).
//                coordenada(18,-89.241017320926744, 13.702774215672081 ).
//                coordenada(19,-89.240328374232078, 13.710785214132702 ).
//                coordenada(20,-89.241943093047723, 13.708986423642306 ).
//                coordenada(21,-89.242222977642442, 13.706622875660329 ).
//                coordenada(22,-89.241900033879304, 13.70287879965719 ).
//                coordenada(23,-89.237529528284952, 13.710471472597236 ).
//                coordenada(24,-89.231996425143336, 13.710576053155627 ).
//                coordenada(25,-89.22680779534906, 13.707585030806259 ).
//                coordenada(26,-89.239015069595354, 13.714152680218579 ).
//                coordenada(27,-89.243817462245403, 13.710228256684607 ).
//                coordenada(28,-89.237789037883076, 13.708531222243993 ).
//                coordenada(29,-89.240845969546001, 13.706073255930214 ).
//                coordenada(30,-89.23893621026906, 13.704883980540865 ).
//                coordenada(31,-89.241691047035829, 13.710664495152757 ).
//                coordenada(32,-89.239495298448887, 13.710706459360988 ).
//                coordenada(33,-89.238861770987711, 13.710636519009769 ).
//                coordenada(34,-89.242950902782482, 13.709055861517729 ).
//                coordenada(35,-89.243951588204084, 13.709454523923759 ).
//                coordenada(36,-89.242182899835072, 13.705172071636358 ).
//                coordenada(37,-89.243514269039665, 13.703075907260443 ).

        Point point1 = new Point(-89.224267304412422, 13.703150717800634, SpatialReferences.getWgs84());
        Point point2 = new Point(-89.227109209527967, 13.703067050713086, SpatialReferences.getWgs84());
        Point point3 = new Point(-89.228896165017289, 13.702962466811764, SpatialReferences.getWgs84());
        Point point4 = new Point(-89.230747709259219, 13.702774215672081, SpatialReferences.getWgs84());
        Point point5 = new Point(-89.232706901422205, 13.702753298869473, SpatialReferences.getWgs84());
        Point point6 = new Point(-89.232728431006436, 13.703652719700306, SpatialReferences.getWgs84());
        Point point7 = new Point(-89.230618531753976, 13.703736386579315, SpatialReferences.getWgs84());
        Point point8 = new Point(-89.22885310584887, 13.703945553646497, SpatialReferences.getWgs84());
        Point point9 = new Point(-89.230446295080313, 13.705828048871826, SpatialReferences.getWgs84());
        Point point10 = new Point(-89.232491605580137, 13.706037214076886, SpatialReferences.getWgs84());
        Point point11 = new Point(-89.232427016827501, 13.707940608884595, SpatialReferences.getWgs84());
        Point point12 = new Point(-89.235462688200926, 13.708170688530808, SpatialReferences.getWgs84());
        Point point13 = new Point(-89.235634924874589, 13.706371878017634, SpatialReferences.getWgs84());
        Point point14 = new Point(-89.236000927806131, 13.702355879266182, SpatialReferences.getWgs84());
        Point point15 = new Point(-89.235828691132468, 13.703924636948157, SpatialReferences.getWgs84());
        Point point16 = new Point(-89.240565199658363, 13.708798177329566, SpatialReferences.getWgs84());
        Point point17 = new Point(-89.2408881434215, 13.705033219394245, SpatialReferences.getWgs84());
        Point point18 = new Point(-89.241017320926744, 13.702774215672081, SpatialReferences.getWgs84());
        Point point19 = new Point(-89.240328374232078, 13.710785214132702, SpatialReferences.getWgs84());
        Point point20 = new Point(-89.241943093047723, 13.708986423642306, SpatialReferences.getWgs84());
        Point point21 = new Point(-89.242222977642442, 13.706622875660329, SpatialReferences.getWgs84());
        Point point22 = new Point(-89.241900033879304, 13.70287879965719, SpatialReferences.getWgs84());
        Point point23 = new Point(-89.237529528284952, 13.710471472597236, SpatialReferences.getWgs84());
        Point point24 = new Point(-89.231996425143336, 13.710576053155627, SpatialReferences.getWgs84());
        Point point25 = new Point(-89.22680779534906, 13.707585030806259, SpatialReferences.getWgs84());
        Point point26 = new Point(-89.239015069595354, 13.714152680218579, SpatialReferences.getWgs84());
        Point point27 = new Point(-89.243817462245403, 13.710228256684607, SpatialReferences.getWgs84());
        Point point28 = new Point(-89.237789037883076, 13.708531222243993, SpatialReferences.getWgs84());
        Point point29 = new Point(-89.240845969546001, 13.706073255930214, SpatialReferences.getWgs84());
        Point point30 = new Point(-89.23893621026906, 13.704883980540865, SpatialReferences.getWgs84());
        Point point31 = new Point(-89.241691047035829, 13.710664495152757, SpatialReferences.getWgs84());
        Point point32 = new Point(-89.239495298448887, 13.710706459360988, SpatialReferences.getWgs84());
        Point point33 = new Point(-89.238861770987711, 13.710636519009769, SpatialReferences.getWgs84());
        Point point34 = new Point(-89.242950902782482, 13.709055861517729, SpatialReferences.getWgs84());
        Point point35 = new Point(-89.243951588204084, 13.709454523923759, SpatialReferences.getWgs84());
        Point point36 = new Point(-89.242182899835072, 13.705172071636358, SpatialReferences.getWgs84());
        Point point37 = new Point(-89.243514269039665, 13.703075907260443, SpatialReferences.getWgs84());


        IntersectionRaster.intersections = List.of(point1, point2, point3, point4, point5, point6, point7, point8, point9,
                point10, point11, point12, point13, point14, point15, point16, point17, point18, point19,
                point20, point21, point22, point23, point24, point25, point26, point27, point28, point29,
                point30, point31, point32, point33, point34, point35, point36, point37);






    }
}
