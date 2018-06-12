package com.ka.boilerplate.datastore.mysql.repo;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ka.boilerplate.datastore.mysql.entity.OrderEntity;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

  List<OrderEntity> findByDateModifiedGreaterThanEqualAndDateModifiedLessThan(Timestamp datestart,
      Timestamp dateModified);
}
