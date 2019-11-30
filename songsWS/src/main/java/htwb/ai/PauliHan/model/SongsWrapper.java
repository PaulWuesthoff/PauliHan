package htwb.ai.PauliHan.model;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;
import java.util.List;

public class SongsWrapper {
    private List<Song> songs;

    public SongsWrapper() {
        songs = new ArrayList<Song>();
    }

    public SongsWrapper(List<Song> songs) {
        this.songs = songs;
    }

    @XmlAnyElement(lax=true)
    public List<Song> getSongs() {
        return songs;
    }
}
