package htwb.ai.PauliHan.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "songs")
@XmlRootElement
public class Song {


    @Id
    @Column(name = "song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "artist")
    private String artist;
    @Column(name = "label")
    private String label;
    @Column(name = "released")
    private Integer released;

    public Song() {
    }

    public Song(Integer id, String title, String artist, String label, Integer released) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.released = released;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }
    
    public boolean isValid() {
    	if(this.title == null || this.title.isEmpty()) return false;
    	if(id == null) return false;
    	return true;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", title=" + title + ", artist=" + artist + ", label=" + label + ", released="
                + released + "]";
    }
}
