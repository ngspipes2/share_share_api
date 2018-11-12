package pt.isel.ngspipes.share_share_api.serviceInterface.controller.implementation;

import pt.isel.ngspipes.share_core.logic.domain.*;

import java.util.Collection;

public class ControllerUtils {

    public static void hidePasswordsOfAccessTokens(Collection<AccessToken> tokens) {
        tokens.forEach(ControllerUtils::hidePasswordOfAccessToken);
    }

    public static void hidePasswordOfAccessToken(AccessToken token) {
        token.getOwner().setPassword("");
        token.setToken("");
    }


    public static void hidePasswordsOfRepositoryGroupMembers(Collection<RepositoryGroupMember> members) {
        members.forEach(ControllerUtils::hidePasswordOfRepositoryGroupMember);
    }

    public static void hidePasswordOfRepositoryGroupMember(RepositoryGroupMember member) {
        if(member != null) {
            hidePasswordOfRepositoryInfo(member.getRepository());
            hidePasswordOfGroup(member.getGroup());
        }
    }


    public static void hidePasswordsOfRepositoryUserMembers(Collection<RepositoryUserMember> members) {
        members.forEach(ControllerUtils::hidePasswordOfRepositoryUserMember);
    }

    public static void hidePasswordOfRepositoryUserMember(RepositoryUserMember member) {
        if(member != null) {
            hidePasswordOfRepositoryInfo(member.getRepository());
            hidePassword(member.getUser());
        }
    }


    public static void hidePasswordsOfRepositoriesInfo(Collection<RepositoryInfo> repositories) {
        repositories.forEach(ControllerUtils::hidePasswordOfRepositoryInfo);
    }

    public static void hidePasswordOfRepositoryInfo(RepositoryInfo repository) {
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
