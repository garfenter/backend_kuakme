package me.kuak.rm.server.web.rs.model;

import java.util.List;
import me.kuak.rm.server.model.RmResource;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class FileUploadResponse {

    List<RmResource> files;

    public FileUploadResponse() {
    }

    public FileUploadResponse(List<RmResource> files) {
        this.files = files;
    }

    public List<RmResource> getFiles() {
        return files;
    }

    public void setFiles(List<RmResource> files) {
        this.files = files;
    }

}
