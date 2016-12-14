/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.bll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.PlaylistBE;
import mytunes.dal.PlaylistDAO;

/**
 *
 * @author Nicolai
 */
public class PlaylistManager 
{   
    public final String filename;
    private PlaylistDAO playlistDAO;
    
    public PlaylistManager(String filename)
    {
        playlistDAO = new PlaylistDAO();
        this.filename = filename;
    }
    
    public List<PlaylistBE> loadPlaylistFiles(File file, ArrayList<PlaylistBE> playlist) throws IOException, ClassNotFoundException
   {
       return playlistDAO.loadPlaylistFromFile(playlist);
    }
    public void savePlaylist(List<PlaylistBE> allPlaylist, File file, InputStream inStream) throws IOException
   {
        //Because the observerable list is not serializable we have to convert it to a normal arraylist.
        ArrayList<PlaylistBE> serializableList = new ArrayList<>(allPlaylist);
        playlistDAO.savePlaylistToFile(allPlaylist, file);
   }
}
