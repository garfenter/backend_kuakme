package me.kuak.rm.server.svc.impl;

import me.kuak.rm.server.dao.FileDao;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.svc.FileSvc;
import org.apache.commons.fileupload.FileItem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.util.Date;

/**
 * Created by guyo on 11/28/15.
 */
@Stateless
public class FileSvcImpl implements FileSvc{

    @EJB
    private FileDao fileDao;

    private static final String MAINDIR = "/var/rm/files/";

    @Override
    public void uploadFile(FileItem file, Integer parent, String type) throws Exception {
        File dir = new File(MAINDIR + parent.toString());
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new Exception("Directory can not be created");
            }
        }
        File newFile = new File(dir.getCanonicalPath() + "/" + file.getName());
        while (!newFile.exists()) {
            newFile = File.createTempFile("REP_", file.getName(), dir);
        }
        file.write(newFile);
        saveInDB(newFile.getName(), parent, type);
    }

    private void saveInDB(String name, Integer parent, String type) {
        RmResource resource = new RmResource();
        RallyObject roParent = new RallyObject();
        roParent.setCreationDate(new Date());
        roParent.setId(parent);
        resource.setFilename(name);
        resource.setParent(roParent);
        resource.setType(type);
        fileDao.save(resource);
        resource.setDownloadUrl("/rm-server-web/rs/files/" + resource.getId());
    }

    @Override
    public File downloadFile(Integer id) {
        RmResource resource = fileDao.findById(id);
        if (resource != null) {
            File file = new File(MAINDIR + resource.getParent().getId() + "/" + resource.getFilename());
            System.out.println(file.getAbsolutePath());
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

}
