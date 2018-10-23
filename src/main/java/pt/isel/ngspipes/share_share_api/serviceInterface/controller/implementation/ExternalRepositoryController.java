package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_publish_repository.logic.domain.PublishedRepository;
import pt.isel.ngspipes.share_publish_repository.logic.service.publishedRepository.IPublishedRepositoryService;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IExternalRepositoryController;

import java.util.Collection;

@RestController
public class ExternalRepositoryController implements IExternalRepositoryController {

    @Autowired
    private IPublishedRepositoryService publishedRepositoryService;
    @Autowired
    private IPermissionService<PublishedRepository, Integer> permissionService;



    @Override
    public ResponseEntity<Collection<PublishedRepository>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<PublishedRepository> repositories = publishedRepositoryService.getAll();

        ControllerUtils.hidePasswordsOfPublishedRepositories(repositories);

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PublishedRepository> get(@PathVariable Integer repositoryId) throws Exception {
        if(!isValidAccess(repositoryId, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        PublishedRepository repository = publishedRepositoryService.getById(repositoryId);

        ControllerUtils.hidePasswordOfPublishedRepository(repository);

        return new ResponseEntity<>(repository, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> insert(@RequestBody PublishedRepository repository) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        publishedRepositoryService.insert(repository);

        return new ResponseEntity<>(repository.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer repositoryId, @RequestBody PublishedRepository repository) throws Exception {
        if(!isValidAccess(repositoryId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!repositoryId.equals(repository.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        publishedRepositoryService.update(repository);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer repositoryId) throws Exception {
        if(!isValidAccess(repositoryId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        publishedRepositoryService.delete(repositoryId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<PublishedRepository>> getRepositoriesOfUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<PublishedRepository> repositories = publishedRepositoryService.getRepositoriesOfUser(userName);

        ControllerUtils.hidePasswordsOfPublishedRepositories(repositories);

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    private boolean isValidAccess(Integer repositoryId, Access.Operation operation) throws ServiceException {
        Access<Integer> access = new Access<>(getCurrentUserName(), operation, repositoryId);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
