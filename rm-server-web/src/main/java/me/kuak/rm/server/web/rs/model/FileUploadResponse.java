package me.kuak.rm.server.web.rs.model;

import java.util.List;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public class FileUploadResponse {

    List<UploadedFile> files;

    public FileUploadResponse() {
    }

    public FileUploadResponse(List<UploadedFile> files) {
        this.files = files;
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }

}
