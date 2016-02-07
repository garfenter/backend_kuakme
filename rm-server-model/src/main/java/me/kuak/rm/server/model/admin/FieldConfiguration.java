package me.kuak.rm.server.model.admin;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
@Table(name = "field_configuration")
@Entity
public class FieldConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "displayName")
    private String displayName;
    @Column(name = "fieldName")
    private String fieldName;
    @Column(name = "fieldType")
    private FieldType fieldType;
    @Column(name = "defaultValue")
    private String defaultValue;
    @Column(name = "type_")
    private String type_;
    @Column(name = "configuration")
    private String configuration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private EntityConfiguration parent;

    public FieldConfiguration() {
    }

    public FieldConfiguration(String displayName, String fieldName, FieldType fieldType, String defaultValue, String configuration, EntityConfiguration parent) {
        this.displayName = displayName;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.defaultValue = defaultValue;
        this.configuration = configuration;
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntityConfiguration getParent() {
        return parent;
    }

    public void setParent(EntityConfiguration parent) {
        this.parent = parent;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getType_() {
        return type_;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FieldConfiguration other = (FieldConfiguration) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

}
