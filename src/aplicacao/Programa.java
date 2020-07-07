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
		int horas, mes, ano;
		ContratoPorHora contrato;

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o nome do funcion�rio: ");
			String nome = scanner.next();
			System.out.print("Digite o n�vel do funcion�rio: ");
			NivelFuncionario nivel = NivelFuncionario.valueOf(scanner.next());
			System.out.print("Digite o sal�rio do funcion�rio (R$):");
			Double salario = scanner.nextDouble();
			System.out.print("Digite o nome do departamento do funcion�rio: ");
			Departamento departamento = new Departamento(scanner.next());

			Funcionario funcionario = new Funcionario(nome, nivel, salario, departamento);
			System.out.print("Digite quantos contratos o funcion�rio possui: ");
			int qtdContratos = scanner.nextInt();

			for (int i = 1; i <= qtdContratos; i++) {
				System.out.print("Digite a data (DD/MM/AAAA) do contrato " + i + ": ");
				data = formatadorData.parse(scanner.next());
				System.out.print("Digite o valor-hora do contrato #" + i + " (R$): ");
				valorHora = scanner.nextDouble();
				System.out.print("Digite o total de horas do contrato " + i + ": ");
				horas = scanner.nextInt();

				contrato = new ContratoPorHora(data, valorHora, horas);
				funcionario.adicionarContrato(contrato);
			}

			System.out.print("Digite o m�s: ");
			mes = scanner.nextInt();
			System.out.print("Digite o ano: ");
			ano = scanner.nextInt();
			scanner.close();

			System.out.println("--------------------------------------------------------");
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("N�vel: " + funcionario.getNivel());
			System.out.println("N�vel: " + funcionario.getSalario());
			System.out.println("Departamento: " + funcionario.getDepartamento().getNome());
			System.out.println("Contratos:");

			int i = 1;
			for (ContratoPorHora meuContrato : funcionario.getContratos()) {
				System.out.println(i + ":" + meuContrato.getData() + " - R$ " + meuContrato.getValorPorHora()
						+ "/hora - " + meuContrato.getHoras() + " horas");
				i++;
			}

			System.out.println("Remunera��o no m�s " + mes + "/" + ano + ": R$ " + funcionario.calcularRemuneracao(mes, ano));
		} catch (IllegalArgumentException e) {
			System.out.println("ERRO: voc� digitou um tipo de N�VEL inv�lido. Programa terminado.");
		} catch (InputMismatchException e) {
			System.out.println("ERRO: voc� digitou um tipo de dado inv�lido para este campo. Programa terminado.");
		}
	}

}
