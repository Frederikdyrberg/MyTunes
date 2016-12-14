/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.io.Serializable;

/**
 *
 * @author FrederikD
 */
public class Song implements Serializable {
    
    private String songName;
    private String artistName;
    private String pathName;
    
    public Song(String songName,String artistName, String pathName){
        this.songName = songName;
        this.artistName = artistName;
        this.pathName = pathName;
    }
    
    public String getSongName()
    {
        return songName;
    }
    public String getArtistName()
    {
        return artistName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

   
}
    
    

