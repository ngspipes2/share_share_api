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
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_dynamic_repository.logic.service.repositoryMetadata.IRepositoryMetadataService;
import pt.isel.ngspipes.share_share_api.logic.service.IOperationsService;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IInternalRepositoryController;

import java.util.Collection;

@RestController
public class InternalRepositoryController implements IInternalRepositoryController {

    @Autowired
    private IRepositoryMetadataService repositoryService;
    @Autowired
    private IPermissionService<RepositoryMetadata, Integer> permissionService;
    @Autowired
    private IOperationsService operationsService;



    @Override
    public ResponseEntity<Collection<RepositoryMetadata>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryMetadata> repositories = repositoryService.getAll();

        ControllerUtils.hidePasswordsOfRepositoriesMetadata(repositories);

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepositoryMetadata> get(@PathVariable Integer repositoryId) throws Exception {
        if(!isValidAccess(repositoryId, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        RepositoryMetadata repository = repositoryService.getById(repositoryId);

        ControllerUtils.hidePasswordOfRepositoryMetadata(repository);

        return new ResponseEntity<>(repository, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> insert(@RequestBody RepositoryMetadata repository) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        operationsService.createInternalRepository(repository);

        return new ResponseEntity<>(repository.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable Integer repositoryId, @RequestBody RepositoryMetadata repository) throws Exception {
        if(!isValidAccess(repositoryId, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!repositoryId.equals(repository.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repositoryService.update(repository);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer repositoryId) throws Exception {
        if(!isValidAccess(repositoryId, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        operationsService.deleteInternalRepository(repositoryId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryMetadata>> getRepositoriesOfUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryMetadata> repositories = repositoryService.getRepositoriesOfUser(userName);

        ControllerUtils.hidePasswordsOfRepositoriesMetadata(repositories);

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
