package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pt.isel.ngspipes.dsl_core.descriptors.utils.IOUtils;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_share_api.logic.operation.importExport.IImportOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IImportController;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

@RestController
public class ImportController implements IImportController {

    @Autowired
    private IImportOperation importOperation;



    @Override
    public Collection<IToolDescriptor> importTools(@RequestPart(value = "file") MultipartFile file) throws Exception {
        String tempDir = createWorkingDir();
        String fileName = file.getName() + ".zip";
        String filePath = tempDir + File.separator + fileName;

        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
            return importOperation.importTools(filePath);
        } finally {
            IOUtils.deleteFolder(tempDir);
        }
    }

    @Override
    public Collection<IPipelineDescriptor> importPipelines(@RequestPart(value = "file") MultipartFile file) throws Exception {
        String tempDir = createWorkingDir();
        String fileName = file.getName() + ".zip";
        String filePath = tempDir + File.separator + fileName;

        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
            return importOperation.importPipelines(filePath);
        } finally {
            IOUtils.deleteFolder(tempDir);
        }
    }

    public static String createWorkingDir() throws IOException {
        File workingDir;

        while(true) {
            workingDir = new File(UUID.randomUUID().toString());
            if(!workingDir.exists()) {
                workingDir.mkdirs();
                return workingDir.getPath();
            }
        }
    }

}
