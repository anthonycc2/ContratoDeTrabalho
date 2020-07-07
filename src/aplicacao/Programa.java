package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;
import entidades.*;
import entidades.enums.NivelFuncionario;

public class Programa {

	public static void main(String[] args) throws ParseException, IllegalArgumentException, InputMismatchException {
		Locale.setDefault(Locale.US);

		Date data;
		SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
		Double valorHora;
		int opcao, horas, mes, ano;
		NivelFuncionario nivel;
		ContratoPorHora contrato;

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o nome do funcionário: ");
			String nome = scanner.next();
			System.out.println("Níveis:");
			System.out.println("1 - JUNIOR (padrão)");
			System.out.println("2 - PLENO");
			System.out.println("3 - SENIOR");
			System.out.print("Digite o nível do funcionário: ");
			opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				nivel = NivelFuncionario.JUNIOR;
				break;
			case 2:
				nivel = NivelFuncionario.JUNIOR;
				break;
			case 3:
				nivel = NivelFuncionario.SENIOR;
				break;
			default:
				nivel = NivelFuncionario.JUNIOR;
				break;
			}
			System.out.print("Digite o salário do funcionário (R$):");
			Double salario = scanner.nextDouble();
			System.out.print("Digite o nome do departamento do funcionário: ");
			Departamento departamento = new Departamento(scanner.next());

			Funcionario funcionario = new Funcionario(nome, nivel, salario, departamento);
			System.out.print("Digite quantos contratos o funcionário possui: ");
			int qtdContratos = scanner.nextInt();

			for (int i = 1; i <= qtdContratos; i++) {
				System.out.print("Digite a data (DD/MM/AAAA) do contrato #" + i + ": ");
				data = formatadorData.parse(scanner.next());
				System.out.print("Digite o valor-hora do contrato #" + i + " (R$): ");
				valorHora = scanner.nextDouble();
				System.out.print("Digite o total de horas do contrato #" + i + ": ");
				horas = scanner.nextInt();

				contrato = new ContratoPorHora(data, valorHora, horas);
				funcionario.adicionarContrato(contrato);
			}

			System.out.print("Digite o mês: ");
			mes = scanner.nextInt();
			System.out.print("Digite o ano: ");
			ano = scanner.nextInt();
			scanner.close();

			System.out.println("--------------------------------------------------------");
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Nível: " + funcionario.getNivel());
			System.out.println("Nível: " + funcionario.getSalario());
			System.out.println("Departamento: " + funcionario.getDepartamento().getNome());
			System.out.println("Contratos:");
			System.out.println("Data - Valor-hora - Total de horas - Valor total");

			int i = 1;
			for (ContratoPorHora meuContrato : funcionario.getContratos()) {
				System.out.println(i + ": " + meuContrato.getData() + " - R$ " + meuContrato.getValorPorHora()
						+ "/hora - " + meuContrato.getHoras() + " horas" + " - R$ " + meuContrato.calcularValorTotal());
				i++;
			}

			System.out.println("Remuneração no mês " + mes + "/" + ano + ": R$ " + funcionario.calcularRemuneracao(mes, ano));
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: você digitou um tipo de NÍVEL inválido. Programa terminado.");
		} catch (InputMismatchException e) {
			System.out.println("Erro: você digitou um tipo de dado inválido para este campo. Programa terminado.");
		}
	}

}
