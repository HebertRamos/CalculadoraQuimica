package ufms.calculadora.extensoes.calculoEstequiometrico;

public class Mol implements VariavelEstequiometrica {
	
	private Double valor;
	public static final EnumMedidaGrandeza grandeza = EnumMedidaGrandeza.MOL;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public EnumMedidaGrandeza getGrandeza() {
		return grandeza;
	}
}
