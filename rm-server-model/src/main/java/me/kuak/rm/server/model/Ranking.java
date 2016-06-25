package me.kuak.rm.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(query
            = "SELECT g.id, g.user_ as name, rog.description, rco.name as country, SUM(qa.points) points\n"
            + "FROM rmdb.question_answer qa\n"
            + "INNER JOIN rmdb.question q ON (qa.question = q.id)\n"
            + "INNER JOIN rmdb.registration r ON (qa.registration = r.id)\n"
            + "INNER JOIN rmdb.group_ g ON (g.id = r.group_)\n"
            + "INNER JOIN rmdb.rally_country rc ON (rc.id = q.rally_country)\n"
            + "INNER JOIN rmdb.rally_object rog ON (rog.id = g.id)\n"
            + "INNER JOIN rmdb.rally_object rco ON (rco.id = g.country)\n"
            + "WHERE \n"
            + "	rc.rally = ?rallyId\n"
            + "GROUP BY\n"
            + "	g.id, rog.name, rco.name, rog.description", name = "Ranking.findRankingsByRallyId", resultClass = Ranking.class)
})
public class Ranking implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String group;
    @Column(name = "description")
    private String description;
    @Column(name = "country")
    private String country;
    @Column(name = "points")
    private Integer points;
    @Column(name = "position")
    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
