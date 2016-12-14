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
import mytunes.be.Song;

/**
 *
 * @author FrederikD
 */
public class MytunesDAO {
    
    private ArrayList <Song> saveSong;
    
    public MytunesDAO(){
        saveSong = new ArrayList();
    }
    
    
    public void saveMusicToFile(List<Song> allMusic, File file) throws IOException
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(allMusic);
        }
    }
    
    public List<Song> loadMusicFromFile(ArrayList<Song> song) throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/Users/FrederikD/Music/Music")))
        {
            saveSong = (ArrayList<Song>) ois.readObject();
        }
        return saveSong;
    }   
}
