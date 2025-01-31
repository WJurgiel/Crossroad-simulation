module org.wjurgiel.crossroad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires org.json;

    opens org.wjurgiel.crossroad to javafx.fxml;
    exports org.wjurgiel.crossroad;
    exports org.wjurgiel.crossroad.Files;
    opens org.wjurgiel.crossroad.Files to javafx.fxml;
}