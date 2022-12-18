package com.smartapps.smartuser.web.dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.dto.CommonDto;
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
public class SmartUserDto extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String password;
	private String roles;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dob;
	private String phone;
	private String email;
	
	@JsonIgnore
	public Date getSqlDob() {
		if(StringUtils.isNotEmpty(dob)) {
			return SmartDateUtil.parseDate(dob);
		}
		return SmartDateUtil.getCurrentSystemDate();
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
