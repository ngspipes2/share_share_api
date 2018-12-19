package pt.isel.ngspipes.share_share_api.logic.operation.importExport;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.repository.LocalPipelinesRepository;
import pt.isel.ngspipes.dsl_core.descriptors.tool.repository.LocalToolsRepository;
import pt.isel.ngspipes.dsl_core.descriptors.utils.IOUtils;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ImportOperation implements IImportOperation {

    @Override
    public Collection<IToolDescriptor> importTools(String path) throws ServiceException {
        String workingDir = null;

        try {
            workingDir = createWorkingDir();

            ZipUtils.unzip(path, workingDir);

            LocalToolsRepository repository = new LocalToolsRepository(workingDir, new HashMap<>());

            return repository.getAll();
        } catch (Exception e) {
            throw new ServiceException("Error while importing Tools!", e);
        } finally {
            if(workingDir != null)
                IOUtils.deleteFolder(workingDir);
        }
    }

    @Override
    public Collection<IPipelineDescriptor> importPipelines(String path) throws ServiceException {
        String workingDir = null;

        try {
            workingDir = createWorkingDir();

            ZipUtils.unzip(path, workingDir);

            LocalPipelinesRepository repository = new LocalPipelinesRepository(workingDir, new HashMap<>());

            return repository.getAll();
        } catch (Exception e) {
            throw new ServiceException("Error while importing Pipelines!", e);
        } finally {
            if(workingDir != null)
                IOUtils.deleteFolder(workingDir);
        }
    }

    public static String createWorkingDir() throws IOException {
        File workingDir;

        while(true) {
            workingDir = new File("Import_Working_Dir_" + UUID.randomUUID().toString());
            if(!workingDir.exists()) {
                workingDir.mkdirs();
                return workingDir.getPath();
            }
        }
    }

}
