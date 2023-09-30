package com.example.tarea1declarativa;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import javafx.geometry.Point2D;

public class MapComponent {
    private final MapView mapView;

    String yourApiKey;

    public MapComponent() {
        this.yourApiKey = "AAPKd876ee59fbb34ced8ed04010d4e388280v8F2OkcRm7uz0iAli7igh_2G8sC_voPnmf9zZP9Y5LTf0nyny4rv7Jsmi2qG3Ho";
        this.mapView = new MapView();
        setMap();
    }


    public void setMap() {
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_NAVIGATION);
        Viewpoint viewpoint = new Viewpoint(13.7013, -89.2244, 10000);
        map.setInitialViewpoint(viewpoint);
        mapView.setMap(map);
    }

    public MapView getMapView() {
        return mapView;
    }

    public void loadPoints(){
        Point point = new Point(-89.2244, 13.7013, SpatialReferences.getWgs84());
        Point point2 = new Point(-89.2254, 13.7013, SpatialReferences.getWgs84());
        Point point3 = new Point(-89.2264, 13.7013, SpatialReferences.getWgs84());

        // Creating a graphic with point and symbol
        SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);

        Graphic graphic = new Graphic(point, symbol);
        Graphic graphic2 = new Graphic(point2, symbol);
        Graphic graphic3 = new Graphic(point3, symbol);

        // Add a point with an id attribute
        graphic.getAttributes().put("id", 0);
        graphic2.getAttributes().put("id", 1);

        // Adding graphic to the graphics overlay
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        graphicsOverlay.getGraphics().add(graphic2);
        graphicsOverlay.getGraphics().add(graphic);


        graphicsOverlay.getGraphics().forEach( selectedPoint -> {
            if(selectedPoint.isSelected())
            {
                selectedPoint.getAttributes().forEach((key, value) -> {
                    System.out.println(key + " : " + value);
                });
                System.out.println("Selected point: " + selectedPoint.getGeometry().toString());
            }
        });


        // Adding graphics overlay to the map view
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        setPointsEventListener(graphicsOverlay);

    }


    public void setPointsEventListener(GraphicsOverlay graphicsOverlay){
        // Display point coords on click
        mapView.setOnMouseClicked(e -> {

            Point mapPoint = mapView.screenToLocation( new javafx.geometry.Point2D(e.getX(), e.getY()));
            System.out.println("X: " + mapPoint.getX() + " Y: " + mapPoint.getY());

            System.out.println(e);

            //.out.println(e.getX());
            double x = e.getX();
            double y = e.getY();

            Point2D point2D = new Point2D(x, y);
            ListenableFuture<IdentifyGraphicsOverlayResult> identifyResultFuture = mapView.identifyGraphicsOverlayAsync(graphicsOverlay, point2D, 10, false);


            identifyResultFuture.addDoneListener(
                    () -> {
                        try {
                            IdentifyGraphicsOverlayResult identifyGraphicsOverlayResult = identifyResultFuture.get();
                            if (!identifyGraphicsOverlayResult.getGraphics().isEmpty()) {

                                mapView.getGraphicsOverlays().forEach( graphicsOverlay1 -> {
                                    graphicsOverlay1.getGraphics().forEach( graphic1 -> {
                                        graphic1.setSelected(false);
                                    });
                                });

                                Graphic identifiedGraphic = identifyGraphicsOverlayResult.getGraphics().get(0);
                                identifiedGraphic.setSelected(true);
                                System.out.println("Identified graphic: " + identifiedGraphic.getAttributes().get("id"));

                                //Display popup
                                mapView.getCallout().showCalloutAt(identifiedGraphic, mapPoint);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            );
        });
    }



}
