package br.com.alura.AluraFake.user;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndRole(String email, Role role);
    User findByEmail(String email);
}
