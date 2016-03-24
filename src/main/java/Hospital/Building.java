package Hospital;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by matthewlemay on 3/23/16.
 */
public class Building {

    private ArrayList<Level> levels; // List of level instances
    private java.util.HashMap<String, UUID> doctors; // Doctor Name and UUID of location
    private java.util.HashMap<String, UUID> departments; // Department Name and UUID of location

    /**
     * Constructor for the Building class. For the arbitrary number of levels in the locations.json file it adds each
     * level to the ArrayList of levels. Then it adds each doctor from the doctors.json file to the doctors hash map.
     * Finally it adds all of the departments to the to the departments hash map. If either the doctors.json file or
     * departments.json file have not been created, they are created with no data.
     *
     * @param jsonFilePath Directory that contains the locations.json, doctors.json, and departments.json files and the
     *                     levels directory with the {number}.json files that correspond to the levels in the locations.json.
     * @throws IOException Thrown if the directory does not exist or one of the essential json files does not exist.
     */
    public Building(String jsonFilePath) throws IOException {
    }

    /**
     * Constructor for the Building class. This constructor is to be used if the json files have not been created yet
     * or an image has been modified. For each picture in the path specified. All of the pictures will be translated to
     * a new or existing level. For each picture {number}.png that does not have a level of the same name specified in
     * the locations.json file, adds the the {number} as a new level in the locations.json and a corresponding json file
     * will be created in the levels directory. If the level exists in the locations.json file it does not modify the
     * locations.json file in any way. It overwrites the existing file in the level directory if there is one and if not
     * it creates a new {number}.json file. Then it adds each doctor from the doctors.json file to the doctors hash map.
     * Finally it adds all of the departments to the to the departments hash map. If either the doctors.json file or
     * departments.json file have not been created, they are created with no data. This constructor does not modify the
     * doctors.json or department.json file if they exist. If either the doctors.json file or departments.json file have
     * not been created, they are created with no data.
     *
     * @param jsonFilePath Directory that contains the locations.json, doctors.json, and departments.json files and the
     *                     levels directory with the {level}.json files that correspond to the levels in the locations.json.
     *                     Or the directory where you want to create the json files.
     * @param imageFilePath Path to the black and white images that you want to load as levels. The places you can move
     *                      to should be white and the places that you cannot move to should be black.
     * @throws IOException Throw if either directory does not exist.
     */
    public Building(String jsonFilePath, String imageFilePath) throws IOException {
    }
}
