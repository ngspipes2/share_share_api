package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.utils.PipelineSerialization;
import pt.isel.ngspipes.dsl_core.descriptors.utils.Serialization;
import pt.isel.ngspipes.pipeline_descriptor.IPipelineDescriptor;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;
import pt.isel.ngspipes.tool_descriptor.interfaces.IToolDescriptor;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@CrossOrigin
@RestController
public interface IExportController {

    @RequestMapping(value = Routes.EXPORT_TOOLS_ROUTE, method = RequestMethod.POST)
    ResponseEntity<ByteArrayResource> exportTools(
            @RequestBody Collection<IToolDescriptor> tools,
            @RequestParam Serialization.Format format,
            @RequestParam String outputName,
            HttpServletResponse response) throws Exception;

    @RequestMapping(value = Routes.EXPORT_PIPELINES_ROUTE, method = RequestMethod.POST)
    ResponseEntity<ByteArrayResource> exportPipelines(
            @RequestBody Collection<IPipelineDescriptor> pipelines,
            @RequestParam PipelineSerialization.Format format,
            @RequestParam String outputName,
            HttpServletResponse response) throws Exception;

}
