package ticktacktoeserver;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ServerControlBase extends AnchorPane {

    protected final ImageView imageView;
//    protected final ImageView btnBackServerControl;
    protected final Button btnStartServer;
    protected final Button brnStopServer;
    protected final Text txtServerStatus;
    ServerHomePageController shp;

    public ServerControlBase() {

        imageView = new ImageView();
//        btnBackServerControl = new ImageView();
        btnStartServer = new Button();
        brnStopServer = new Button();
        txtServerStatus = new Text();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(750.0);
        imageView.setFitWidth(650.0);
        imageView.setLayoutX(-18.0);
        imageView.setLayoutY(-8.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/Images/BackGround.png").toExternalForm()));

//        btnBackServerControl.setFitHeight(83.0);
//        btnBackServerControl.setFitWidth(78.0);
//        btnBackServerControl.setPickOnBounds(true);
//        btnBackServerControl.setPreserveRatio(true);
//        btnBackServerControl.setImage(new Image(getClass().getResource("/Images/backArrow.png").toExternalForm()));

        btnStartServer.setLayoutX(182.0);
        btnStartServer.setLayoutY(217.0);
        btnStartServer.setMnemonicParsing(false);
        btnStartServer.setPrefHeight(99.0);
        btnStartServer.setPrefWidth(236.0);
        btnStartServer.setStyle("-fx-background-color: #3f51b5; -fx-background-radius: 50; -fx-border-color: white; -fx-border-radius: 50; -fx-border-width: 2;");
        btnStartServer.setText("Start Server");
        btnStartServer.setTextFill(javafx.scene.paint.Color.WHITE);
        btnStartServer.setFont(new Font("System Bold", 18.0));

        brnStopServer.setLayoutX(182.0);
        brnStopServer.setLayoutY(400.0);
        brnStopServer.setMnemonicParsing(false);
        brnStopServer.setPrefHeight(99.0);
        brnStopServer.setPrefWidth(236.0);
        brnStopServer.setStyle("-fx-background-color: #3f51b5; -fx-background-radius: 50; -fx-border-color: white; -fx-border-radius: 50; -fx-border-width: 2;");
        brnStopServer.setText("Stop Server");
        brnStopServer.setTextFill(javafx.scene.paint.Color.WHITE);
        brnStopServer.setFont(new Font("System Bold", 18.0));

        txtServerStatus.setLayoutX(187.0);
        txtServerStatus.setLayoutY(622.0);
        txtServerStatus.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        txtServerStatus.setStrokeWidth(0.0);
        txtServerStatus.setText("Server Closed");
        txtServerStatus.setFont(new Font("System Bold Italic", 36.0));

        getChildren().add(imageView);
//        getChildren().add(btnBackServerControl);
        getChildren().add(btnStartServer);
        getChildren().add(brnStopServer);
        getChildren().add(txtServerStatus);
          shp=new ServerHomePageController(btnStartServer,brnStopServer,txtServerStatus);
    }
}
