package uz.pdp.lesson6_7role_and_permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson6_7role_and_permission.entity.Lavozim;

import java.util.Optional;

public interface LavozimRepository extends JpaRepository<Lavozim, Long> {

    Optional<Lavozim> findByName(String name);
    boolean existsByName(String name);
}
