package com.example.tarea1declarativa;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;

import java.util.List;

public class LinesOnMap {

    public Graphic drawLine(List<Point> points){

        Polyline polyline = new Polyline ((PointCollection) points);

        return new Graphic(polyline);

    }


}
