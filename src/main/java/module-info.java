module com.example.tarea1declarativa {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.esri.arcgisruntime;
    requires jpl;
    requires com.google.gson;

    opens com.example.tarea1declarativa to javafx.fxml;
    exports com.example.tarea1declarativa;
}