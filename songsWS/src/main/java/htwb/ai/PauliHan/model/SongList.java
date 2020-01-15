package htwb.ai.PauliHan.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

// Probably have to change table name later
@Entity
@Table(name = "songlist")
@XmlRootElement
public class SongList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerid")
    private User user;

    @Column(name = "listname")
    private String name;

    @Column(name = "state")
    private Boolean isPrivate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "song_songlist",
            joinColumns = {@JoinColumn(name = "listid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "song_id")})
    private Collection<Song> songCollection;

    public SongList() {
    }

    public SongList(User user, String name, Boolean isPrivate, Collection<Song> songCollection) {
        this.user = user;
        this.name = name;
        this.isPrivate = isPrivate;
        this.songCollection = songCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean state) {
        this.isPrivate = state;
    }

    public Collection<Song> getSongCollection() {
        return songCollection;
    }

    public void setSongCollection(Collection<Song> songCollection) {
        this.songCollection = songCollection;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", user=" + user.getUserId() + ", name=" + name + ", state=" + isPrivate + ", songs:"
                + songCollection.toString() + "]";
    }
}
