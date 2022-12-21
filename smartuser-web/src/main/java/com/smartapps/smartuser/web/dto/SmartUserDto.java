package com.smartapps.smartuser.web.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SmartDateUtil;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SmartUser")
public class SmartUserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;

	@JsonIgnore
	private String password;
	
	private String roles;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dob;
	private String phone;
	private String email;
	private String profilePhotoPath;
	private Boolean active;
	
	@JsonIgnore
	private String procTs;
	
	@JsonIgnore
	private String procApprId;
	
	@JsonIgnore
	private String procUserId;
	
	@JsonIgnore
	private String procUserIpAddress;
	
	@JsonIgnore
	private String procUserLatitude;

	@JsonIgnore
	private String procUserLongitude;
	
	@JsonIgnore
	private long version;
	
	@JsonIgnore
	public Date getSqlDob() {
		if(StringUtils.isNotEmpty(dob)) {
			return SmartDateUtil.parseDate(dob);
		}
		return SmartDateUtil.getCurrentSystemDate();
	}
	
	@JsonIgnore
	public Timestamp getSqlProcTs() {
		if(StringUtils.isNotEmpty(procTs)) {
			return SmartDateUtil.parseTimestamp(procTs);
		}
		return SmartDateUtil.getCurrentSystemTimestamp();
	}

	@Override
	public String toString() {
		try {
			return SmartLibraryUtil.mapToString(this, true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
