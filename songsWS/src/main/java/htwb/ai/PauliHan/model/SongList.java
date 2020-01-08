package htwb.ai.PauliHan.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

// Probably have to change table name later
@Entity
@Table(name = "songLists")
@XmlRootElement
public class SongList {
    @Id
    @Column(name = "list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ownerID")
    private String ownerId;
    @Column(name = "listName")
    private String name;
    @Column(name = "state")
    private Boolean state;
    @Column(name = "songs")
    private Collection<Song> songCollection;

    public SongList() {
    }

    public SongList(String ownerId, String name, Boolean state, Collection<Song> songCollection) {
        this.ownerId = ownerId;
        this.name = name;
        this.state = state;
        this.songCollection = songCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Collection<Song> getSongCollection() {
        return songCollection;
    }

    public void setSongCollection(Collection<Song> songCollection) {
        this.songCollection = songCollection;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", owner=" + ownerId + ", name=" + name + ", state=" + state + ", songs:"
                + songCollection.toString() + "]";
    }
}
