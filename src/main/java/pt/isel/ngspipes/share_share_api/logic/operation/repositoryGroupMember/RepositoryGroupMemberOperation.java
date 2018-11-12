package pt.isel.ngspipes.share_share_api.logic.operation.repositoryGroupMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isel.ngspipes.share_core.logic.domain.RepositoryGroupMember;
import pt.isel.ngspipes.share_core.logic.service.exceptions.ServiceException;
import pt.isel.ngspipes.share_core.logic.service.repositoryGroupMember.IRepositoryGroupMemberService;

import java.util.Collection;

@Service
public class RepositoryGroupMemberOperation implements IRepositoryGroupMemberOperation {

    @Autowired
    private IRepositoryGroupMemberService memberService;



    @Override
    public Collection<RepositoryGroupMember> getAllMembers() throws ServiceException {
        return memberService.getAll();
    }

    @Override
    public RepositoryGroupMember getMember(int id) throws ServiceException {
        return memberService.getById(id);
    }

    @Override
    public void createMember(RepositoryGroupMember member) throws ServiceException {
        memberService.insert(member);
    }

    @Override
    public void deleteMember(int id) throws ServiceException {
        memberService.delete(id);
    }

    @Override
    public void updateMember(RepositoryGroupMember member) throws ServiceException {
        memberService.update(member);
    }

    @Override
    public Collection<RepositoryGroupMember> getMembersWithGroup(String groupName) throws ServiceException {
        return memberService.getMembersWithGroup(groupName);
    }

    @Override
    public Collection<RepositoryGroupMember> getMembersOfRepository(String repositoryName) throws ServiceException {
        return memberService.getMembersOfRepository(repositoryName);
    }

    @Override
    public void deleteMembersWithGroup(String groupName) throws ServiceException {
        memberService.deleteMembersWithGroup(groupName);
    }

    @Override
    public void deleteMembersOfRepository(String repositoryName) throws ServiceException {
        memberService.deleteMembersOfRepository(repositoryName);
    }

}
