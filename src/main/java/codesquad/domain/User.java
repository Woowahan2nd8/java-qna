package codesquad.domain;

import codesquad.service.CustomException;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncreasment 사용하는 설정.
    private Long id;

    @Column(length = 30, unique = true, nullable = false, updatable = false)
    private String userId;
    @Column(nullable = false, updatable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 50)
    private String email;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void updateData(User user) {
        if (!matchPassword(user)) {
            throw new CustomException("비밀번호가 일치하지 않습니다.");
        }
        this.name = user.name;
        this.email = user.email;
    }

    public boolean matchPassword(User user) {
        return matchPassword(user.password);
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public boolean matchUserId(User user) {
        return userId.equals(user.userId);
    }

}
