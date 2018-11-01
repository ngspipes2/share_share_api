package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IInternalRepositoryController {

    @RequestMapping(value = Routes.GET_ALL_INTERNAL_REPOSITORIES_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<RepositoryMetadata>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_INTERNAL_REPOSITORY_ROUTE, method = RequestMethod.GET)
    ResponseEntity<RepositoryMetadata> get(@PathVariable String repositoryName) throws Exception;

    @RequestMapping(value = Routes.CREATE_INTERNAL_REPOSITORY_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> insert(@RequestBody RepositoryMetadata repository) throws Exception;

    @RequestMapping(value = Routes.UPDATE_INTERNAL_REPOSITORY_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable String repositoryName, @RequestBody RepositoryMetadata repository) throws Exception;

    @RequestMapping(value = Routes.DELETE_INTERNAL_REPOSITORY_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable String repositoryName) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_INTERNAL_REPOSITORIES_ROUTE, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<RepositoryMetadata>> getRepositoriesOfUser(@RequestParam String userName) throws Exception;

    @RequestMapping(value = Routes.GET_INTERNAL_REPOSITORIES_NAMES_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<String>> getRepositoriesNames() throws Exception;

}
