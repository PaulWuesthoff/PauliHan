package htwb.ai.PauliHan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.glassfish.jersey.server.JSONP;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Collection;

@Entity
@Table(name = "songlist")
@XmlRootElement(name = "songList")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerid")
    private User user;

    @Column(name = "listname")
    @XmlAttribute(name = "name")
    private String name;

    @Column(name = "state")
    @JsonProperty("isPrivate")
    @XmlAttribute(name = "isPrivate")
    private Boolean isPrivate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "song_songlist",
            joinColumns = {@JoinColumn(name = "listid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "song_id")})
    @XmlElement(name = "song")
    @JsonProperty("songs")
    private Collection<Song> songList;

    public SongList() {
    }

    public SongList(User user, String name, Boolean isPrivate, Collection<Song> songList) {
        this.user = user;
        this.name = name;
        this.isPrivate = isPrivate;
        this.songList = songList;
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

    public Collection<Song> getSongList() {
        return songList;
    }

    public void setSongList(Collection<Song> songCollection) {
        this.songList = songCollection;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", user=" + user.getUserId() + ", name=" + name + ", state=" + isPrivate + ", songs:"
                + songList.toString() + "]";
    }
}
