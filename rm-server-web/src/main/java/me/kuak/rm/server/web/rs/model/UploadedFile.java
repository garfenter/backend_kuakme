package me.kuak.rm.server.web.rs.model;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class UploadedFile {

    private String name;
    private Long size;
    private String url;
    private String thumbnailUrl;
    private String deleteUrl;
    private String deleteType;
    private Integer resourceId;

    public UploadedFile() {
    }

    public UploadedFile(String name, Long size, String url, String thumbnailUrl, String deleteUrl, String deleteType, Integer resourceId) {
        this.name = name;
        this.size = size;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.deleteUrl = deleteUrl;
        this.deleteType = deleteType;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

}
