package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="disc_table", discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
@Table(name="respuesta_pregunta")
public class RespuestaPregunta
  implements Serializable
{
  private static final long serialVersionUID = 5576729852148369474L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Basic(optional=false)
  @Column(name="id_respuesta_pregunta")
  private Integer idRespuestaPregunta;
  @Column(name="id_participante")
  private Integer idParticipante;
  @Column(name="id_pregunta")
  private Integer idPregunta;
  @Column(name="respuesta_numero")
  private Integer respuestaNumero;
  @Column(name="respuesta_texto")
  private String respuestaTexto;
  @Basic(optional=false)
  @Column(name="esta_contestada")
  private int estaContestada;
  @Column(name="id_cuestionario_empresa")
  private Integer idCuestionarioEmpresa;
  @JoinColumn(name="id_cuestionario_empresa", referencedColumnName="id_cuestionario_empresa", insertable=false, updatable=false)
  @OneToOne(optional=false)
  private CuestionarioEmpresa cuestionarioEmpresa;
  
  public Integer getIdRespuestaPregunta()
  {
    return this.idRespuestaPregunta;
  }
  
  public void setIdRespuestaPregunta(Integer idRespuestaPregunta)
  {
    this.idRespuestaPregunta = idRespuestaPregunta;
  }
  
  public Integer getIdParticipante()
  {
    return this.idParticipante;
  }
  
  public void setIdParticipante(Integer idParticipante)
  {
    this.idParticipante = idParticipante;
  }
  
  public Integer getIdPregunta()
  {
    return this.idPregunta;
  }
  
  public void setIdPregunta(Integer idPregunta)
  {
    this.idPregunta = idPregunta;
  }
  
  public Integer getRespuestaNumero()
  {
    return this.respuestaNumero;
  }
  
  public void setRespuestaNumero(Integer respuestaNumero)
  {
    this.respuestaNumero = respuestaNumero;
  }
  
  public String getRespuestaTexto()
  {
    return this.respuestaTexto;
  }
  
  public void setRespuestaTexto(String respuestaTexto)
  {
    this.respuestaTexto = respuestaTexto;
  }
  
  public int getEstaContestada()
  {
    return this.estaContestada;
  }
  
  public void setEstaContestada(int estaContestada)
  {
    this.estaContestada = estaContestada;
  }
  
  public Integer getIdCuestionarioEmpresa()
  {
    return this.idCuestionarioEmpresa;
  }
  
  public void setIdCuestionarioEmpresa(Integer idCuestionarioEmpresa)
  {
    this.idCuestionarioEmpresa = idCuestionarioEmpresa;
  }
  
  public CuestionarioEmpresa getCuestionarioEmpresa()
  {
    return this.cuestionarioEmpresa;
  }
  
  public void setCuestionarioEmpresa(CuestionarioEmpresa cuestionarioEmpresa)
  {
    this.cuestionarioEmpresa = cuestionarioEmpresa;
  }
  
  public String toString()
  {
    return "RespuestaPregunta [idRespuestaPregunta=" + this.idRespuestaPregunta + ", idParticipante=" + this.idParticipante + ", idPregunta=" + this.idPregunta + ", respuestaNumero=" + this.respuestaNumero + ", respuestaTexto=" + this.respuestaTexto + ", estaContestada=" + this.estaContestada + ", cuestionarioEmpresa=" + this.cuestionarioEmpresa + "]";
  }
}