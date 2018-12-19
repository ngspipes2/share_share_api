package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IImportController {

    @RequestMapping(value = Routes.IMPORT_TOOLS_ROUTE, method = RequestMethod.POST)
    Collection<IToolDescriptor> importTools(@RequestPart(value = "file") MultipartFile file) throws Exception;

    @RequestMapping(value = Routes.IMPORT_PIPELINES_ROUTE, method = RequestMethod.POST)
    Collection<IPipelineDescriptor> importPipelines(@RequestPart(value = "file") MultipartFile file) throws Exception;

}
