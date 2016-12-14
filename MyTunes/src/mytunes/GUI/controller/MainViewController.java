/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mytunes.GUI.model.MyTunesModel;
import mytunes.GUI.model.PlaylistModel;
import mytunes.be.PlaylistBE;
import mytunes.be.Song;

/**
 *
 * @author Patrick, Frederik, Nicolai, Mads
 */
public class MainViewController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button newPlaylist;
    @FXML
    private Button editPlaylist;
    @FXML
    private Button deletePlaylist;
    @FXML
    private Button moveSongUp;
    @FXML
    private Button moveSongDown;
    @FXML
    private Label label1;
    @FXML
    private Button deleteSongs;
    @FXML
    private Button editSongs;
    @FXML
    private Button newSong;
    @FXML
    private Button moveSong;
    @FXML
    private Slider slider;
    @FXML
    private Button deletePlaylistSong;
    
    private Song currentSong;
    @FXML
    private Font x1;
    
    MediaPlayer mediaPlayer = null;
    @FXML
    private TableView<PlaylistBE> tablePlaylist;
    @FXML
    private TableView<Song> tableSong;
    @FXML
    private Button closeApp;
    @FXML
    private TableColumn<Song, String> colTitle;
    @FXML
    private TableColumn<Song, String> colArtist;
    @FXML
    private TableColumn<Song, Integer> colTime;
    private ChangeListener<Song> tableListener;
    
    private MyTunesModel mytunesmodel;
    private Song selectedSong;
    private PlaylistModel playlistmodel;
    @FXML
    private TableColumn<PlaylistBE, String> colPlaylist;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
      dataBind();
      addListenerToTable();
    }
    public void addListenerToTable(){
      tableSong.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>()
      {
      @Override 
      public void changed(ObservableValue<? extends Song> observableValue, Song oldValue, Song newValue) {
        if (tableSong.getSelectionModel().selectedItemProperty() != null) 
        {
        TableViewSelectionModel selectionModel = tableSong.getSelectionModel();
        ObservableList selectedCells = selectionModel.getSelectedCells();
        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
        Object val =  tablePosition.getTableColumn().getCellData(newValue);
        }
    };
  });  
}
      

    public void setSong(Song song)
    {
        currentSong = song;
    }
    /**
     * Stops the current playing song.
     * @param event 
     */
    @FXML
    private void stopSong(ActionEvent event) 
    {
        if(mediaPlayer != null)
        {
             mediaPlayer.stop();
        } 
    }
    /**
     * Play a song defined by "String path".
     * @param event 
     */                  

    @FXML
    private void pauseSong(ActionEvent event) 
    {
        if(mediaPlayer == null)
        {
        String path = "C:/Users/FrederikD/Music/Music/Radiogaga.mp3";
        File file = new File(path);
        Media hit = new Media(file.toURI().toString());
        if(mediaPlayer != null)
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(hit); 
            mediaPlayer.play();
        }
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.pause();
        }
        else
        {
            mediaPlayer.play();
        }
    }
    

        private void loadMyTunesPlaylistView() throws IOException
        {

        Stage primStage = (Stage)tablePlaylist.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/GUI/view/Playlist.fxml"));
        Parent root = loader.load();
        

        PlaylistController playlistController = loader.getController();
        
        playlistController.setModel(playlistmodel);
        
  
        Stage stagePlaylist = new Stage();
        stagePlaylist.setScene(new Scene(root));
        
        stagePlaylist.initModality(Modality.WINDOW_MODAL);
        stagePlaylist.initOwner(primStage);
        
        stagePlaylist.show();
        }
   /**
    * Binds data to our song tableview
    */
    public void dataBind()
    {
        mytunesmodel = new MyTunesModel("C:\\Users\\FrederikD\\Music\\Music");
        playlistmodel = new PlaylistModel("");
        tableSong.setItems(mytunesmodel.getAllMusic());
        tablePlaylist.setItems(playlistmodel.getAllPlaylist());
        colTitle.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getSongName()));
        colArtist.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getArtistName()));
        colPlaylist.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getPlaylistName()));
    
    }
    @FXML
    private void NewPlaylistAction(ActionEvent event) throws IOException 
    {
            PlaylistBE selectedPlaylist = tablePlaylist.getSelectionModel().getSelectedItem();
            loadMyTunesPlaylistView();
    }
        
    
    /**
     * Loads our Song.fxml.
     * @throws IOException 
     */        
    
    private void loadMyTunesSongView() throws IOException
    {
        // Fetches primary stage and gets loader and loads FXML file to Parent
        Stage primStage = (Stage)tableSong.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mytunes/GUI/view/Song.fxml"));
        Parent root = loader.load();
        
        // Fetches controller from patient view
        SongController songController = loader.getController();
        
        songController.setModel(mytunesmodel);
        
        // Sets new stage as modal window
        Stage stageSong = new Stage();
        stageSong.setScene(new Scene(root));
        
        stageSong.initModality(Modality.WINDOW_MODAL);
        stageSong.initOwner(primStage);
        
        stageSong.show();
    }

    @FXML
    private void NewSongAction(ActionEvent event) throws IOException 
    {
         Song selectedSong = tableSong.getSelectionModel().getSelectedItem();
         loadMyTunesSongView();
    }
    /**
     * Plays the selected song on song tableview.
     * @param newSong 
     */    
    public void playSong(Song newSong)
    {
        if(currentSong == null)
        {
            currentSong = newSong;
            File soundFile = new File(currentSong.getPathName());
            Media media = new Media(soundFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
        if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.pause();
        }
        else
        {
            mediaPlayer.play();
        }
     
    }
 }
    /**
     * Pauses the song currently playing.
     */
    public void pauseSong()
    {
        if (currentSong != null)
        {
            mediaPlayer.pause();
        }
    }
    
    /**
     * Shuts down the program.
     * @param event 
     */
    @FXML
    private void closeApp(ActionEvent event) 
    {
    Stage stage = (Stage) closeApp.getScene().getWindow();
    stage.close();
    }
    /**
     * Deletes selected song from tablesong.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void deleteSongAction(ActionEvent event) throws IOException 
    {   
       Song selectedItem = tableSong.getSelectionModel().getSelectedItem();
       tableSong.getItems().remove(selectedItem);
    }
   /**
    * Plays a song on our song tableview if the user double clicks on our tableview.
    * @param event 
    */
    @FXML
    private void mousePressedOnSongTable(MouseEvent event) 
    {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
        {
            if(mediaPlayer == null)
            {
                selectedSong = tableSong.selectionModelProperty().getValue().getSelectedItem();
                playSong(selectedSong);
            }
            if(mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            {
                pauseSong();
            }
            else
            {
                mediaPlayer.play();
            }
        }
    }
/**
 * Deletes the selected playlist.
 * @param event 
 */
    @FXML
    private void deletePlaylistAction(ActionEvent event) 
    {
        PlaylistBE selectedItem = tablePlaylist.getSelectionModel().getSelectedItem();
        tablePlaylist.getItems().remove(selectedItem);
    }
}

  



