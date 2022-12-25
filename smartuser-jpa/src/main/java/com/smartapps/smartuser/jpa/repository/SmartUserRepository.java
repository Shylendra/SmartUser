package com.smartapps.smartuser.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartapps.smartuser.jpa.entities.SmartUser;

@Repository
public interface SmartUserRepository extends JpaRepository<SmartUser, Integer> {
	SmartUser findByName(final String userName);
	SmartUser findByNameAndProcApprId(final String userName, final String appId);
	List<SmartUser> findByProcApprId(final String appId);
	boolean existsSmartUserByName(String userName);
}
