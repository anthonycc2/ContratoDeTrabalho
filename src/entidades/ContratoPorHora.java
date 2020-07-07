package entidades;

import java.util.Date;

public class ContratoPorHora {
	private Date data;
	private Double valorHora;
	private int horas;
	
	public ContratoPorHora(Date data, Double valorPorHora, int horas) {
		this.data = data;
		this.valorHora = valorPorHora;
		this.horas = horas;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Double getValorPorHora() {
		return valorHora;
	}
	
	public void setValorPorHora(Double valorPorHora) {
		this.valorHora = valorPorHora;
	}
	
	public int getHoras() {
		return horas;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public Double calcularValorTotal() {
		return valorHora * horas;
	}
}
