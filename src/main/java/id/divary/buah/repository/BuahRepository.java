package id.divary.buah.repository;

import id.divary.buah.entity.Buah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuahRepository extends JpaRepository<Buah, String> {
    List<Buah> findAllByIsDeletedFalse();
}
