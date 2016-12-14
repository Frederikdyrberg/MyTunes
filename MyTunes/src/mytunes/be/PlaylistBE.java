/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import java.io.Serializable;

/**
 *
 * @author Nicolai
 */
public class PlaylistBE implements Serializable 
{
    private String playlistName;
    
    public PlaylistBE(String playlistName){
        this.playlistName = playlistName;
    }
    
    public String getPlaylistName()
    {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
