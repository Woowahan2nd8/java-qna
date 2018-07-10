package codesquad.service;

import codesquad.domain.User;
import codesquad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void checkUserId(String userId) {
        if(userId == null || userId.equals(""))
            throw new CustomException(CustomErrorMessage.INCORRECT_ACCESS);

    }

    public User getUserByUserId(String userId){
        checkUserId(userId);
        Optional<User> targetUser = userRepository.findByUserId(userId);
        if(!targetUser.isPresent())
            throw new CustomException(CustomErrorMessage.NOT_AUTHORIZED);
        return targetUser.get();
    }

    public User convertObjectToUser(Object targetUser){
        if(targetUser == null)
            throw new CustomException(CustomErrorMessage.INCORRECT_ACCESS);

        if(!(targetUser instanceof User))
            throw new CustomException(CustomErrorMessage.INCORRECT_ACCESS);

        return (User)targetUser;
    }

    /*
        user -> 바꾸기 위한 데이터 포함 중
        userId -> 업데이트 시킬 대상 식별자
     */
    public void updateUser(User user, String userId){
        User targetUser = getUserByUserId(userId);
        targetUser.updateData(user);
        userRepository.save(targetUser);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public Iterable<User> getUserList(){
        return userRepository.findAll();
    }



}