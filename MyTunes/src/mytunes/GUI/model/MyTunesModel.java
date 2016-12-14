/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.GUI.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mytunes.be.Song;
import mytunes.bll.MyTunesManager;
/**
 *
 * @author FrederikD
 */
public class MyTunesModel {
    
    private final ObservableList<Song> allMusic;
    
    public final MyTunesManager mytunesmanager;
    
    public MyTunesModel(String file){
        mytunesmanager = new MyTunesManager(file);
        allMusic = FXCollections.observableArrayList();
   }
    public ObservableList<Song> getAllMusic()
    {
       return allMusic;
    }
    /**
     * Creates a new Song object and adds it.
     * @param songName
     * @param artistName
     * @param pathName 
     */
    public void addSong(String songName,String artistName, String pathName)
    {
        Song song = new Song(songName, artistName, pathName);
        allMusic.add(song);
    }
    public void SaveSongsToFile(File file, InputStream inStream) throws IOException
    {       
       mytunesmanager.saveMusic(allMusic, file, inStream);
    }
   }
    
    
 

