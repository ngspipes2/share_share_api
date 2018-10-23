package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import pt.isel.ngspipes.share_core.logic.domain.Group;
import pt.isel.ngspipes.share_core.logic.domain.GroupMember;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryMetadata;
import pt.isel.ngspipes.share_dynamic_repository.logic.domain.RepositoryUserMember;
import pt.isel.ngspipes.share_publish_repository.logic.domain.PublishedRepository;

import java.util.Collection;

public class ControllerUtils {

    public static void hidePasswordsOfPublishedRepositories(Collection<PublishedRepository> repositories) {
        repositories.forEach(ControllerUtils::hidePasswordOfPublishedRepository);
    }

    public static void hidePasswordOfPublishedRepository(PublishedRepository repository) {
        if(repository != null) {
            hidePassword(repository.getPublisher());
        }
    }


    public static void hidePasswordsOfRepositoryGroupMembers(Collection<RepositoryGroupMember> members) {
        members.forEach(ControllerUtils::hidePasswordOfRepositoryGroupMember);
    }

    public static void hidePasswordOfRepositoryGroupMember(RepositoryGroupMember member) {
        if(member != null) {
            hidePasswordOfRepositoryMetadata(member.getRepository());
            hidePasswordOfGroup(member.getGroup());
        }
    }


    public static void hidePasswordsOfRepositoryUserMembers(Collection<RepositoryUserMember> members) {
        members.forEach(ControllerUtils::hidePasswordOfRepositoryUserMember);
    }

    public static void hidePasswordOfRepositoryUserMember(RepositoryUserMember member) {
        if(member != null) {
            hidePasswordOfRepositoryMetadata(member.getRepository());
            hidePassword(member.getUser());
        }
    }


    public static void hidePasswordsOfRepositoriesMetadata(Collection<RepositoryMetadata> repositories) {
        repositories.forEach(ControllerUtils::hidePasswordOfRepositoryMetadata);
    }

    public static void hidePasswordOfRepositoryMetadata(RepositoryMetadata repository) {
        if(repository != null){
            hidePassword(repository.getOwner());
        }
    }


    public static void hidePasswordsOfGroupMembers(Collection<GroupMember> members) {
        members.forEach(ControllerUtils::hidePasswordOfGroupMember);
    }

    public static void hidePasswordOfGroupMember(GroupMember member) {
        if(member != null){
            hidePasswordOfGroup(member.getGroup());
            hidePassword(member.getUser());
        }
    }


    public static void hidePasswordsOfGroups(Collection<Group> groups) {
        groups.forEach(ControllerUtils::hidePasswordOfGroup);
    }

    public static void hidePasswordOfGroup(Group group) {
        if(group != null)
            hidePassword(group.getOwner());
    }


    public static void hidePasswords(Collection<User> users) {
        users.forEach(ControllerUtils::hidePassword);
    }

    public static void hidePassword(User user) {
        if(user != null)
            user.setPassword("");
    }

}
