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
import javafx.scene.control.Button;
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

    @FXML
    private Button helloButton;

    // Stops -> point
    private List<Stop> stops = new ArrayList<>();
    // from event handler
    private final List<Integer> selectedPointsId = new ArrayList<>();

    // points -> prologController
    private final List<Point> points = new ArrayList<>();

    PrologController prolog = new PrologController();

    private Graphic routeGraphicAux;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        mapView.setMap(MapSettings.mapInit());
        loadPoints();
    }

    public void loadPoints(){
//        Point point = new Point(-89.224267304412422, 13.703150717800634, SpatialReferences.getWgs84());
//        Point point2 = new Point(-89.227109209527967, 13.703067050713086, SpatialReferences.getWgs84());
//        Point point3 = new Point(-89.228896165017289, 13.702962466811764, SpatialReferences.getWgs84());

        // Creating a graphic with point and symbol
//        SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);
//
//        Graphic graphic = new Graphic(point, symbol);
//        Graphic graphic2 = new Graphic(point2, symbol);
//        Graphic graphic3 = new Graphic(point3, symbol);
//
//        // Add a point with an id attribute
//        graphic.getAttributes().put("id", 1);
//        graphic2.getAttributes().put("id", 2);
//        graphic3.getAttributes().put("id", 27);
//
//        graphic.getAttributes().put("title", "Punto 0");
//        graphic2.getAttributes().put("title", "Punto 1");
//        graphic3.getAttributes().put("title", "Punto 2");

        // Adding graphic to the graphics overlay

        IntersectionRaster.getPointsRaster();

        graphicsOverlay = new GraphicsOverlay();

        graphicsOverlay.getGraphics().addAll(IntersectionRaster.getPointsRaster());



        // Adding graphics overlay to the map view
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        setPointsEventListener(graphicsOverlay);
    }


    @FXML
    protected void onHelloButtonClick() {

        //setOneRoute();
        welcomeText.setText("Calculando ruta desde " +  selectedPointsId.get(0).toString() + " a " + selectedPointsId.get(1).toString());
        handleRoute(selectedPointsId.get(0),selectedPointsId.get(1));
        helloButton.setDisable(true);
        // clear
        selectedPointsId.clear();

    }

    @FXML
    protected void onClearButtonClick(){
        resetRoute();
        welcomeText.setText("Selecciona dos puntos para calcular la ruta");
    }
    private void handleRoute(int from, int to){
        prolog.populateStopsId(from ,to);
        prolog.populateIntersections();

        for ( Map.Entry<Integer,Intersection> entry : prolog.getIntersections().entrySet()){
            stops.add( new Stop( interceptionToPoint(entry.getValue())));
        }
        displayRoute();
        //System.out.println(stops);
    }



    private Point interceptionToPoint(Intersection i){
        return new Point( i.getLat(), i.getLon(), SpatialReferences.getWgs84()  );
    }
    private List<Stop> getStops(List<Point> points){
        List<Stop> _stops = new ArrayList<>();
        for (Point point : points) {
            _stops.add(new Stop(point));
        }
        return _stops;
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

                                Graphic identifiedGraphic = identifyGraphicsOverlayResult.getGraphics().get(0);


                                // Add selected point to selectedPointList



                                if( identifiedGraphic.getAttributes().get("id") == "Ruta" ||  selectedPointsId.contains( (int) identifiedGraphic.getAttributes().get("id")))
                                    return;


                                if(selectedPointsId.size() < 2 ){
                                    identifiedGraphic.setSelected(true);
                                    selectedPointsId.add((int) identifiedGraphic.getAttributes().get("id"));
                                }
                                else{
                                    clearPointsSelection();
                                    selectedPointsId.clear();
                                    identifiedGraphic.setSelected(true);
                                    selectedPointsId.add((int) identifiedGraphic.getAttributes().get("id"));
                                }

                                System.out.println(identifiedGraphic.getAttributes());
                                System.out.println("Identified graphic: " + identifiedGraphic.getAttributes().get("id"));

                                //Display popup

                                mapView.getCallout().setTitle(identifiedGraphic.getAttributes().get("id").toString());
                                mapView.getCallout().setDetail(identifiedGraphic.getAttributes().get("title").toString() + "\n" + "Detalles");

                                mapView.getCallout().showCalloutAt(identifiedGraphic, mapView.screenToLocation(point2D));

                                if (selectedPointsId.size() == 2){
                                    helloButton.setDisable(false);
                                    //clearButton.setDisable(false);
                                }
                                else{
                                    helloButton.setDisable(true);
                                    //clearButton.setDisable(true);
                                }
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
        helloButton.setDisable(true);
    }

    public void displayRoute(){
       RouteTask routeTask = new RouteTask("https://route-api.arcgis.com/arcgis/rest/services/World/Route/NAServer/Route_World");

//       RouteParameters routeParameters = new RouteParameters();
       // creating two stops examples

//            Stop stop1 = new Stop(new Point(-89.2244, 13.7013, SpatialReferences.getWgs84()));
//            Stop stop2 = new Stop(new Point(-89.2254, 13.7013, SpatialReferences.getWgs84()));
//            Stop stop3 = new Stop(new Point(-89.2264, 13.7013, SpatialReferences.getWgs84()));
//
//            stops.add(stop1);
//            stops.add(stop2);
//            stops.add(stop3);

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
                        routeGraphic.getAttributes().put("id", "Ruta");
                        routeGraphicAux = routeGraphic;
                        graphicsOverlay.getGraphics().add(routeGraphic);


                        route.getDirectionManeuvers().forEach(step -> System.out.println(step.getDirectionText()));
                    } catch (Exception e) { e.printStackTrace(); }
                });
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public void resetRoute(){
        clearPointsSelection();
        selectedPointsId.clear();
        stops.clear();
        // clear route
        graphicsOverlay.getGraphics().remove( routeGraphicAux );
        //clearButton.setDisable(true);

    }
}