package com.greenart.jpa_test.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_info")
@DynamicInsert
@DynamicUpdate
@Builder
public class ProductInfoVO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pi_seq")        private Integer seq;
    @Column(name = "pi_name")       private String name;
	@Column(name = "pi_price")      private Integer price; // = 0;
	@Column(name = "pi_discount")   private Double discount; // = 0.0;
	@Column(name = "pi_reg_dt")     private Date regDt; // = new Date();
	@Column(name = "pi_status")     private Integer status; // = 1;
	@Column(name = "pi_stock")      private Integer stock; // = 0;
}
