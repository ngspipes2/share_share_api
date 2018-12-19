package pt.isel.ngspipes.share_share_api.logic.operation.importExport;

import org.springframework.stereotype.Service;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.repository.LocalPipelinesRepository;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.utils.PipelineSerialization;
import pt.isel.ngspipes.dsl_core.descriptors.tool.repository.LocalToolsRepository;
import pt.isel.ngspipes.dsl_core.descriptors.utils.IOUtils;
import pt.isel.ngspipes.dsl_core.descriptors.utils.Serialization;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ExportOperation implements IExportOperation {

    @Override
    public void exportTools(Collection<IToolDescriptor> tools, Serialization.Format format, String resultPath) throws ServiceException {
        String workingDir  = null;

        try {
            workingDir = createWorkingDir();
            LocalToolsRepository repository = new LocalToolsRepository(workingDir, new HashMap<>(), format);

            for(IToolDescriptor tool : tools)
                repository.insert(tool);

            ZipUtils.zip(workingDir, resultPath);
        } catch (Exception e) {
            throw new ServiceException("Error while exporting Tools!", e);
        } finally {
            if(workingDir != null)
                IOUtils.deleteFolder(workingDir);
        }
    }

    @Override
    public void exportPipelines(Collection<IPipelineDescriptor> pipelines, PipelineSerialization.Format format, String resultPath) throws ServiceException {
        String workingDir = null;

        try {
            workingDir = createWorkingDir();
            LocalPipelinesRepository repository = new LocalPipelinesRepository(workingDir, new HashMap<>(), format);

            for(IPipelineDescriptor pipeline : pipelines)
                repository.insert(pipeline);

            ZipUtils.zip(workingDir, resultPath);
        } catch (Exception e) {
            throw new ServiceException("Error while exporting Pipelines!", e);
        } finally {
            if(workingDir != null)
                IOUtils.deleteFolder(workingDir);
        }
    }

    public static String createWorkingDir() throws IOException {
        File workingDir;

        while(true) {
            workingDir = new File("Export_Working_Dir_" + UUID.randomUUID().toString());
            if(!workingDir.exists()) {
                workingDir.mkdirs();
                return workingDir.getPath();
            }
        }
    }

}
