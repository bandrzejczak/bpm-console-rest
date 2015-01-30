package pl.edu.pw.ii.bpmConsole.interfaces;

import pl.edu.pw.ii.bpmConsole.valueObjects.GroupInfo;
import pl.edu.pw.ii.bpmConsole.valueObjects.UserInfo;

import java.util.List;

public interface UserService {

    List<String> getUserGroups(String username);

    boolean checkPassword(String username, String password);

    void createUser(String login, String password);

    void createGroup(String login);

    void createMembership(String login, String groupName);

    UserRights verifyRights(String id, List<String> groups);

    List<UserInfo> listUsers();

    List<GroupInfo> listGroups();
}
