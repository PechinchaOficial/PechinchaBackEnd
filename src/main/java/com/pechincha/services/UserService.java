package com.pechincha.services;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.TimeZone;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pechincha.security.UserSS;


public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		} catch (Exception e) {
			return null;
		}
	}

	public static Date agora() {
		Date dataAtual = null;
		try {
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
			TimeZone.setDefault(tz);
			Calendar ca = GregorianCalendar.getInstance(tz);
			dataAtual = ca.getTime();

		} catch (Exception e) {
			return null;
		}
		return dataAtual;

	}
		
	
	public static String validar(String documento) {
		if(UserService.isCPF(documento)) {
			return UserService.imprimeCPF(UserService.removeCaracteresEspeciais(documento));
		}else if (UserService.isCNPJ(documento)) {
			return UserService.imprimeCNPJ(UserService.removeCaracteresEspeciais(documento));
		}else {
			return documento;
		}
	
	}

	public static boolean isCPF(String CPF) {
		
		CPF = UserService.removeCaracteresEspeciais(CPF);
		
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0        
				// (48 eh a posicao de '0' na tabela ASCII)        
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static boolean isCNPJ(String CNPJ) {
		
		CNPJ = removeCaracteresEspeciais(CNPJ);
		
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") || (CNPJ.length() != 14))
			return (false);

		char dig13, dig14;
		int sm, i, r, num, peso;

		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				// converte o i-ésimo caractere do CNPJ em um número:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posição de '0' na tabela ASCII)
				num = (int) (CNPJ.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (CNPJ.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);

			// Verifica se os dígitos calculados conferem com os dígitos informados.
			if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
	
	private static String removeCaracteresEspeciais(String doc) {
		if (doc.contains(".")) {
			doc = doc.replace(".", "");
		}
		if (doc.contains("-")) {
			doc = doc.replace("-", "");
		}
		if (doc.contains("/")) {
			doc = doc.replace("/", "");
		}
		return doc.trim();
	}
	
	public static String imprimeCPF(String CPF) {
		String cpfCompleto = "";
		if (CPF.length() < 11) {
			String zEsquerda = "";
			for (int i = 0; i < (11 - CPF.length()); i++) {
				zEsquerda = zEsquerda + "0";
			}
			cpfCompleto = zEsquerda + CPF;
		} else {
			cpfCompleto = CPF;
		}
		String bloco1 = cpfCompleto.substring(0, 3);
		String bloco2 = cpfCompleto.substring(3, 6);
		String bloco3 = cpfCompleto.substring(6, 9);
		String bloco4 = cpfCompleto.substring(9, 11);

		cpfCompleto = bloco1 + "." + bloco2 + "." + bloco3 + "-" + bloco4;
		return cpfCompleto;
	}

	public static String imprimeCNPJ(String CNPJ) {
		// máscara do CNPJ: 99.999.999.9999-99
		String cnpjCompleto = "";
		if (CNPJ.length() < 14) {
			String zEsquerda = "";
			for (int i = 0; i < (14 - CNPJ.length()); i++) {
				zEsquerda = zEsquerda + "0";
			}
			cnpjCompleto = zEsquerda + CNPJ;
		} else {
			cnpjCompleto = CNPJ;
		}
		String bloco1 = cnpjCompleto.substring(0, 2);
		String bloco2 = cnpjCompleto.substring(2, 5);
		String bloco3 = cnpjCompleto.substring(5, 8);
		String bloco4 = cnpjCompleto.substring(8, 12);
		String bloco5 = cnpjCompleto.substring(12, 14);

		cnpjCompleto = bloco1 + "." + bloco2 + "." + bloco3 + "." + bloco4 + "-" + bloco5;
		return cnpjCompleto;
	}
	
	public static long betweenDates(Date firstDate, Date secondDate) {
		long dias = 100;
		try {
			dias = ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
		} catch (Exception ex) {

		}
		return dias;
	}
}
