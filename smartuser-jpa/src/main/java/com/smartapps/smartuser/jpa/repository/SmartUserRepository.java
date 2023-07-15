package com.smartapps.smartuser.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartapps.smartuser.jpa.entities.SmartUser;

@Repository
public interface SmartUserRepository extends JpaRepository<SmartUser, String> {
	List<SmartUser> findAllByOrderByProcTsDesc();
	SmartUser findByName(final String userName);
	SmartUser findByNameAndProcAppId(final String userName, final String appId);
	SmartUser findByIdAndEmailAndActivationTokenAndProcAppId(final String id, final String email, final String activationToken, final String appId);
	List<SmartUser> findByProcAppIdOrderByIdDesc(final String appId);
	List<SmartUser> findByProcAppIdInOrderByIdDesc(final List<String> appIds);
	boolean existsSmartUserByName(String userName);
	boolean existsSmartUserByNameAndProcAppId(final String userName, final String appId);
	void deleteByIdIn(List<String> ids);
	
	@Modifying
	@Query("update SmartUser u set u.userStatus = :status where u.id = :id and u.procAppId = :appId")
	void updateStatus(@Param(value = "id") String id, @Param(value = "appId") String appId, @Param(value = "status") String status);
}
