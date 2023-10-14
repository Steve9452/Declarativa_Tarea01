package com.example.tarea1declarativa;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;

import java.util.List;

public class LinesOnMap {

    public Graphic drawLine(List<IntersectionRaster.PointCord> points){

        List<Point> pointsList = pointCordToPoint(points);


        PointCollection polylinePoints = new PointCollection(pointsList);
        Polyline polyline = new Polyline(polylinePoints);

        SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF00FF00, 5);
        return new Graphic(polyline, polylineSymbol);
    }

    private List<Point> pointCordToPoint(List<IntersectionRaster.PointCord> points){
        return points.stream().map(pointCord -> new Point(pointCord.latitude, pointCord.longitude, SpatialReferences.getWgs84())).toList();
    }


}
