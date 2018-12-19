package pt.isel.ngspipes.share_share_api.serviceInterface.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.isel.ngspipes.dsl_core.descriptors.pipeline.PipelineMapper;
import pt.isel.ngspipes.dsl_core.descriptors.tool.ToolMapper;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule pipelineTypeResolverModule = new SimpleModule();
        pipelineTypeResolverModule.setAbstractTypes(PipelineMapper.getTypeResolver());
        SimpleModule pipelineSerializerModule = PipelineMapper.getSerializerModule();
        SimpleModule pipelineDeserializerModule = PipelineMapper.getDeserializerModule();

        SimpleModule toolTypeResolverModule = new SimpleModule();
        toolTypeResolverModule.setAbstractTypes(ToolMapper.getTypeResolver());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(pipelineTypeResolverModule);
        mapper.registerModule(pipelineSerializerModule);
        mapper.registerModule(pipelineDeserializerModule);
        mapper.registerModule(toolTypeResolverModule);

        return mapper;
    }

}
