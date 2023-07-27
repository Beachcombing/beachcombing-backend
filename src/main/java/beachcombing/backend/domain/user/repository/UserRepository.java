package beachcombing.backend.domain.user.repository;

import beachcombing.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
