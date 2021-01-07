package com.lsk.netdisk.common.response.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("response")
public class Properies {
	private String packageName;
}
