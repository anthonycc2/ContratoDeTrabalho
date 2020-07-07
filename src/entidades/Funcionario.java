package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import entidades.enums.NivelFuncionario;

public class Funcionario {
	private String nome;
	private NivelFuncionario nivel;
	private Double salario;
	private Departamento departamento;
	private ArrayList<ContratoPorHora> contratos;

	public Funcionario() {
	}

	public Funcionario(String name, NivelFuncionario nivel, Double salario, Departamento departamento) {
		this.nome = name;
		this.nivel = nivel;
		this.salario = salario;
		this.departamento = departamento;
		this.contratos = new ArrayList<ContratoPorHora>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public NivelFuncionario getNivel() {
		return nivel;
	}

	public void setNivel(NivelFuncionario nivel) {
		this.nivel = nivel;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public ArrayList<ContratoPorHora> getContratos() {
		return contratos;
	}

	public void adicionarContrato(ContratoPorHora contrato) {
		this.contratos.add(contrato);
	}

	public void removerContrato(ContratoPorHora contrato) {
		this.contratos.remove(contrato);
	}

	public Double calcularRemuneracao(int mes, int ano) {
		Double remuneracao = salario;
		int mesContrato;
		int anoContrato;
		Calendar calendario = Calendar.getInstance();

		for (ContratoPorHora contrato : contratos) {
			calendario.setTime(contrato.getData());
			mesContrato = calendario.get(Calendar.MONTH) + 1; // 0 = janeiro; 1 = fevereiro; ...
			anoContrato = calendario.get(Calendar.YEAR);
			
			if (mesContrato == mes && anoContrato == ano) {
				remuneracao += contrato.calcularValorTotal();
			}
		}

		return remuneracao;
	}
}