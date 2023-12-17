package com.pankiba.onetoonesharedpk.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = { "employee" })
@NoArgsConstructor
@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = -8132793126873784036L;

	@Id
	private Long addressId;

	private String street;

	private String city;

	private String state;

	/**
	 * The @MapsId annotation tells Hibernate/JPA to use the primary key value of
	 * the Employee entity as the primary key value of the EmployeeDetails entity.
	 *
	 * @JoinColumn defines foreign key column and indicates the owner of the
	 *             relationship.
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "EMPLOYEE_ADDRESS_ID")
	@JsonIgnore
	private Employee employee;

	public Address(String street, String city, String state) {
		this.street = street;
		this.city = city;
		this.state = state;
	}

}
