package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryInfo;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.permission.Access;
import pt.isel.ngspipes.share_core.logic.service.permission.IPermissionService;
import pt.isel.ngspipes.share_share_api.logic.operation.repositoryInfo.RepositoryInfoOperation;
import pt.isel.ngspipes.share_share_api.serviceInterface.controller.facade.IRepositoryInfoController;

import java.util.Collection;

@RestController
public class RepositoryInfoController implements IRepositoryInfoController {

    @Autowired
    private RepositoryInfoOperation repositoryInfoOperation;
    @Autowired
    private IPermissionService<RepositoryInfo, String> permissionService;



    @Override
    public ResponseEntity<Collection<RepositoryInfo>> getAll() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryInfo> repositories = repositoryInfoOperation.getAllRepositories();

        ControllerUtils.hidePasswordsOfRepositoriesInfo(repositories);

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RepositoryInfo> get(@PathVariable String repositoryName) throws Exception {
        if(!isValidAccess(repositoryName, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        RepositoryInfo repository = repositoryInfoOperation.getRepository(repositoryName);

        ControllerUtils.hidePasswordOfRepositoryInfo(repository);

        return new ResponseEntity<>(repository, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> insert(@RequestBody RepositoryInfo repository) throws Exception {
        if(!isValidAccess(null, Access.Operation.INSERT))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryInfoOperation.createRepository(repository);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable String repositoryName, @RequestBody RepositoryInfo repository) throws Exception {
        if(!isValidAccess(repositoryName, Access.Operation.UPDATE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if(!repositoryName.equals(repository.getRepositoryName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        repositoryInfoOperation.updateRepository(repository);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable String repositoryName) throws Exception {
        if(!isValidAccess(repositoryName, Access.Operation.DELETE))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        repositoryInfoOperation.deleteRepository(repositoryName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryInfo>> getRepositoriesOfUser(@RequestParam String userName) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryInfo> repositories = repositoryInfoOperation.getRepositoriesOfUser(userName);

        ControllerUtils.hidePasswordsOfRepositoriesInfo(repositories);

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<String>> getRepositoriesNames() throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<String> repositoriesNames = repositoryInfoOperation.getRepositoriesNames();

        return new ResponseEntity<>(repositoriesNames, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<RepositoryInfo>> getRepositoriesAccessibleByUser(@RequestParam String accessibleBy) throws Exception {
        if(!isValidAccess(null, Access.Operation.GET))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Collection<RepositoryInfo> repositories = repositoryInfoOperation.getRepositoriesAccessibleByUser(accessibleBy);

        ControllerUtils.hidePasswordsOfRepositoriesInfo(repositories);

        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }

    private boolean isValidAccess(String repositoryName, Access.Operation operation) throws ServiceException {
        Access<String> access = new Access<>(getCurrentUserName(), operation, repositoryName);
        return permissionService.isValidAccess(access);
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
