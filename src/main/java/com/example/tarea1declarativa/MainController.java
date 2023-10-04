package com.example.tarea1declarativa;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.tasks.networkanalysis.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private Label welcomeText;

    @FXML
    private StackPane stackPane;

    @FXML
    private MapView mapView;

    private GraphicsOverlay graphicsOverlay;

    // points
    private final List<Stop> stops = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapView.setMap(MapSettings.mapInit());
        loadPoints();



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
        graphic3.getAttributes().put("id", 2);

        graphic.getAttributes().put("title", "Punto 0");
        graphic2.getAttributes().put("title", "Punto 1");
        graphic3.getAttributes().put("title", "Punto 2");

        // Adding graphic to the graphics overlay

        graphicsOverlay = new GraphicsOverlay();

        graphicsOverlay.getGraphics().addAll(Arrays.asList(graphic, graphic2, graphic3));



        // Adding graphics overlay to the map view
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        setPointsEventListener(graphicsOverlay);
    }


    @FXML
    protected void onHelloButtonClick() {
        //loadPoints();
        clearPointsSelection();
        welcomeText.setText("Welcome to JavaFX Application!");
        setOneRoute();

        PrologController prolog = new PrologController();


    }

    public void setPointsEventListener(GraphicsOverlay graphicsOverlay){
        // Display point coords on click
        mapView.setOnMouseClicked(e -> {

            Point mapPoint = mapView.screenToLocation( new javafx.geometry.Point2D(e.getX(), e.getY()));


            double x = e.getX();
            double y = e.getY();

            Point2D point2D = new Point2D(x, y);
            ListenableFuture<IdentifyGraphicsOverlayResult> identifyResultFuture = mapView.identifyGraphicsOverlayAsync(graphicsOverlay, point2D, 10, false);


            identifyResultFuture.addDoneListener(
                    () -> {
                        try {
                            IdentifyGraphicsOverlayResult identifyGraphicsOverlayResult = identifyResultFuture.get();
                            if (!identifyGraphicsOverlayResult.getGraphics().isEmpty()) {

                                clearPointsSelection();

                                Graphic identifiedGraphic = identifyGraphicsOverlayResult.getGraphics().get(0);
                                identifiedGraphic.setSelected(true);

                                System.out.println(identifiedGraphic.getAttributes());
                                System.out.println("Identified graphic: " + identifiedGraphic.getAttributes().get("id"));

                                //Display popup

                                mapView.getCallout().setTitle(identifiedGraphic.getAttributes().get("id").toString());
                                mapView.getCallout().setDetail(identifiedGraphic.getAttributes().get("title").toString() + "\n" + "Detalles");

                                mapView.getCallout().showCalloutAt(identifiedGraphic, mapView.screenToLocation(point2D));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
            );
        });
    }

    public void clearPointsSelection(){
        mapView.getGraphicsOverlays().forEach( graphicsOverlay -> {
            graphicsOverlay.getGraphics().forEach( graphic -> {
                graphic.setSelected(false);
            });
        });
    }




    public void setOneRoute(){
       RouteTask routeTask = new RouteTask("https://route-api.arcgis.com/arcgis/rest/services/World/Route/NAServer/Route_World");

//       RouteParameters routeParameters = new RouteParameters();
       // creating two stops examples

            Stop stop1 = new Stop(new Point(-89.2244, 13.7013, SpatialReferences.getWgs84()));
            Stop stop2 = new Stop(new Point(-89.2254, 13.7013, SpatialReferences.getWgs84()));
            Stop stop3 = new Stop(new Point(-89.2264, 13.7013, SpatialReferences.getWgs84()));

            stops.add(stop1);
            stops.add(stop2);
            stops.add(stop3);

//            routeParameters.setStops(stops);
//            routeParameters.setReturnDirections(true);
//            routeParameters.setReturnStops(true);
//            routeParameters.setReturnRoutes(true);

        ListenableFuture<RouteParameters> routeParametersFuture = routeTask.createDefaultParametersAsync();


        routeParametersFuture.addDoneListener(() -> {
            try {
                RouteParameters routeParameters = routeParametersFuture.get();
                routeParameters.setStops(stops);

                routeParameters.setReturnDirections(true);
                routeParameters.setDirectionsLanguage("es");

                ListenableFuture<RouteResult> routeResultFuture = routeTask.solveRouteAsync(routeParameters);

                routeResultFuture.addDoneListener(() -> {
                    try {
                        RouteResult routeResult = routeResultFuture.get();
                        Route route = routeResult.getRoutes().get(0);


                        SimpleLineSymbol routeSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF800080, 5);
                        Graphic routeGraphic = new Graphic(route.getRouteGeometry(), routeSymbol);
                        //routeGraphic.setGeometry(route.getRouteGeometry());

                        graphicsOverlay.getGraphics().add(routeGraphic);


                        route.getDirectionManeuvers().forEach(step -> System.out.println(step.getDirectionText()));
                    } catch (Exception e) { e.printStackTrace(); }
                });
            } catch (Exception e) { e.printStackTrace(); }
        });


    }


}