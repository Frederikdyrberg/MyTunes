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
import javafx.scene.media.MediaPlayer;
import mytunes.be.Song;
import mytunes.dal.MytunesDAO;


/**
 *
 * @author FrederikD
 */
public class MyTunesManager {
    public final String filename;
    private MytunesDAO mytunesDAO;
    private Song currentSong;
    private MediaPlayer mediaplayer;
    public MyTunesManager instance;
   
    public MyTunesManager(String filename)       
    {
        mytunesDAO = new MytunesDAO();
        this.filename = filename;
    }
   public Song getCurrentlyPlayingSong()
    {
        return this.currentSong;
    }
    /**
     * 
     * @param songName
     * @param artistName
     * @param songCategory
     * @param playTime
     * @return 
     */
    public List<Song> loadMusicFiles(File file, ArrayList<Song> song) throws IOException, ClassNotFoundException
    {
       return mytunesDAO.loadMusicFromFile(song);
    }
    public void saveMusic(List<Song> allMusic, File file, InputStream inStream) throws IOException
   {
        //Because the observerable list is not serializable we have to convert it to a normal arraylist.
        ArrayList<Song> serializableList = new ArrayList<>(allMusic);
        mytunesDAO.saveMusicToFile(allMusic, file);
   }
    public MediaPlayer getMediaPlayer()
    {
        return mediaplayer;
    }
}
