package hn.uth.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long>, JpaSpecificationExecutor<Habitacion> {

}
