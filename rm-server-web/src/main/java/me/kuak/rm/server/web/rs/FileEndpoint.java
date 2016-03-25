package me.kuak.rm.server.web.rs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.kuak.rm.server.svc.FileSvc;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
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

    @EJB
    private FileSvc fileSvc;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@Context HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String type = null;
        Integer parent = null;
        List<FileItem> files = new ArrayList<>();
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
                    files.add(item);
                }
            }
            if (parent != null && type != null && files.size() > 0) {
                for (FileItem file : files) {
                    fileSvc.uploadFile(file, parent, type);
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("{id}")
    public Response downloadFile(@PathParam("id") Integer id) {
        File file = fileSvc.downloadFile(id);

        if (file != null && file.exists()) {
            return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) //optional
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}