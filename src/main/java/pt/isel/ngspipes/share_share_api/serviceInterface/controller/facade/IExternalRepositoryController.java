package pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isel.ngspipes.share_publish_repository.logic.domain.PublishedRepository;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import java.util.Collection;

@CrossOrigin
@RestController
public interface IExternalRepositoryController {

    @RequestMapping(value = Routes.GET_ALL_EXTERNAL_REPOSITORIES_ROUTE, method = RequestMethod.GET)
    ResponseEntity<Collection<PublishedRepository>> getAll() throws Exception;

    @RequestMapping(value = Routes.GET_EXTERNAL_REPOSITORY_ROUTE, method = RequestMethod.GET)
    ResponseEntity<PublishedRepository> get(@PathVariable Integer repositoryId) throws Exception;

    @RequestMapping(value = Routes.CREATE_EXTERNAL_REPOSITORY_ROUTE, method = RequestMethod.POST)
    ResponseEntity<Integer> insert(@RequestBody PublishedRepository repository) throws Exception;

    @RequestMapping(value = Routes.UPDATE_EXTERNAL_REPOSITORY_ROUTE, method = RequestMethod.PUT)
    ResponseEntity<Void> update(@PathVariable Integer repositoryId, @RequestBody PublishedRepository repository) throws Exception;

    @RequestMapping(value = Routes.DELETE_EXTERNAL_REPOSITORY_ROUTE, method = RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable Integer repositoryId) throws Exception;

    @RequestMapping(value = Routes.GET_ALL_EXTERNAL_REPOSITORIES_ROUTE, method = RequestMethod.GET, params = "userName")
    ResponseEntity<Collection<PublishedRepository>> getRepositoriesOfUser(@RequestParam String userName) throws Exception;

}
