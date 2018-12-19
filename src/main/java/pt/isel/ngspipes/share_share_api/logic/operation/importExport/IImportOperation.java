package pt.isel.ngspipes.share_share_api.logic.operation.importExport;

import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import java.util.Collection;

public interface IImportOperation {

    Collection<IToolDescriptor> importTools(String path) throws ServiceException;

    Collection<IPipelineDescriptor> importPipelines(String path) throws ServiceException;

}
