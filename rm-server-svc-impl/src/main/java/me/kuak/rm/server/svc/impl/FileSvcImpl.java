package me.kuak.rm.server.svc.impl;

import me.kuak.rm.server.dao.FileDao;
import me.kuak.rm.server.model.RallyObject;
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.svc.FileSvc;
import org.apache.commons.fileupload.FileItem;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import me.kuak.rm.server.dao.RallyObjectDao;

/**
 * Created by guyo on 11/28/15.
 */
@Stateless
public class FileSvcImpl implements FileSvc {

    @EJB
    private FileDao fileDao;
    @EJB
    private RallyObjectDao rallyObjectDao;

    private static final String MAINDIR = "/var/rm/files/";

    @Override
    public RmResource uploadFile(FileItem file, Integer parent, String type) throws Exception {
        File dir = new File(MAINDIR + parent.toString());
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Exception("Directory can not be created");
            }
        }
        File newFile = new File(dir.getCanonicalPath() + "/" + file.getName());
        while (!newFile.exists()) {
            newFile = File.createTempFile("REP_", file.getName(), dir);
        }
        file.write(newFile);
        return saveInDB(newFile.getName(), parent, type);
    }

    @Override
    public RmResource uploadFile(FileItem file, String type) throws Exception {
        File dir = new File(MAINDIR + "tmp");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Exception("Directory can not be created");
            }
        }
        File newFile = new File(dir.getCanonicalPath() + "/" + file.getName());
        while (!newFile.exists()) {
            newFile = File.createTempFile("REP_", file.getName(), dir);
        }
        file.write(newFile);
        return saveInDB(newFile.getName(), null, type);
    }

    private RmResource saveInDB(String name, Integer parent, String type) {
        RmResource resource = new RmResource();
        if (parent != null) {
            resource.setParent(rallyObjectDao.findRallyObjectById(parent, RallyObject.class));
        }
        resource.setFilename(name);
        resource.setType(type);
        resource.setType_("base");
        fileDao.save(resource);
        resource.setDownloadUrl("/rm-server-web/rs/files/" + resource.getId());
        return resource;
    }

    @Override
    public File downloadFile(Integer id) {
        RmResource resource = fileDao.findById(id);
        if (resource != null) {
            File file = new File(MAINDIR +  "tmp/" + resource.getFilename());
            System.out.println(file.getAbsolutePath());
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    @Override
    public RmResource assocResourceWithParent(Integer resourceId, Integer parentId) {
        RmResource resource = fileDao.findById(resourceId);
        RallyObject parent = rallyObjectDao.findRallyObjectById(parentId, RallyObject.class);
        resource.setParent(parent);
        return resource;
    }

}
