package com.example.tarea1declarativa;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;

public class MapSettings {
    public static ArcGISMap mapInit(){
        ArcGISRuntimeEnvironment.setApiKey("AAPKd876ee59fbb34ced8ed04010d4e388280v8F2OkcRm7uz0iAli7igh_2G8sC_voPnmf9zZP9Y5LTf0nyny4rv7Jsmi2qG3Ho");
        ArcGISMap map = new ArcGISMap(com.esri.arcgisruntime.mapping.BasemapStyle.ARCGIS_NAVIGATION);
        Viewpoint viewpoint = new Viewpoint(13.7013, -89.2244, 10000);
        map.setInitialViewpoint(viewpoint);
        return map;
    }
}
