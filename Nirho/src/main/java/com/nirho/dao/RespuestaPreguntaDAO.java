package com.nirho.dao;

import com.nirho.model.RespuestaPregunta;
import com.nirho.model.RespuestaPreguntaIRH;
import java.util.List;

public abstract interface RespuestaPreguntaDAO
  extends BaseDAO<RespuestaPregunta, Integer>
{
  public abstract void agregarEditarRespuestasCuestionarioActivoEmpresa(List<RespuestaPreguntaIRH> paramList);
  
  public abstract void agregarEditarRespuestasCuestionarioActivoEmpresa(RespuestaPreguntaIRH param);

  public abstract List<RespuestaPreguntaIRH> consultarRespuestasCuestionarioIRH(Integer paramInteger);
}
