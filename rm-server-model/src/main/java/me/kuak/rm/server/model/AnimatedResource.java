package me.kuak.rm.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Entity
@Table(name = "ANIMATED_RESOURCE")
@DiscriminatorValue("animated-resource")
public class AnimatedResource extends RmResource implements Serializable {

    @Column(name = "posx")
    private Integer posx;
    @Column(name = "posy")
    private Integer posy;
    @Column(name = "posz")
    private Integer posz;
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;
    @Column(name = "animation")
    private String animation;

    public AnimatedResource() {
    }

    public AnimatedResource(String downloadUrl, String filename, String extension, String contentType, String type, String name, String description) {
        super(downloadUrl, filename, extension, contentType, type, name, description);
    }

    public Integer getPosx() {
        return posx;
    }

    public void setPosx(Integer posx) {
        this.posx = posx;
    }

    public Integer getPosy() {
        return posy;
    }

    public void setPosy(Integer posy) {
        this.posy = posy;
    }

    public Integer getPosz() {
        return posz;
    }

    public void setPosz(Integer posz) {
        this.posz = posz;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

}
