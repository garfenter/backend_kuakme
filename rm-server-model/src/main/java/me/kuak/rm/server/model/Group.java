package me.kuak.rm.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "group_")
@DiscriminatorValue("group")
@NamedQueries(
        @NamedQuery(name = "Group.findByUser", query = "select g from Group g where g.user = :user")
)
@XmlRootElement
public class Group extends RallyObject implements Serializable {

    @Column(name = "password")
    private String password;
    @Column(name = "user_")
    private String user;
    @Column(name = "role")
    private String role;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Person> members;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Registration> registrations;

    @Column(name = "url")
    private String url;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution")
    private Institution institution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level")
    private Level level;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar")
    private RmResource avatar;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport")
    private RmResource transport;

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public RmResource getAvatar() {
        return avatar;
    }

    public void setAvatar(RmResource avatar) {
        this.avatar = avatar;
    }

    public RmResource getTransport() {
        return transport;
    }

    public void setTransport(RmResource transport) {
        this.transport = transport;
    }

}
