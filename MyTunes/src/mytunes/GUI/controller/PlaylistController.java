/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.GUI.model.PlaylistModel;
import mytunes.be.Song;

/**
 * FXML Controller class
 *
 * @author FrederikD
 */
public class PlaylistController implements Initializable {

    @FXML
    private TextField playlistName;
    @FXML
    private Button playlistCancel;
    @FXML
    private Button playlistSave;
    
    private Song currentPlaylist;
    
    private PlaylistModel playlistModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    public void setModel(PlaylistModel model)
    {
        this.playlistModel = model;
    }

    void setMytunes(Song newPlaylist) 
    {
     currentPlaylist = newPlaylist;
    }
    /**
     * Cancels adding a playlist by shutting down the "Playlist.fxml" window. 
     * @param event 
     */
    @FXML
    private void playlistCancel(ActionEvent event) 
    {

    Stage stage = (Stage) playlistCancel.getScene().getWindow();
    stage.close();
    } 
    /**
     * Saves the name of the playlist to our playlistTable, and closes the "Playlist.fxml" window.
     * @param event 
     */
    @FXML
    private void savePlaylist(ActionEvent event) {
        playlistModel.addPlaylist(playlistName.getText());
        Stage stage = (Stage) playlistCancel.getScene().getWindow();
        stage.close();
    }
}
