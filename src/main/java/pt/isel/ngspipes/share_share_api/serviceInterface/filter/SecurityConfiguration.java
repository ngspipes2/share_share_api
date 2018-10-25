package pt.isel.ngspipes.share_share_api.serviceInterface.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import pt.isel.ngspipes.share_core.logic.domain.User;
import pt.isel.ngspipes.share_share_api.serviceInterface.config.Routes;

import javax.sql.DataSource;

@CrossOrigin
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()

            .antMatchers(HttpMethod.OPTIONS).permitAll()

            .antMatchers(HttpMethod.GET, Routes.GET_ALL_USERS_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_USER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(HttpMethod.POST, Routes.CREATE_USER_ROUTE).permitAll()
            .antMatchers(Routes.UPDATE_USER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_USER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CHANGE_USER_PASSWORD_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_USER_IMAGE_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CHANGE_USER_IMAGE_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_USERS_NAMES_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.GET_ALL_GROUPS_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_GROUP_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CREATE_GROUP_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.UPDATE_GROUP_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_GROUP_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_GROUP_IMAGE_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CHANGE_GROUP_IMAGE_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_GROUPS_NAMES_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.GET_ALL_GROUP_MEMBERS_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CREATE_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.UPDATE_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.CREATE_ACCESS_TOKEN_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_ACCESS_TOKEN_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_ACCESS_TOKENS_OF_USER).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.GET_ALL_INTERNAL_REPOSITORIES_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_INTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CREATE_INTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.UPDATE_INTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_INTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.GET_ALL_INTERNAL_REPOSITORY_GROUP_MEMBERS_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CREATE_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.UPDATE_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_INTERNAL_REPOSITORY_GROUP_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.GET_ALL_INTERNAL_REPOSITORY_USER_MEMBERS_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CREATE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.UPDATE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_INTERNAL_REPOSITORY_USER_MEMBER_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .antMatchers(Routes.GET_ALL_EXTERNAL_REPOSITORIES_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.GET_EXTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.CREATE_EXTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.UPDATE_EXTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())
            .antMatchers(Routes.DELETE_EXTERNAL_REPOSITORY_ROUTE).hasAnyRole(User.Role.NORMAL.toString(), User.Role.ADMIN.toString())

            .and().httpBasic()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, true from _user where username=?")
            .authoritiesByUsernameQuery("select username, concat('ROLE_', role) from _user where username=?")
            .passwordEncoder(passwordEncoder);
    }

}
