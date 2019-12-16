package htwb.ai.PauliHan.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userid")
    private String userId;
    @Column(name = "key")
    private String key;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    public User() {
    }

    public User(String userId, String key, String firstName, String lastName) {
        this.userId = userId;
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "id: " + userId + ", key: " + key + ", firstname: " + firstName + ", lastname: " + lastName;
    }
}
