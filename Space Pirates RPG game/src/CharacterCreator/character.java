/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package CharacterCreator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 *
 * @author nica
 * @author Ethan
 * @author Ben
 */
public class character extends Application 
{
    
    @Override
    public void start(Stage primaryStage) 
    {
        // set up root
        StackPane root = new StackPane();
        root.getStyleClass().add("stackpane");
        
        // create character button
        Button btn = new Button();
        btn.setText("Create Character");
        btn.getStyleClass().add("create-btn");
        
        // create button event handler
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {                
                //Enter the Characters Name to Submit it
                Label charLabel = new Label("Enter Character Name");
                charLabel.getStyleClass().add("text");

                TextField avatarName = new TextField();
                avatarName.getStyleClass().add("avatar-field");

                // Label for error message if user didn't give proper input
                Label errorMsg = new Label();

                // Add a character Race input field?

                //Creates Dropdown for Character Class
                Label charClass = new Label ("Character Class");
                charClass.getStyleClass().add("text");
                ComboBox<String> classBox = new ComboBox<>();
                classBox.getItems().addAll("Titan", "Elite Trooper", "Jet Trooper", "Engineer", "Marksman");

                //Creates Dropdown for Weapon
                Label primaryWeapon = new Label ("Weapon");
                primaryWeapon.getStyleClass().add("text");
                ComboBox<String> weaponBox = new ComboBox<>();
                weaponBox.getItems().addAll("Laser Rifle", "Rocket Launcher", "ElectroStaff", "Beam Blade", "Sniper Rifle");

                //Creates Dropdown for Secondary Weapon
                Label secondaryWeapon = new Label ("Secondary Weapon");
                secondaryWeapon.getStyleClass().add("text");
                ComboBox<String> weapon2Box = new ComboBox<>();
                weapon2Box.getItems().addAll("Energy Shield", "Precision Pistol", "fusion gun", "grenades");

                //Creates Dropdown for Character Specialty
                Label specialty = new Label ("Specialty");
                specialty.getStyleClass().add("text");
                ComboBox<String> specialtyBox = new ComboBox<>();
                specialtyBox.getItems().addAll("Combat", "Detective", "Survivalist", "Explorer", "Communicator");

                //Save & clear Buttons initilaized & placed inside an HBox for the form
                Button saveBtn = new Button();
                saveBtn.setText("Save");
                saveBtn.getStyleClass().add("save-btn");

                Button clearBtn = new Button();
                clearBtn.setText("Clear Form");
                clearBtn.getStyleClass().add("clear-btn");

                HBox formBtns = new HBox(20, saveBtn, clearBtn);
                formBtns.setAlignment(Pos.CENTER);

                //Hides Start Button
                btn.setVisible(false);

                // Pack form elements into a VBox and add it to root
                VBox form = new VBox(10, charLabel, avatarName,
                                        errorMsg, charClass, classBox, 
                                        primaryWeapon, weaponBox, 
                                        secondaryWeapon, weapon2Box, 
                                        specialty, specialtyBox, formBtns);
                form.setAlignment(Pos.CENTER);
                root.getChildren().add(form);

                // clear button event handler
                clearBtn.setOnAction(new EventHandler<ActionEvent>() 
                {
                    @Override
                    public void handle(ActionEvent event) 
                    {
                    // Clear all input fields
                    avatarName.setText("");
                    errorMsg.setText("");
                    classBox.getSelectionModel().clearSelection();
                    weaponBox.getSelectionModel().clearSelection();
                    weapon2Box.getSelectionModel().clearSelection();
                    specialtyBox.getSelectionModel().clearSelection();
                    } // end clearBtn handle method

                }); // end clearBtn action event

                // save button event handler
                saveBtn.setOnAction(new EventHandler<ActionEvent>() 
                {
                    
                    @Override
                    public void handle(ActionEvent event) 
                    {
                        if (avatarName.getText() == null || avatarName.getText().trim().isEmpty()) 
                        {
                            System.out.println("Please fill in the character's name and try again");
                            errorMsg.setText("Please fill in the character's name and try again");
                            errorMsg.getStyleClass().add("error-msg");

                        }
                        else
                        {
                            //Stores User Input from combo boxes
                            String charName = avatarName.getText();
                            String charClass = classBox.getSelectionModel().getSelectedItem();
                            String charWep = weaponBox.getSelectionModel().getSelectedItem();
                            String charWep2 = weapon2Box.getSelectionModel().getSelectedItem();
                            String charSpec = specialtyBox.getSelectionModel().getSelectedItem();

                            // Initialize fileData outside of try-catch block for later use afterwards
                            String fileData = "Space Piraces RPG Character Sheet"
                                        + "\nName: " + charName
                                        + "\nClass: " + charClass
                                        + "\nWeapon: " + charWep
                                        + "\nSidearm: " + charWep2
                                        + "\nSpecialty: " + charSpec;
                            
                            // print stored user input to console
                            System.out.println(charClass + " " 
                                                + charWep + " "
                                                + charWep2 + " "
                                                + charSpec);

                            // try catch block for handling files
                            try 
                            {
                                //if & while statements create Character Sheet
                                File charFile = new File(charName + ".txt");
                                File newFile;

                                if (charFile.exists()) 
                                {
                                    int i = 2;

                                    while (charFile.exists()) 
                                    {
                                        newFile = new File(charName + "_" + i + ".txt");
                                        charFile = newFile;
                                        System.out.println("Current file name test: " + newFile.getName());
                                        i++;
                                    }
                                }

                                // Create new Character File
                                charFile.createNewFile();
                                System.out.println("File name: " + charFile.getName());

                                //Saves Data to Character Sheet
                                FileWriter charWriter = new FileWriter(charFile);
                                
                                charWriter.write(fileData);
                                charWriter.close();
                                System.out.println("Successfully wrote to the file.");
                            }
                            // Throw an error if it exists
                            catch (IOException e) 
                            {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            } // end try-catch
                            
                            // display file contents in a label widget
                            Label fileContent = new Label(fileData);
                            fileContent.getStyleClass().add("text");
                            fileContent.setAlignment(Pos.CENTER);

                            // Add a "create new character" button
                            Button newCharBtn = new Button("Create New Character");
                            newCharBtn.getStyleClass().add("create-btn");

                            // place fileContent and newCharBtn in a Vbox
                            VBox result = new VBox(10, fileContent, newCharBtn);
                            result.setAlignment(Pos.CENTER);

                            // newChar button event handler
                            newCharBtn.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                @Override
                                public void handle(ActionEvent event) 
                                {
                                    // Clear all input fields
                                    avatarName.setText("");
                                    errorMsg.setText("");
                                    classBox.getSelectionModel().clearSelection();
                                    weaponBox.getSelectionModel().clearSelection();
                                    weapon2Box.getSelectionModel().clearSelection();
                                    specialtyBox.getSelectionModel().clearSelection();
                                    
                                    // hide result & display form to create a new character
                                    form.setVisible(true);
                                    result.setVisible(false);
                                } // end newCharBtn handle method

                            }); // end newCharBtn action event

                            //Hide form and display result
                            form.setVisible(false);
                            root.getChildren().add(result);

                        } // end if-else

                    } // end saveBtn handle method

                }); // end saveBtn action event

            } // end btn handle method

        }); // end btn action event

        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("styles.css");
        
        primaryStage.setTitle("Space Pirates RPG Character Creator");
        primaryStage.setScene(scene);
        // display the application
        primaryStage.show();
    
    }  // end start method

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
        
        
    }
    
}



