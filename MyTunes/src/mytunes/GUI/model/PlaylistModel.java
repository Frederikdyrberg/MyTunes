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
import mytunes.be.PlaylistBE;
import mytunes.bll.PlaylistManager;

/**
 *
 * @author Nicolai
 */
public class PlaylistModel 
{
    private final ObservableList<PlaylistBE> allPlaylist;
    
    public final PlaylistManager playlistmanager;
    
    public PlaylistModel(String file){
        playlistmanager = new PlaylistManager(file);
        allPlaylist = FXCollections.observableArrayList();
    }
    
    public ObservableList<PlaylistBE> getAllPlaylist()
    {
        return allPlaylist;
    }
    public void addPlaylist(String playlistName)
    {
        PlaylistBE playlist = new PlaylistBE(playlistName);
        allPlaylist.add(playlist);
    }
    public void SavePlaylistsToFile(File file, InputStream inStream) throws IOException
    {       
       playlistmanager.savePlaylist(allPlaylist, file, inStream);
    }
}
