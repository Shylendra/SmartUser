package com.smartapps.smartuser.jpa.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.smartapps.smartlib.config.SmartSequenceIdGenerator;
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
	
	private static final String SEQUENCE_NAME = "smartuser_seq";
	
	private static final String SEQUENCE_ID_PREFIX = "SUN";
	
	private static final String SEQUENCE_STRATEGY = "com.smartapps.smartlib.config.SmartSequenceIdGenerator";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @GenericGenerator(
            name = SEQUENCE_NAME, 
            strategy = SEQUENCE_STRATEGY, 
            parameters = {
                @Parameter(name = SmartSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                @Parameter(name = SmartSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = SEQUENCE_ID_PREFIX),
                @Parameter(name = SmartSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
    private String id;

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

	@Column(name = "ABOUT")
	@Convert(converter = TrimConverter.class)
	private String about;

	@Column(name = "COMPANY")
	@Convert(converter = TrimConverter.class)
	private String company;

	@Column(name = "JOB")
	@Convert(converter = TrimConverter.class)
	private String job;

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

	@Column(name = "TWITTER_URL")
	@Convert(converter = TrimConverter.class)
	private String twitterUrl;

	@Column(name = "FACEBOOK_URL")
	@Convert(converter = TrimConverter.class)
	private String facebookUrl;

	@Column(name = "INSTAGRAM_URL")
	@Convert(converter = TrimConverter.class)
	private String instagramUrl;

	@Column(name = "LINKEDIN_URL")
	@Convert(converter = TrimConverter.class)
	private String linkedInUrl;

	@PrePersist
	public void control() {
		if (StringUtils.isEmpty(about)) {
			about = "All about me here...";
		}
		if (StringUtils.isEmpty(company)) {
			company = "International Inc Pty Ltd";
		}
		if (StringUtils.isEmpty(job)) {
			job = "Senior Technical Consultant";
		}
		if (StringUtils.isEmpty(gender)) {
			gender = "MALE";
		}
		if (StringUtils.isEmpty(phone)) {
			phone = "000111222";
		}
		if (StringUtils.isEmpty(profilePhotoPath)) {
			profilePhotoPath = "assets/img/profile-img2.jpg";
		}
		if (StringUtils.isEmpty(twitterUrl)) {
			twitterUrl = "https://twitter.com/#";
		}
		if (StringUtils.isEmpty(facebookUrl)) {
			facebookUrl = "https://facebook.com/#";
		}
		if (StringUtils.isEmpty(instagramUrl)) {
			instagramUrl = "https://instagram.com/#";
		}
		if (StringUtils.isEmpty(linkedInUrl)) {
			linkedInUrl = "https://linkedin.com/#";
		}
	}
}
