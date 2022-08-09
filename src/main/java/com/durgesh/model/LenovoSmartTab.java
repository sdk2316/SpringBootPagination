package com.durgesh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LenovoSmartTab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String model;
	private String ram;
	private String rom;
	private Double size;
	private Integer expandableUpto;
	private String primaryCamera;
	private String battery;
	private String processor;
	private String tabletGuarantee;
	//private String accessoriesGuarantee;
	
	private String accessoryGuarantee;

}
