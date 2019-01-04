package com.nirho.dto;

import java.io.Serializable;

public class RespEval360  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer respuesta;
	private Integer autoEval;
	public RespEval360() {
		
	}
	public RespEval360(Integer respuesta, Integer autoEval) {
		this.respuesta = respuesta;
		this.autoEval = autoEval;
	}

	public Integer getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Integer respuesta) {
		this.respuesta = respuesta;
	}
	public Integer getAutoEval() {
		return autoEval;
	}
	public void setAutoEval(Integer autoEval) {
		this.autoEval = autoEval;
	}
	@Override
	public String toString() {
		return "RespEval360 [respuesta=" + respuesta + ", autoEval=" + autoEval + "]";
	}
}
