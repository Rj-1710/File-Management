package com.mphasis.parent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mphasis.parent.entity.FileLoad;
import com.mphasis.parent.model.dto.SearchCriteriaDto;

import java.util.List;

public interface FileLoadRepository extends JpaRepository<FileLoad, Long> {

	@Query("SELECT f FROM FileLoad f WHERE "
            + "(:#{#searchCriteria.filename} IS NULL OR f.fileName LIKE %:#{#searchCriteria.filename}%) AND "
            + "(:#{#searchCriteria.status} IS NULL OR f.status = :#{#searchCriteria.status})")
    List<FileLoad> findByCriteria(@Param("searchCriteria") SearchCriteriaDto searchCriteria);
}
