package com.durgesh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LenovoSmartTab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 50)
	private String model;
	@NotBlank
	@Size(min = 1, max = 2)
	private String ram;
	
	@NotBlank
    @Size(min = 4, max = 1024)
	private String rom;
	@NotBlank
    @Size(min = 10, max = 82)
	private Double size;
	
	@NotBlank
	@Min(value = 500)
    @Max(value = 2000)
    //@Size(min = 500, max = 1024)
	private Integer expandableUpto;
	
	@Min(value = 32)
    @Max(value = 100)
	private String primaryCamera;
	@Min(value = 3000)
    @Max(value = 9000)
	private String battery;
	
	@NotBlank(message = "Please enter a processor")
	private String processor;
	private String tabletGuarantee;
	//private String accessoriesGuarantee;
	
	private String accessoryGuarantee;

}
