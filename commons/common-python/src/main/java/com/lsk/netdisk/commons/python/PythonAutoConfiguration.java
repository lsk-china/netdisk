package com.lsk.netdisk.commons.python;

import com.lsk.netdisk.commons.python.core.PythonComponent;
import org.python.util.PythonInterpreter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PythonAutoConfiguration {
	private final PythonInterpreter pythonInterpreter = new PythonInterpreter();

	@Bean("pythonComponent")
	@ConditionalOnMissingBean(PythonComponent.class)
	public PythonComponent pythonComponent(){
		return new PythonComponent(pythonInterpreter);
	}
}
