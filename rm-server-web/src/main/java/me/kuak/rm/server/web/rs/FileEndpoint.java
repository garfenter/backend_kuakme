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
import me.kuak.rm.server.model.RmResource;
import me.kuak.rm.server.web.rs.model.FileUploadResponse;
import me.kuak.rm.server.web.rs.model.UploadedFile;

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
    @Path("/{type}/{parent}")
    public FileUploadResponse uploadFile(@PathParam("type") String type, @PathParam("parent") Integer parent, @Context HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> files = new ArrayList<>();
        try {
            List items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                switch (item.getFieldName()) {
                    case "file":
                        files.add(item);
                        break;
                    default:
                        break;
                }
            }
            FileUploadResponse result = new FileUploadResponse(new ArrayList<UploadedFile>());
            if (parent != null && type != null && files.size() > 0) {
                for (FileItem file : files) {
                    RmResource resource = fileSvc.uploadFile(file, parent, type);
                    result.getFiles().add(new UploadedFile(file.getFieldName(), file.getSize(), resource.getDownloadUrl(), resource.getDownloadUrl(), resource.getDownloadUrl(), resource.getDownloadUrl()));
                }
            } 
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new FileUploadResponse(new ArrayList<UploadedFile>());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("{id}")
    public Response downloadFile(@PathParam("id") Integer id) {
        File file = fileSvc.downloadFile(id);

        if (file != null && file.exists()) {
            return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"") //optional
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("{id}")
    public Response deleteFile(@PathParam("id") Integer id) {
        File file = fileSvc.downloadFile(id);

        if (file != null && file.exists()) {
            return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"") //optional
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
