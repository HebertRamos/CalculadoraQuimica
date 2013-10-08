package ufms.calculadora.extensoes.calculoEstequiometrico;

public class FabricaVariavelEstequiometrica {

	
	public static VariavelEstequiometrica criaVariavel(EnumMedidaGrandeza grandeza){
		if(grandeza.equals(EnumMedidaGrandeza.MOL)){
			return new Mol();
		}
		return null;
	}
}
