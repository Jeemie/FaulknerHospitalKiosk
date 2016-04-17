package Kiosk.Controllers.AdminDashboardSubControllers;

import Map.Building;
import Map.Exceptions.FloorDoesNotExistException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by matt on 4/15/16.
 */
public class AdminDashboardAddFloorController {

    // The building you want to add the floor to
    private Building currentBuilding;

    // The controller that created this instance
    private AdminSubControllerLoader loader;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDashboardAddFloorController.class);

    @FXML
    private TextField floorNumberTextField;

    @FXML
    private Button selectFloorFileButton;

    @FXML
    private TextField selectFloorFileTextField;

    @FXML
    private Button discardFloorButton;

    @FXML
    private Button createFloorButton;


    public void setListeners() {

        this.selectFloorFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select the floor's Image");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("GIF", "*.gif"),
                        new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );

                File imageFile = fileChooser.showOpenDialog(new Stage());

//                imageFile.t
//                currentBuilding.addFloor();

            }

        });

        this.discardFloorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

            }

        });


        this.createFloorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (false) {

                    LOGGER.info("c");
                    return;
                }

                int floorNum = Integer.parseInt(floorNumberTextField.getText());

                try {

                    currentBuilding.getFloor(floorNum);

                } catch (FloorDoesNotExistException e) {

                    LOGGER.info("Floor " + floorNum + " does not exist, a new floor will be created");

                    URL url;
                    URLConnection con;
                    DataInputStream dis;
                    FileOutputStream fos;
                    byte[] fileData;
                    try {

                        url = new URL(selectFloorFileTextField.getText()); //File Location goes here
                        con = url.openConnection(); // open the url connection.
                        dis = new DataInputStream(con.getInputStream());
                        fileData = new byte[con.getContentLength()];

                        for (int q = 0; q < fileData.length; q++) {
                            fileData[q] = dis.readByte();
                        }
                        dis.close(); // close the data input stream

                        File newFloorImage = new File(System.getProperty("user.dir") + "/resources/" + "Floor" +
                                floorNum + ".png");

                        newFloorImage.createNewFile();

                        LOGGER.info(newFloorImage.getAbsolutePath());

                        fos = new FileOutputStream(newFloorImage); //FILE Save Location goes here
                        fos.write(fileData);  // write out the file we want to save.
                        fos.close(); // close the output stream writer

                    } catch(Exception exception) {

                        LOGGER.info("a", exception);

                    }

                    currentBuilding.addFloor(floorNum, "Floor" + floorNum + ".png");

                    loader.removeFromScene();

                }


            }

        });


        this.discardFloorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                loader.removeFromScene();

            }

        });


    }



    public void setParentController(AdminSubControllerLoader loader) {

        this.loader = loader;

    }

    public void setCurrentBuilding(Building currentBuilding) {

        this.currentBuilding = currentBuilding;

    }
}
