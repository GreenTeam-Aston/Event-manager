package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findById(Long id);
    boolean existsUserByLogin(String login);


    @Query(value = "select u.id ,u.login ,u.nickname ,u.password, u.age ,u.gender ,u.user_role , u.contact_id, u.created_at ,u.updated_at  from user_friends uf join users u on u.id = uf.id_friend \n" +
            "where uf.id_user  = :userId",nativeQuery = true)
    List<User> findAllFriends(Long userId);

}
