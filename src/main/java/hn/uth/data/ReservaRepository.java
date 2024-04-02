package hn.uth.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReservaRepository
        extends
            JpaRepository<Reserva, Long>,
            JpaSpecificationExecutor<Reserva> {

}
