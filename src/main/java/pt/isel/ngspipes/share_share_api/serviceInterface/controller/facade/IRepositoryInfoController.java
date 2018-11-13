package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IRepositoryInfoController {

    @RequestMapping(value = Routes.GET_ALL_REPOSITORIES_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<RepositoryInfo>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_REPOSITORY_ROUTE, method = RequestMethod.GET)
    ResponseEntity<RepositoryInfo> get(@PathVariable String repositoryName) throws Exception;

    @RequestMapping(value = Routes.CREATE_REPOSITORY_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Void> insert(@RequestBody RepositoryInfo repository) throws Exception;

    @RequestMapping(value = Routes.UPDATE_REPOSITORY_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable String repositoryName, @RequestBody RepositoryInfo repository) throws Exception;

    @RequestMapping(value = Routes.DELETE_REPOSITORY_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable String repositoryName) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_REPOSITORIES_ROUTE, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<RepositoryInfo>> getRepositoriesOfUser(@RequestParam String userName) throws Exception;

    @RequestMapping(value = Routes.GET_REPOSITORIES_NAMES_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<String>> getRepositoriesNames() throws Exception;

    @RequestMapping(value = Routes.GET_ALL_REPOSITORIES_ROUTE, method = RequestMethod.GET, params = "accessibleBy")
    ResponseEntity<Collection<RepositoryInfo>> getRepositoriesAccessibleByUser(@RequestParam String accessibleBy) throws Exception;

}
