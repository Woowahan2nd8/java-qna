package codesquad.service;

import codesquad.domain.User;

public interface IUserService {
    User getUserByUserId(String userId);
    User convertObjectToUser(Object targetUser);
    void updateUser(User user, String userId);
    Iterable<User> getUserList();
    void saveUser(User user);
}
