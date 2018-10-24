package pt.isel.ngspipes.share_share_api.serviceInterface.config;

public class Routes {

    public static final String USERS_ROUTE = "/users";

    public static final String GET_ALL_USERS_ROUTE = USERS_ROUTE;
    public static final String GET_USER_ROUTE = USERS_ROUTE + "/{userName}";
    public static final String CREATE_USER_ROUTE = USERS_ROUTE;
    public static final String UPDATE_USER_ROUTE = USERS_ROUTE + "/{userName}";
    public static final String DELETE_USER_ROUTE = USERS_ROUTE + "/{userName}";
    public static final String GET_USER_IMAGE_ROUTE = USERS_ROUTE + "/{userName}/image";
    public static final String CHANGE_USER_IMAGE_ROUTE = USERS_ROUTE + "/{userName}/image";
    public static final String CHANGE_USER_PASSWORD_ROUTE = USERS_ROUTE + "/{userName}/password";
    public static final String GET_USERS_NAMES_ROUTE = "/usersnames";


    public static final String GROUPS_ROUTE = "/groups";

    public static final String GET_ALL_GROUPS_ROUTE = GROUPS_ROUTE;
    public static final String GET_GROUP_ROUTE = GROUPS_ROUTE + "/{groupName}";
    public static final String CREATE_GROUP_ROUTE = GROUPS_ROUTE;
    public static final String UPDATE_GROUP_ROUTE = GROUPS_ROUTE + "/{groupName}";
    public static final String DELETE_GROUP_ROUTE = GROUPS_ROUTE + "/{groupName}";
    public static final String GET_GROUP_IMAGE_ROUTE = GROUPS_ROUTE + "/{groupName}/image";
    public static final String CHANGE_GROUP_IMAGE_ROUTE = GROUPS_ROUTE + "/{groupName}/image";
    public static final String GET_GROUPS_NAMES_ROUTE = "/groupsnames";


    public static final String GROUP_MEMBERS_ROUTE = "/groupmembers";

    public static final String GET_ALL_GROUP_MEMBERS_ROUTE = GROUP_MEMBERS_ROUTE;
    public static final String GET_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String CREATE_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE;
    public static final String UPDATE_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String DELETE_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE + "/{memberId}";


    public static final String ACCESS_TOKEN_ROUTE = "/tokens";

    public static final String CREATE_ACCESS_TOKEN_ROUTE = ACCESS_TOKEN_ROUTE;
    public static final String DELETE_ACCESS_TOKEN_ROUTE = ACCESS_TOKEN_ROUTE + "/{tokenId}";


    public static final String INTERNAL_REPOSITORIES_ROUTE = "/internalrepositories";

    public static final String GET_ALL_INTERNAL_REPOSITORIES_ROUTE = INTERNAL_REPOSITORIES_ROUTE;
    public static final String GET_INTERNAL_REPOSITORY_ROUTE = INTERNAL_REPOSITORIES_ROUTE + "/{repositoryId}";
    public static final String CREATE_INTERNAL_REPOSITORY_ROUTE = INTERNAL_REPOSITORIES_ROUTE;
    public static final String UPDATE_INTERNAL_REPOSITORY_ROUTE = INTERNAL_REPOSITORIES_ROUTE + "/{repositoryId}";
    public static final String DELETE_INTERNAL_REPOSITORY_ROUTE = INTERNAL_REPOSITORIES_ROUTE + "/{repositoryId}";


    public static final String INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE = "/internalrepositorygroupmembers";

    public static final String GET_ALL_INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE = INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE;
    public static final String GET_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE = INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String CREATE_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE = INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE;
    public static final String UPDATE_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE = INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String DELETE_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE = INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE + "/{memberId}";


    public static final String INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE = "/internalrepositoryusermembers";

    public static final String GET_ALL_INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE = INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE;
    public static final String GET_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE = INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE + "/{memberId}";
    public static final String CREATE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE = INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE;
    public static final String UPDATE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE = INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE + "/{memberId}";
    public static final String DELETE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE = INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE + "/{memberId}";


    public static final String EXTERNAL_REPOSITORIES_ROUTE = "/externalrepositories";

    public static final String GET_ALL_EXTERNAL_REPOSITORIES_ROUTE = EXTERNAL_REPOSITORIES_ROUTE;
    public static final String GET_EXTERNAL_REPOSITORY_ROUTE = EXTERNAL_REPOSITORIES_ROUTE + "/{repositoryId}";
    public static final String CREATE_EXTERNAL_REPOSITORY_ROUTE = EXTERNAL_REPOSITORIES_ROUTE;
    public static final String UPDATE_EXTERNAL_REPOSITORY_ROUTE = EXTERNAL_REPOSITORIES_ROUTE + "/{repositoryId}";
    public static final String DELETE_EXTERNAL_REPOSITORY_ROUTE = EXTERNAL_REPOSITORIES_ROUTE + "/{repositoryId}";

}
