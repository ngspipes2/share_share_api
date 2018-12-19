package pt.isel.ngspipes.share_share_api.logic.operation.importExport;

import pt.isel.ngspipes.dsl_core.descriptors.pipeline.utils.PipelineSerialization;
import pt.isel.ngspipes.dsl_core.descriptors.utils.Serialization;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import java.util.Collection;

public interface IExportOperation {

    void exportTools(Collection<IToolDescriptor> tools, Serialization.Format format, String resultPath) throws ServiceException;

    void exportPipelines(Collection<IPipelineDescriptor> pipelines, PipelineSerialization.Format format, String resultPath) throws ServiceException;

}
