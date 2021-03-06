package me.kuak.rm.server.svc;

import java.io.File;
import me.kuak.rm.server.model.RmResource;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author Juan Luis Cano <garfenter at adstter.com>
 */
public interface FileSvc {

    RmResource uploadFile(FileItem data, Integer parent, String type) throws Exception;

    RmResource uploadFile(FileItem data, String type) throws Exception;

    RmResource assocResourceWithParent(Integer resourceId, Integer parentId);

    File downloadFile(Integer id);
}
