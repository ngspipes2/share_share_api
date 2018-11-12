package pt.isel.ngspipes.share_share_api.serviceInterface.config;

public class Routes {

    public static final String LOGIN_ROUTE = "/login";


    public static final String USERS_ROUTE = "/users";

    public static final String GET_ALL_USERS_ROUTE = USERS_ROUTE;
    public static final String GET_USER_ROUTE = USERS_ROUTE + "/{userName}";
    public static final String CREATE_USER_ROUTE = USERS_ROUTE;
    public static final String UPDATE_USER_ROUTE = USERS_ROUTE + "/{userName}";
    public static final String DELETE_USER_ROUTE = USERS_ROUTE + "/{userName}";
    public static final String GET_USER_IMAGE_ROUTE = USERS_ROUTE + "/{userName}/image";
    public static final String CHANGE_USER_IMAGE_ROUTE = USERS_ROUTE + "/{userName}/image";
    public static final String DELETE_USER_IMAGE_ROUTE = USERS_ROUTE + "/{userName}/image";
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
    public static final String DELETE_GROUP_IMAGE_ROUTE = GROUPS_ROUTE + "/{groupName}/image";
    public static final String GET_GROUPS_NAMES_ROUTE = "/groupsnames";


    public static final String GROUP_MEMBERS_ROUTE = "/groupmembers";

    public static final String GET_ALL_GROUP_MEMBERS_ROUTE = GROUP_MEMBERS_ROUTE;
    public static final String GET_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String CREATE_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE;
    public static final String UPDATE_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String DELETE_GROUP_MEMBER_ROUTE = GROUP_MEMBERS_ROUTE + "/{memberId}";


    public static final String ACCESS_TOKEN_ROUTE = "/tokens";

    public static final String GET_ACCESS_TOKEN_ROUTE = ACCESS_TOKEN_ROUTE + "/{tokenId}";
    public static final String CREATE_ACCESS_TOKEN_ROUTE = ACCESS_TOKEN_ROUTE;
    public static final String UPDATE_ACCESS_TOKEN_ROUTE = ACCESS_TOKEN_ROUTE + "/{tokenId}";
    public static final String DELETE_ACCESS_TOKEN_ROUTE = ACCESS_TOKEN_ROUTE + "/{tokenId}";
    public static final String GET_ACCESS_TOKENS_OF_USER = ACCESS_TOKEN_ROUTE;


    public static final String REPOSITORIES_ROUTE = "/repositories";

    public static final String GET_ALL_REPOSITORIES_ROUTE = REPOSITORIES_ROUTE;
    public static final String GET_REPOSITORY_ROUTE = REPOSITORIES_ROUTE + "/{repositoryName}";
    public static final String CREATE_REPOSITORY_ROUTE = REPOSITORIES_ROUTE;
    public static final String UPDATE_REPOSITORY_ROUTE = REPOSITORIES_ROUTE + "/{repositoryName}";
    public static final String DELETE_REPOSITORY_ROUTE = REPOSITORIES_ROUTE + "/{repositoryName}";
    public static final String GET_REPOSITORIES_NAMES_ROUTE = "/repositoriesnames";


    public static final String REPOSITORY_GROUP_MEMBERS_ROUTE = "/repositorygroupmembers";

    public static final String GET_ALL_REPOSITORY_GROUP_MEMBERS_ROUTE = REPOSITORY_GROUP_MEMBERS_ROUTE;
    public static final String GET_REPOSITORY_GROUP_MEMBER_ROUTE = REPOSITORY_GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String CREATE_REPOSITORY_GROUP_MEMBER_ROUTE = REPOSITORY_GROUP_MEMBERS_ROUTE;
    public static final String UPDATE_REPOSITORY_GROUP_MEMBER_ROUTE = REPOSITORY_GROUP_MEMBERS_ROUTE + "/{memberId}";
    public static final String DELETE_REPOSITORY_GROUP_MEMBER_ROUTE = REPOSITORY_GROUP_MEMBERS_ROUTE + "/{memberId}";


    public static final String REPOSITORY_USER_MEMBERS_ROUTE = "/repositoryusermembers";

    public static final String GET_ALL_REPOSITORY_USER_MEMBERS_ROUTE = REPOSITORY_USER_MEMBERS_ROUTE;
    public static final String GET_REPOSITORY_USER_MEMBER_ROUTE = REPOSITORY_USER_MEMBERS_ROUTE + "/{memberId}";
    public static final String CREATE_REPOSITORY_USER_MEMBER_ROUTE = REPOSITORY_USER_MEMBERS_ROUTE;
    public static final String UPDATE_REPOSITORY_USER_MEMBER_ROUTE = REPOSITORY_USER_MEMBERS_ROUTE + "/{memberId}";
    public static final String DELETE_REPOSITORY_USER_MEMBER_ROUTE = REPOSITORY_USER_MEMBERS_ROUTE + "/{memberId}";

}
