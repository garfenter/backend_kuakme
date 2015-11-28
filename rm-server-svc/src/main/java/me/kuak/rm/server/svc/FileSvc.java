package me.kuak.rm.server.svc;

import java.io.File;
import java.io.InputStream;
import me.kuak.rm.server.model.RmResource;
import org.apache.commons.fileupload.FileItem;

import javax.xml.ws.Response;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface FileSvc {

    void uploadFile(FileItem data, Integer parent, String type) throws Exception;

    File downloadFile(Integer id);
}
