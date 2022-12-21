package com.smartapps.smartuser.jpa.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smartapps.smartlib.converter.TrimConverter;
import com.smartapps.smartlib.entities.CommonEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SMART_USER")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class SmartUser extends CommonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	@Convert(converter = TrimConverter.class)
	private String name;
	
	@Column(name = "PASSWORD")
	@Convert(converter = TrimConverter.class)
	private String password;
	
	@Column(name = "ROLES")
	@Convert(converter = TrimConverter.class)
	private String roles;
	
	@Column(name = "FIRST_NAME")
	@Convert(converter = TrimConverter.class)
	private String firstName;
	
	@Column(name = "MIDDLE_NAME")
	@Convert(converter = TrimConverter.class)
	private String middleName;
	
	@Column(name = "LAST_NAME")
	@Convert(converter = TrimConverter.class)
	private String lastName;
	
	@Column(name = "GENDER")
	@Convert(converter = TrimConverter.class)
	private String gender;

	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "PHONE")
	@Convert(converter = TrimConverter.class)
	private String phone;
	
	@Column(name = "EMAIL")
	@Convert(converter = TrimConverter.class)
	private String email;
	
	@Column(name = "PROFILE_PHOTO_PATH")
	@Convert(converter = TrimConverter.class)
	private String profilePhotoPath;
	
	@Column(name = "ACTIVE")
	private Boolean active;

}
