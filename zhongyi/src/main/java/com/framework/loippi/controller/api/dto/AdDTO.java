package com.framework.loippi.controller.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.framework.loippi.entity.Ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDTO {
	/** ID */
	private Long id;

	/** 连接 */
	private String url;

	/** 标题 */
	private String title;
	
	/** 图片 */
	private String image;
	
	
	public List<AdDTO> build(List<Ad> ads){
		List<AdDTO> dtos = new ArrayList<AdDTO>();
		for (Ad ad : ads) {
			AdDTO dto = new AdDTO();
			BeanUtils.copyProperties(ad, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
