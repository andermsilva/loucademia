package br.com.softblue.loucademia.domain.acesso;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import br.com.softblue.loucademia.domain.aluno.Aluno;

@Entity
@Table(name = "ENTRADAS_SAIDAS")
public class Acesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id" ,nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id",nullable = false)
	private Aluno aluno;
	
	@Column(name = "entrada", nullable = false)
	private LocalDateTime entrada;
	
	@Column(name = "saida", nullable = true)
	private LocalDateTime saida;

	
	
	public boolean isEntradaSaidaPreechidas(){
		
		if(entrada != null && saida != null) {
			
		    return true;
		}
		
		return false;
	}
	
	
	
	public TipoAcesso registraAcesso() {
		
		LocalDateTime now = LocalDateTime.now();
		
		TipoAcesso tipoAcesso;
		
		if(entrada == null) {
			entrada = now;
			tipoAcesso = TipoAcesso.ENTRADA;
		
		}else if(saida == null) {
			saida = now;
			tipoAcesso = TipoAcesso.SAIDA;
			
		}else {
			tipoAcesso = null;
		}
		
		return tipoAcesso;
	}
	
	
	public String calcularDuracao() {
		if(entrada == null || saida == null) {
			return null;
			
		}
		
		 Duration d = Duration.between(entrada,saida);
		 
		 return String.format("%02d:%02d", d.toHoursPart(), d.toMinutesPart());
		 
		 
				
		 
		 
	//	System.out.println(duracao);
		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}
	
	
	

	@Override
	public String toString() {
		return "Acesso [id=" + id + ", aluno=" + aluno + ", entrada=" + entrada + ", saida=" + saida + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}