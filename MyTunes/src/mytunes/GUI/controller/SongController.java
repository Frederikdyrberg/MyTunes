/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import mytunes.GUI.model.MyTunesModel;
import mytunes.be.Song;
import mytunes.bll.MyTunesManager;

/**
 * FXML Controller class
 *
 * @author Patrick, Frederik, Nicolai, Mads
 */
public class SongController implements Initializable {

    @FXML
    private Button songCancel;
    @FXML
    private Button songChoose;
    
    private MyTunesModel myTunesModel;
    public Song myTunes;
    private MyTunesManager myTunesManager;
    @FXML
    private Label titleLbl;
    @FXML
    private Label Filelbl;
    @FXML
    private Label lblTime;
    private Label lblArtist;
    @FXML
    private TextField artistField;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    


    public void setModel(MyTunesModel model)
    {
        this.myTunesModel = model;
    }
    /**
     * Closes down the window when clicked.
     * @param event 
     */
    @FXML
    private void songCancel(ActionEvent event) 
    {
    Stage stage = (Stage) songCancel.getScene().getWindow();
    stage.close();
    }
    /**
     * Saves the song to our song tableview and shuts down the window.
     * @param event 
     */
    
    @FXML
    private void saveSong(ActionEvent event) 
    {
        myTunesModel.addSong(titleLbl.getText(), artistField.getText(), Filelbl.getText());
        Stage stage = (Stage) songCancel.getScene().getWindow();
        stage.close();
    }
    /**
     * Opens a window which allows the user to choose an audio file on their computer. 
     * If the user opens a file, the filelbl and titleLbl will autofill with the filepath & file name.
     * @param event 
     */
    @FXML
    private void chooseSongFile(ActionEvent event) 
    {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter extensionFilter = new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Window win = songChoose.getScene().getWindow();
        File file = fileChooser.showOpenDialog(win);
        if(file != null)
        {
            Filelbl.setText(file.getPath());
            titleLbl.setText(file.getName());
        }
    
    
    }
}
