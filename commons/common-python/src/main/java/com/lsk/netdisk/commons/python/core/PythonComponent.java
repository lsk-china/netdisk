package com.lsk.netdisk.commons.python.core;

import lombok.RequiredArgsConstructor;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class PythonComponent {
	private final PythonInterpreter interpreter;

	public void runPythonCode(String code){
		interpreter.exec(code);
	}

	public void runPythonFile(String file){
		interpreter.execfile(file);
	}

	public String runPythonFunction(String name, PyObject... params){
		PyFunction function = interpreter.get(name, PyFunction.class);
		PyObject object = function.__call__(params);
		return object.asString();
	}

}
