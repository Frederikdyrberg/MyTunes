/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.PlaylistBE;

/**
 *
 * @author Nicolai
 */
public class PlaylistDAO {

    
    private ArrayList <PlaylistBE> savePlaylist;
    
    public PlaylistDAO(){
        savePlaylist = new ArrayList();
    }
    
    
    public void savePlaylistToFile(List<PlaylistBE> allPlaylist, File file) throws IOException
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(allPlaylist);
        }
    }
    
    public List<PlaylistBE> loadPlaylistFromFile(ArrayList<PlaylistBE> playlist) throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/Users/FrederikD/Music/Music")))
        {
            savePlaylist = (ArrayList<PlaylistBE>) ois.readObject();
        }
        return savePlaylist;
    }
}
