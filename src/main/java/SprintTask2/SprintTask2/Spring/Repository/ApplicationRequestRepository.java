package SprintTask2.SprintTask2.Spring.Repository;

import SprintTask2.SprintTask2.Spring.Models.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.*;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {

    @Query("SELECT o FROM ApplicationRequest o where o.handled = false")
    List<ApplicationRequest> searchApplicationRequest();

    @Query("SELECT a FROM  ApplicationRequest a ORDER BY a.handled")
    List<ApplicationRequest> sortByHandle();

    List<ApplicationRequest> findAllByHandledIsTrue();
}
