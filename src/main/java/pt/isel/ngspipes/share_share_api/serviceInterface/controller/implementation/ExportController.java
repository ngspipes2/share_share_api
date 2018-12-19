package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.utils.PipelineSerialization;
import pt.isel.ngspipes.dsl_core.descriptors.utils.Serialization;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_share_api.logic.operation.importExport.IExportOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IExportController;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

@RestController
public class ExportController implements IExportController {

    @Autowired
    private IExportOperation exportOperation;



    @Override
    public ResponseEntity<ByteArrayResource> exportTools(@RequestBody Collection<IToolDescriptor> tools,
                                                         @RequestParam Serialization.Format format,
                                                         @RequestParam String outputName,
                                                         HttpServletResponse response) throws Exception {
        String fileName = outputName + ".zip";
        String resultPath = "." + File.separator + fileName;
        exportOperation.exportTools(tools, format, resultPath);

        File file = new File(resultPath);
        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        file.delete();

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName.replace(" ", "_"))
                .body(resource);
    }

    @Override
    public ResponseEntity<ByteArrayResource> exportPipelines(@RequestBody Collection<IPipelineDescriptor> pipelines,
                                                             @RequestParam PipelineSerialization.Format format,
                                                             @RequestParam String outputName,
                                                             HttpServletResponse response) throws Exception {
        String fileName = outputName + ".zip";
        String resultPath = "." + File.separator + fileName;
        exportOperation.exportPipelines(pipelines, format, resultPath);

        File file = new File(resultPath);
        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        file.delete();

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName.replace(" ", "_"))
                .body(resource);
    }

}
