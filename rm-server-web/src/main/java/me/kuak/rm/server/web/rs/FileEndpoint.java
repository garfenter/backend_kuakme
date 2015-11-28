package me.kuak.rm.server.web.rs;

import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author Eduardo Herrera <guyo14>
 */
@Path("files")
@Stateless
public class FileEndpoint {

    private static final String TEMPDIR = "/tmp/";
    private static final String PREFIX = "rm_";

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(@Context HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String type = null;
        Integer parent = null;
        List<File> files = new ArrayList<>();
        try {
            List items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while(iterator.hasNext()) {
                FileItem item = iterator.next();
                if (item.getFieldName().equals("parent")) {
                    parent = Integer.valueOf(item.getString());
                } else if (item.getFieldName().equals("type")) {
                    type = item.getString();
                } else if (item.getFieldName().equals("file")) {
                    //fFile tmp = File.createTempFile(PREFIX, item.getName());
                    //item.write();
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } finally {
            for (File file : files) {
                file.delete();
            }
        }
        return "All ok";
    }

    @GET
    public String downloadFile() {
        return "HI";
    }


}