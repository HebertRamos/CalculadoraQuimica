package ufms.calculadora.extensoes.recursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ufms.calculadora.modelo.Elemento;
import ufms.calculadora.modelo.EnumSiglaElemento;

/**
 * 
 * @author Hebert Ramos
 * 
 * Classe estática que faz a instanciação de todos os elementos da tabela periódica.
 */
public class TabelaPeriodica {
	
	private static List<Elemento> elementos;
	private static Map<EnumSiglaElemento, Elemento> mapaElementos;
	

	public static List<Elemento> getElementos(){
		
		if(elementos == null){
			montaElementos();
		}
		return elementos;
	}
	
	public static Elemento getElemento(EnumSiglaElemento sigla){
				
		if(mapaElementos == null){
			montaMapaElementos();
		}
		
		return mapaElementos.get(sigla);
		
	}
	
	private static void montaMapaElementos(){
		
		if(elementos == null){
			montaElementos();
		}
		
		mapaElementos = new HashMap<EnumSiglaElemento, Elemento>();
		for (Elemento elemento : elementos) {
			mapaElementos.put(elemento.getSigla(), elemento);
		}
	}
	
	
	private static void montaElementos(){
		
		elementos = new ArrayList<Elemento>();
		
		Elemento elemento1 = new Elemento();
		elemento1.setNumeroAtomico(1);
		elemento1.setSigla(EnumSiglaElemento.valueOf("H"));
		elemento1.setNome("Hidrogênio");
		elemento1.setMassaAtomica(1.00794);
		elementos.add(elemento1);
		
		Elemento elemento2 = new Elemento();
		elemento2.setNumeroAtomico(2);
		elemento2.setSigla(EnumSiglaElemento.valueOf("He"));
		elemento2.setNome("Hélio");
		elemento2.setMassaAtomica(4.002602);
		elementos.add(elemento2);

		Elemento elemento3 = new Elemento();
		elemento3.setNumeroAtomico(3);
		elemento3.setSigla(EnumSiglaElemento.valueOf("Li"));
		elemento3.setNome("Lítio");
		elemento3.setMassaAtomica(6.941);
		elementos.add(elemento3);

		Elemento elemento4 = new Elemento();
		elemento4.setNumeroAtomico(4);
		elemento4.setSigla(EnumSiglaElemento.valueOf("Be"));
		elemento4.setNome("Berílio");
		elemento4.setMassaAtomica(9.012182);
		elementos.add(elemento4);

		Elemento elemento5 = new Elemento();
		elemento5.setNumeroAtomico(5);
		elemento5.setSigla(EnumSiglaElemento.valueOf("B"));
		elemento5.setNome("Boro");
		elemento5.setMassaAtomica(10.811);
		elementos.add(elemento5);

		Elemento elemento6 = new Elemento();
		elemento6.setNumeroAtomico(6);
		elemento6.setSigla(EnumSiglaElemento.valueOf("C"));
		elemento6.setNome("Carbono");
		elemento6.setMassaAtomica(12.011);
		elementos.add(elemento6);

		Elemento elemento7 = new Elemento();
		elemento7.setNumeroAtomico(7);
		elemento7.setSigla(EnumSiglaElemento.valueOf("N"));
		elemento7.setNome("Nitrogênio");
		elemento7.setMassaAtomica(14.00674);
		elementos.add(elemento7);

		Elemento elemento8 = new Elemento();
		elemento8.setNumeroAtomico(8);
		elemento8.setSigla(EnumSiglaElemento.valueOf("O"));
		elemento8.setNome("Oxigênio");
		elemento8.setMassaAtomica(15.9994);
		elementos.add(elemento8);

		Elemento elemento9 = new Elemento();
		elemento9.setNumeroAtomico(9);
		elemento9.setSigla(EnumSiglaElemento.valueOf("F"));
		elemento9.setNome("Flúor");
		elemento9.setMassaAtomica(18.9984032);
		elementos.add(elemento9);

		Elemento elemento10 = new Elemento();
		elemento10.setNumeroAtomico(10);
		elemento10.setSigla(EnumSiglaElemento.valueOf("Ne"));
		elemento10.setNome("Néon");
		elemento10.setMassaAtomica(20.1797);
		elementos.add(elemento10);

		Elemento elemento11 = new Elemento();
		elemento11.setNumeroAtomico(11);
		elemento11.setSigla(EnumSiglaElemento.valueOf("Na"));
		elemento11.setNome("Sódio");
		elemento11.setMassaAtomica(22.989768);
		elementos.add(elemento11);

		Elemento elemento12 = new Elemento();
		elemento12.setNumeroAtomico(12);
		elemento12.setSigla(EnumSiglaElemento.valueOf("Mg"));
		elemento12.setNome("Magnésio");
		elemento12.setMassaAtomica(24.305);
		elementos.add(elemento12);

		Elemento elemento13 = new Elemento();
		elemento13.setNumeroAtomico(13);
		elemento13.setSigla(EnumSiglaElemento.valueOf("Al"));
		elemento13.setNome("Alumínio");
		elemento13.setMassaAtomica(26.981539);
		elementos.add(elemento13);

		Elemento elemento14 = new Elemento();
		elemento14.setNumeroAtomico(14);
		elemento14.setSigla(EnumSiglaElemento.valueOf("Si"));
		elemento14.setNome("Silício");
		elemento14.setMassaAtomica(28.0855);
		elementos.add(elemento14);

		Elemento elemento15 = new Elemento();
		elemento15.setNumeroAtomico(15);
		elemento15.setSigla(EnumSiglaElemento.valueOf("P"));
		elemento15.setNome("Fósforo");
		elemento15.setMassaAtomica(30.973762);
		elementos.add(elemento15);

		Elemento elemento16 = new Elemento();
		elemento16.setNumeroAtomico(16);
		elemento16.setSigla(EnumSiglaElemento.valueOf("S"));
		elemento16.setNome("Enxofre");
		elemento16.setMassaAtomica(32.066);
		elementos.add(elemento16);

		Elemento elemento17 = new Elemento();
		elemento17.setNumeroAtomico(17);
		elemento17.setSigla(EnumSiglaElemento.valueOf("Cl"));
		elemento17.setNome("Cloro");
		elemento17.setMassaAtomica(35.4527);
		elementos.add(elemento17);

		Elemento elemento18 = new Elemento();
		elemento18.setNumeroAtomico(18);
		elemento18.setSigla(EnumSiglaElemento.valueOf("Ar"));
		elemento18.setNome("Árgon");
		elemento18.setMassaAtomica(39.948);
		elementos.add(elemento18);

		Elemento elemento19 = new Elemento();
		elemento19.setNumeroAtomico(19);
		elemento19.setSigla(EnumSiglaElemento.valueOf("K"));
		elemento19.setNome("Potássio");
		elemento19.setMassaAtomica(39.0983);
		elementos.add(elemento19);

		Elemento elemento20 = new Elemento();
		elemento20.setNumeroAtomico(20);
		elemento20.setSigla(EnumSiglaElemento.valueOf("Ca"));
		elemento20.setNome("Cálcio");
		elemento20.setMassaAtomica(40.078);
		elementos.add(elemento20);

		Elemento elemento21 = new Elemento();
		elemento21.setNumeroAtomico(21);
		elemento21.setSigla(EnumSiglaElemento.valueOf("Sc"));
		elemento21.setNome("Escândio");
		elemento21.setMassaAtomica(44.95591);
		elementos.add(elemento21);

		Elemento elemento22 = new Elemento();
		elemento22.setNumeroAtomico(22);
		elemento22.setSigla(EnumSiglaElemento.valueOf("Ti"));
		elemento22.setNome("Titânio");
		elemento22.setMassaAtomica(47.88);
		elementos.add(elemento22);

		Elemento elemento23 = new Elemento();
		elemento23.setNumeroAtomico(23);
		elemento23.setSigla(EnumSiglaElemento.valueOf("V"));
		elemento23.setNome("Vanádio");
		elemento23.setMassaAtomica(50.9415);
		elementos.add(elemento23);

		Elemento elemento24 = new Elemento();
		elemento24.setNumeroAtomico(24);
		elemento24.setSigla(EnumSiglaElemento.valueOf("Cr"));
		elemento24.setNome("Cromo");
		elemento24.setMassaAtomica(51.9961);
		elementos.add(elemento24);

		Elemento elemento25 = new Elemento();
		elemento25.setNumeroAtomico(25);
		elemento25.setSigla(EnumSiglaElemento.valueOf("Mn"));
		elemento25.setNome("Manganês");
		elemento25.setMassaAtomica(54.93805);
		elementos.add(elemento25);

		Elemento elemento26 = new Elemento();
		elemento26.setNumeroAtomico(26);
		elemento26.setSigla(EnumSiglaElemento.valueOf("Fe"));
		elemento26.setNome("Ferro");
		elemento26.setMassaAtomica(55.847);
		elementos.add(elemento26);

		Elemento elemento27 = new Elemento();
		elemento27.setNumeroAtomico(27);
		elemento27.setSigla(EnumSiglaElemento.valueOf("Co"));
		elemento27.setNome("Cobalto");
		elemento27.setMassaAtomica(58.9332);
		elementos.add(elemento27);

		Elemento elemento28 = new Elemento();
		elemento28.setNumeroAtomico(28);
		elemento28.setSigla(EnumSiglaElemento.valueOf("Ni"));
		elemento28.setNome("Níquel");
		elemento28.setMassaAtomica(58.69);
		elementos.add(elemento28);

		Elemento elemento29 = new Elemento();
		elemento29.setNumeroAtomico(29);
		elemento29.setSigla(EnumSiglaElemento.valueOf("Cu"));
		elemento29.setNome("Cobre");
		elemento29.setMassaAtomica(63.546);
		elementos.add(elemento29);

		Elemento elemento30 = new Elemento();
		elemento30.setNumeroAtomico(30);
		elemento30.setSigla(EnumSiglaElemento.valueOf("Zn"));
		elemento30.setNome("Zinco");
		elemento30.setMassaAtomica(65.39);
		elementos.add(elemento30);

		Elemento elemento31 = new Elemento();
		elemento31.setNumeroAtomico(31);
		elemento31.setSigla(EnumSiglaElemento.valueOf("Ga"));
		elemento31.setNome("Gálio");
		elemento31.setMassaAtomica(69.723);
		elementos.add(elemento31);

		Elemento elemento32 = new Elemento();
		elemento32.setNumeroAtomico(32);
		elemento32.setSigla(EnumSiglaElemento.valueOf("Ge"));
		elemento32.setNome("Germânio");
		elemento32.setMassaAtomica(72.61);
		elementos.add(elemento32);

		Elemento elemento33 = new Elemento();
		elemento33.setNumeroAtomico(33);
		elemento33.setSigla(EnumSiglaElemento.valueOf("As"));
		elemento33.setNome("Arsênio");
		elemento33.setMassaAtomica(74.92159);
		elementos.add(elemento33);

		Elemento elemento34 = new Elemento();
		elemento34.setNumeroAtomico(34);
		elemento34.setSigla(EnumSiglaElemento.valueOf("Se"));
		elemento34.setNome("Selênio");
		elemento34.setMassaAtomica(78.96);
		elementos.add(elemento34);

		Elemento elemento35 = new Elemento();
		elemento35.setNumeroAtomico(35);
		elemento35.setSigla(EnumSiglaElemento.valueOf("Br"));
		elemento35.setNome("Bromo");
		elemento35.setMassaAtomica(79.904);
		elementos.add(elemento35);

		Elemento elemento36 = new Elemento();
		elemento36.setNumeroAtomico(36);
		elemento36.setSigla(EnumSiglaElemento.valueOf("Kr"));
		elemento36.setNome("Crípton");
		elemento36.setMassaAtomica(83.8);
		elementos.add(elemento36);

		Elemento elemento37 = new Elemento();
		elemento37.setNumeroAtomico(37);
		elemento37.setSigla(EnumSiglaElemento.valueOf("Rb"));
		elemento37.setNome("Rubídio");
		elemento37.setMassaAtomica(85.4678);
		elementos.add(elemento37);

		Elemento elemento38 = new Elemento();
		elemento38.setNumeroAtomico(38);
		elemento38.setSigla(EnumSiglaElemento.valueOf("Sr"));
		elemento38.setNome("Estrôncio");
		elemento38.setMassaAtomica(87.62);
		elementos.add(elemento38);

		Elemento elemento39 = new Elemento();
		elemento39.setNumeroAtomico(39);
		elemento39.setSigla(EnumSiglaElemento.valueOf("Y"));
		elemento39.setNome("Ítrio");
		elemento39.setMassaAtomica(88.90585);
		elementos.add(elemento39);

		Elemento elemento40 = new Elemento();
		elemento40.setNumeroAtomico(40);
		elemento40.setSigla(EnumSiglaElemento.valueOf("Zr"));
		elemento40.setNome("Zircônio");
		elemento40.setMassaAtomica(91.224);
		elementos.add(elemento40);

		Elemento elemento41 = new Elemento();
		elemento41.setNumeroAtomico(41);
		elemento41.setSigla(EnumSiglaElemento.valueOf("Nb"));
		elemento41.setNome("Nióbio");
		elemento41.setMassaAtomica(92.90638);
		elementos.add(elemento41);

		Elemento elemento42 = new Elemento();
		elemento42.setNumeroAtomico(42);
		elemento42.setSigla(EnumSiglaElemento.valueOf("Mo"));
		elemento42.setNome("Molibdénio");
		elemento42.setMassaAtomica(95.94);
		elementos.add(elemento42);

		Elemento elemento43 = new Elemento();
		elemento43.setNumeroAtomico(43);
		elemento43.setSigla(EnumSiglaElemento.valueOf("Tc"));
		elemento43.setNome("Tecnécio");
		elemento43.setMassaAtomica(98.9063);
		elementos.add(elemento43);

		Elemento elemento44 = new Elemento();
		elemento44.setNumeroAtomico(44);
		elemento44.setSigla(EnumSiglaElemento.valueOf("Ru"));
		elemento44.setNome("Rutênio");
		elemento44.setMassaAtomica(101.07);
		elementos.add(elemento44);

		Elemento elemento45 = new Elemento();
		elemento45.setNumeroAtomico(45);
		elemento45.setSigla(EnumSiglaElemento.valueOf("Rh"));
		elemento45.setNome("Ródio");
		elemento45.setMassaAtomica(102.9055);
		elementos.add(elemento45);

		Elemento elemento46 = new Elemento();
		elemento46.setNumeroAtomico(46);
		elemento46.setSigla(EnumSiglaElemento.valueOf("Pd"));
		elemento46.setNome("Paládio");
		elemento46.setMassaAtomica(106.42);
		elementos.add(elemento46);

		Elemento elemento47 = new Elemento();
		elemento47.setNumeroAtomico(47);
		elemento47.setSigla(EnumSiglaElemento.valueOf("Ag"));
		elemento47.setNome("Prata");
		elemento47.setMassaAtomica(107.8682);
		elementos.add(elemento47);

		Elemento elemento48 = new Elemento();
		elemento48.setNumeroAtomico(48);
		elemento48.setSigla(EnumSiglaElemento.valueOf("Cd"));
		elemento48.setNome("Cádmio");
		elemento48.setMassaAtomica(112.411);
		elementos.add(elemento48);

		Elemento elemento49 = new Elemento();
		elemento49.setNumeroAtomico(49);
		elemento49.setSigla(EnumSiglaElemento.valueOf("In"));
		elemento49.setNome("Índio");
		elemento49.setMassaAtomica(114.82);
		elementos.add(elemento49);

		Elemento elemento50 = new Elemento();
		elemento50.setNumeroAtomico(50);
		elemento50.setSigla(EnumSiglaElemento.valueOf("Sn"));
		elemento50.setNome("Estanho");
		elemento50.setMassaAtomica(118.71);
		elementos.add(elemento50);

		Elemento elemento51 = new Elemento();
		elemento51.setNumeroAtomico(51);
		elemento51.setSigla(EnumSiglaElemento.valueOf("Sb"));
		elemento51.setNome("Antimónio");
		elemento51.setMassaAtomica(121.75);
		elementos.add(elemento51);

		Elemento elemento52 = new Elemento();
		elemento52.setNumeroAtomico(52);
		elemento52.setSigla(EnumSiglaElemento.valueOf("Te"));
		elemento52.setNome("Telúrio");
		elemento52.setMassaAtomica(127.6);
		elementos.add(elemento52);

		Elemento elemento53 = new Elemento();
		elemento53.setNumeroAtomico(53);
		elemento53.setSigla(EnumSiglaElemento.valueOf("I"));
		elemento53.setNome("Iodo");
		elemento53.setMassaAtomica(128.90447);
		elementos.add(elemento53);

		Elemento elemento54 = new Elemento();
		elemento54.setNumeroAtomico(54);
		elemento54.setSigla(EnumSiglaElemento.valueOf("Xe"));
		elemento54.setNome("Xénon");
		elemento54.setMassaAtomica(131.29);
		elementos.add(elemento54);

		Elemento elemento55 = new Elemento();
		elemento55.setNumeroAtomico(55);
		elemento55.setSigla(EnumSiglaElemento.valueOf("Cs"));
		elemento55.setNome("Césio");
		elemento55.setMassaAtomica(132.90543);
		elementos.add(elemento55);

		Elemento elemento56 = new Elemento();
		elemento56.setNumeroAtomico(56);
		elemento56.setSigla(EnumSiglaElemento.valueOf("Ba"));
		elemento56.setNome("Bário");
		elemento56.setMassaAtomica(137.327);
		elementos.add(elemento56);

		Elemento elemento57 = new Elemento();
		elemento57.setNumeroAtomico(57);
		elemento57.setSigla(EnumSiglaElemento.valueOf("La"));
		elemento57.setNome("Lantânio");
		elemento57.setMassaAtomica(138.9055);
		elementos.add(elemento57);

		Elemento elemento58 = new Elemento();
		elemento58.setNumeroAtomico(58);
		elemento58.setSigla(EnumSiglaElemento.valueOf("Ce"));
		elemento58.setNome("Cério");
		elemento58.setMassaAtomica(140.115);
		elementos.add(elemento58);

		Elemento elemento59 = new Elemento();
		elemento59.setNumeroAtomico(59);
		elemento59.setSigla(EnumSiglaElemento.valueOf("Pr"));
		elemento59.setNome("Praseodímio");
		elemento59.setMassaAtomica(140.90765);
		elementos.add(elemento59);

		Elemento elemento60 = new Elemento();
		elemento60.setNumeroAtomico(60);
		elemento60.setSigla(EnumSiglaElemento.valueOf("Nd"));
		elemento60.setNome("Neodímio");
		elemento60.setMassaAtomica(144.24);
		elementos.add(elemento60);

		Elemento elemento61 = new Elemento();
		elemento61.setNumeroAtomico(61);
		elemento61.setSigla(EnumSiglaElemento.valueOf("Pm"));
		elemento61.setNome("Promécio");
		elemento61.setMassaAtomica(146.9151);
		elementos.add(elemento61);

		Elemento elemento62 = new Elemento();
		elemento62.setNumeroAtomico(62);
		elemento62.setSigla(EnumSiglaElemento.valueOf("Sm"));
		elemento62.setNome("Samário");
		elemento62.setMassaAtomica(150.36);
		elementos.add(elemento62);

		Elemento elemento63 = new Elemento();
		elemento63.setNumeroAtomico(63);
		elemento63.setSigla(EnumSiglaElemento.valueOf("Eu"));
		elemento63.setNome("Európio");
		elemento63.setMassaAtomica(151.965);
		elementos.add(elemento63);

		Elemento elemento64 = new Elemento();
		elemento64.setNumeroAtomico(64);
		elemento64.setSigla(EnumSiglaElemento.valueOf("Gd"));
		elemento64.setNome("Gadolínio");
		elemento64.setMassaAtomica(157.25);
		elementos.add(elemento64);

		Elemento elemento65 = new Elemento();
		elemento65.setNumeroAtomico(65);
		elemento65.setSigla(EnumSiglaElemento.valueOf("Tb"));
		elemento65.setNome("Térbio");
		elemento65.setMassaAtomica(158.92534);
		elementos.add(elemento65);

		Elemento elemento66 = new Elemento();
		elemento66.setNumeroAtomico(66);
		elemento66.setSigla(EnumSiglaElemento.valueOf("Dy"));
		elemento66.setNome("Disprósio");
		elemento66.setMassaAtomica(162.5);
		elementos.add(elemento66);

		Elemento elemento67 = new Elemento();
		elemento67.setNumeroAtomico(67);
		elemento67.setSigla(EnumSiglaElemento.valueOf("Ho"));
		elemento67.setNome("Hólmio");
		elemento67.setMassaAtomica(164.93032);
		elementos.add(elemento67);

		Elemento elemento68 = new Elemento();
		elemento68.setNumeroAtomico(68);
		elemento68.setSigla(EnumSiglaElemento.valueOf("Er"));
		elemento68.setNome("Érbio");
		elemento68.setMassaAtomica(167.26);
		elementos.add(elemento68);

		Elemento elemento69 = new Elemento();
		elemento69.setNumeroAtomico(69);
		elemento69.setSigla(EnumSiglaElemento.valueOf("Tm"));
		elemento69.setNome("Túlio");
		elemento69.setMassaAtomica(168.93421);
		elementos.add(elemento69);

		Elemento elemento70 = new Elemento();
		elemento70.setNumeroAtomico(70);
		elemento70.setSigla(EnumSiglaElemento.valueOf("Yb"));
		elemento70.setNome("Itérbio");
		elemento70.setMassaAtomica(173.04);
		elementos.add(elemento70);

		Elemento elemento71 = new Elemento();
		elemento71.setNumeroAtomico(71);
		elemento71.setSigla(EnumSiglaElemento.valueOf("Lu"));
		elemento71.setNome("Lutécio");
		elemento71.setMassaAtomica(174.967);
		elementos.add(elemento71);

		Elemento elemento72 = new Elemento();
		elemento72.setNumeroAtomico(72);
		elemento72.setSigla(EnumSiglaElemento.valueOf("Hf"));
		elemento72.setNome("Háfnio");
		elemento72.setMassaAtomica(178.49);
		elementos.add(elemento72);

		Elemento elemento73 = new Elemento();
		elemento73.setNumeroAtomico(73);
		elemento73.setSigla(EnumSiglaElemento.valueOf("Ta"));
		elemento73.setNome("Tântalo");
		elemento73.setMassaAtomica(180.9479);
		elementos.add(elemento73);

		Elemento elemento74 = new Elemento();
		elemento74.setNumeroAtomico(74);
		elemento74.setSigla(EnumSiglaElemento.valueOf("W"));
		elemento74.setNome("Tungsténio");
		elemento74.setMassaAtomica(183.85);
		elementos.add(elemento74);

		Elemento elemento75 = new Elemento();
		elemento75.setNumeroAtomico(75);
		elemento75.setSigla(EnumSiglaElemento.valueOf("Re"));
		elemento75.setNome("Rénio");
		elemento75.setMassaAtomica(186.207);
		elementos.add(elemento75);

		Elemento elemento76 = new Elemento();
		elemento76.setNumeroAtomico(76);
		elemento76.setSigla(EnumSiglaElemento.valueOf("Os"));
		elemento76.setNome("Ósmio");
		elemento76.setMassaAtomica(190.2);
		elementos.add(elemento76);

		Elemento elemento77 = new Elemento();
		elemento77.setNumeroAtomico(77);
		elemento77.setSigla(EnumSiglaElemento.valueOf("Ir"));
		elemento77.setNome("Irídio");
		elemento77.setMassaAtomica(192.22);
		elementos.add(elemento77);

		Elemento elemento78 = new Elemento();
		elemento78.setNumeroAtomico(78);
		elemento78.setSigla(EnumSiglaElemento.valueOf("Pt"));
		elemento78.setNome("Platina");
		elemento78.setMassaAtomica(195.08);
		elementos.add(elemento78);

		Elemento elemento79 = new Elemento();
		elemento79.setNumeroAtomico(79);
		elemento79.setSigla(EnumSiglaElemento.valueOf("Au"));
		elemento79.setNome("Ouro");
		elemento79.setMassaAtomica(196.96654);
		elementos.add(elemento79);

		Elemento elemento80 = new Elemento();
		elemento80.setNumeroAtomico(80);
		elemento80.setSigla(EnumSiglaElemento.valueOf("Hg"));
		elemento80.setNome("Mercúrio");
		elemento80.setMassaAtomica(200.59);
		elementos.add(elemento80);

		Elemento elemento81 = new Elemento();
		elemento81.setNumeroAtomico(81);
		elemento81.setSigla(EnumSiglaElemento.valueOf("Tl"));
		elemento81.setNome("Tálio");
		elemento81.setMassaAtomica(204.3833);
		elementos.add(elemento81);

		Elemento elemento82 = new Elemento();
		elemento82.setNumeroAtomico(82);
		elemento82.setSigla(EnumSiglaElemento.valueOf("Pb"));
		elemento82.setNome("Chumbo");
		elemento82.setMassaAtomica(207.2);
		elementos.add(elemento82);

		Elemento elemento83 = new Elemento();
		elemento83.setNumeroAtomico(83);
		elemento83.setSigla(EnumSiglaElemento.valueOf("Bi"));
		elemento83.setNome("Bismuto");
		elemento83.setMassaAtomica(208.98037);
		elementos.add(elemento83);

		Elemento elemento84 = new Elemento();
		elemento84.setNumeroAtomico(84);
		elemento84.setSigla(EnumSiglaElemento.valueOf("Po"));
		elemento84.setNome("Polónio");
		elemento84.setMassaAtomica(208.9824);
		elementos.add(elemento84);

		Elemento elemento85 = new Elemento();
		elemento85.setNumeroAtomico(85);
		elemento85.setSigla(EnumSiglaElemento.valueOf("At"));
		elemento85.setNome("Ástato");
		elemento85.setMassaAtomica(209.9871);
		elementos.add(elemento85);

		Elemento elemento86 = new Elemento();
		elemento86.setNumeroAtomico(86);
		elemento86.setSigla(EnumSiglaElemento.valueOf("Rn"));
		elemento86.setNome("Rádon");
		elemento86.setMassaAtomica(222.0176);
		elementos.add(elemento86);

		Elemento elemento87 = new Elemento();
		elemento87.setNumeroAtomico(87);
		elemento87.setSigla(EnumSiglaElemento.valueOf("Fr"));
		elemento87.setNome("Frâncio");
		elemento87.setMassaAtomica(223.0197);
		elementos.add(elemento87);

		Elemento elemento88 = new Elemento();
		elemento88.setNumeroAtomico(88);
		elemento88.setSigla(EnumSiglaElemento.valueOf("Ra"));
		elemento88.setNome("Rádio");
		elemento88.setMassaAtomica(226.0254);
		elementos.add(elemento88);

		Elemento elemento89 = new Elemento();
		elemento89.setNumeroAtomico(89);
		elemento89.setSigla(EnumSiglaElemento.valueOf("Ac"));
		elemento89.setNome("Actínio");
		elemento89.setMassaAtomica(227.0278);
		elementos.add(elemento89);

		Elemento elemento90 = new Elemento();
		elemento90.setNumeroAtomico(90);
		elemento90.setSigla(EnumSiglaElemento.valueOf("Th"));
		elemento90.setNome("Tório");
		elemento90.setMassaAtomica(232.0381);
		elementos.add(elemento90);

		Elemento elemento91 = new Elemento();
		elemento91.setNumeroAtomico(91);
		elemento91.setSigla(EnumSiglaElemento.valueOf("Pa"));
		elemento91.setNome("Protactínio");
		elemento91.setMassaAtomica(231.0359);
		elementos.add(elemento91);

		Elemento elemento92 = new Elemento();
		elemento92.setNumeroAtomico(92);
		elemento92.setSigla(EnumSiglaElemento.valueOf("U"));
		elemento92.setNome("Urânio");
		elemento92.setMassaAtomica(238.0289);
		elementos.add(elemento92);

		Elemento elemento93 = new Elemento();
		elemento93.setNumeroAtomico(93);
		elemento93.setSigla(EnumSiglaElemento.valueOf("Np"));
		elemento93.setNome("Neptúnio");
		elemento93.setMassaAtomica(237.0482);
		elementos.add(elemento93);

		Elemento elemento94 = new Elemento();
		elemento94.setNumeroAtomico(94);
		elemento94.setSigla(EnumSiglaElemento.valueOf("Pu"));
		elemento94.setNome("Plutônio");
		elemento94.setMassaAtomica(244.0642);
		elementos.add(elemento94);

		Elemento elemento95 = new Elemento();
		elemento95.setNumeroAtomico(95);
		elemento95.setSigla(EnumSiglaElemento.valueOf("Am"));
		elemento95.setNome("Amerício");
		elemento95.setMassaAtomica(243.0614);
		elementos.add(elemento95);

		Elemento elemento96 = new Elemento();
		elemento96.setNumeroAtomico(96);
		elemento96.setSigla(EnumSiglaElemento.valueOf("Cm"));
		elemento96.setNome("Cúrio");
		elemento96.setMassaAtomica(247.0703);
		elementos.add(elemento96);

		Elemento elemento97 = new Elemento();
		elemento97.setNumeroAtomico(97);
		elemento97.setSigla(EnumSiglaElemento.valueOf("Bk"));
		elemento97.setNome("Berkélio");
		elemento97.setMassaAtomica(247.0703);
		elementos.add(elemento97);

		Elemento elemento98 = new Elemento();
		elemento98.setNumeroAtomico(98);
		elemento98.setSigla(EnumSiglaElemento.valueOf("Cf"));
		elemento98.setNome("Califórnio");
		elemento98.setMassaAtomica(251.0796);
		elementos.add(elemento98);

		Elemento elemento99 = new Elemento();
		elemento99.setNumeroAtomico(99);
		elemento99.setSigla(EnumSiglaElemento.valueOf("Es"));
		elemento99.setNome("Einstênio");
		elemento99.setMassaAtomica(252.0829);
		elementos.add(elemento99);

		Elemento elemento100 = new Elemento();
		elemento100.setNumeroAtomico(100);
		elemento100.setSigla(EnumSiglaElemento.valueOf("Fm"));
		elemento100.setNome("Férmio");
		elemento100.setMassaAtomica(257.0951);
		elementos.add(elemento100);

		Elemento elemento101 = new Elemento();
		elemento101.setNumeroAtomico(101);
		elemento101.setSigla(EnumSiglaElemento.valueOf("Md"));
		elemento101.setNome("Mendelévio");
		elemento101.setMassaAtomica(258.0986);
		elementos.add(elemento101);

		Elemento elemento102 = new Elemento();
		elemento102.setNumeroAtomico(102);
		elemento102.setSigla(EnumSiglaElemento.valueOf("No"));
		elemento102.setNome("Nobélio");
		elemento102.setMassaAtomica(259.1009);
		elementos.add(elemento102);

		Elemento elemento103 = new Elemento();
		elemento103.setNumeroAtomico(103);
		elemento103.setSigla(EnumSiglaElemento.valueOf("Lr"));
		elemento103.setNome("Laurêncio");
		elemento103.setMassaAtomica(260.1053);
		elementos.add(elemento103);

		Elemento elemento104 = new Elemento();
		elemento104.setNumeroAtomico(104);
		elemento104.setSigla(EnumSiglaElemento.valueOf("Rf"));
		elemento104.setNome("Rutherfórdio");
		elemento104.setMassaAtomica(261.1087);
		elementos.add(elemento104);

		Elemento elemento105 = new Elemento();
		elemento105.setNumeroAtomico(105);
		elemento105.setSigla(EnumSiglaElemento.valueOf("Db"));
		elemento105.setNome("Dúbnio");
		elemento105.setMassaAtomica(262.1138);
		elementos.add(elemento105);

		Elemento elemento106 = new Elemento();
		elemento106.setNumeroAtomico(106);
		elemento106.setSigla(EnumSiglaElemento.valueOf("Sg"));
		elemento106.setNome("Seabórgio");
		elemento106.setMassaAtomica(263.1182);
		elementos.add(elemento106);

		Elemento elemento107 = new Elemento();
		elemento107.setNumeroAtomico(107);
		elemento107.setSigla(EnumSiglaElemento.valueOf("Bh"));
		elemento107.setNome("Bório");
		elemento107.setMassaAtomica(262.1229);
		elementos.add(elemento107);

		Elemento elemento108 = new Elemento();
		elemento108.setNumeroAtomico(108);
		elemento108.setSigla(EnumSiglaElemento.valueOf("Hs"));
		elemento108.setNome("Hássio");
		elemento108.setMassaAtomica(265.0);
		elementos.add(elemento108);

		Elemento elemento109 = new Elemento();
		elemento109.setNumeroAtomico(109);
		elemento109.setSigla(EnumSiglaElemento.valueOf("Mt"));
		elemento109.setNome("Meitnério");
		elemento109.setMassaAtomica(266.0);
		elementos.add(elemento109);

		Elemento elemento110 = new Elemento();
		elemento110.setNumeroAtomico(110);
		elemento110.setSigla(EnumSiglaElemento.valueOf("Ds"));
		elemento110.setNome("Darmstácio");
		elemento110.setMassaAtomica(269.0);
		elementos.add(elemento110);

		Elemento elemento111 = new Elemento();
		elemento111.setNumeroAtomico(111);
		elemento111.setSigla(EnumSiglaElemento.valueOf("Rg"));
		elemento111.setNome("Roentgênio");
		elemento111.setMassaAtomica(272.0);
		elementos.add(elemento111);

		Elemento elemento112 = new Elemento();
		elemento112.setNumeroAtomico(112);
		elemento112.setSigla(EnumSiglaElemento.valueOf("Cn"));
		elemento112.setNome("Copernício");
		elemento112.setMassaAtomica(277.0);
		elementos.add(elemento112);

		Elemento elemento113 = new Elemento();
		elemento113.setNumeroAtomico(113);
		elemento113.setSigla(EnumSiglaElemento.valueOf("Uut"));
		elemento113.setNome("Unúntrio");
		elemento113.setMassaAtomica(0.0);
		elementos.add(elemento113);

		Elemento elemento118 = new Elemento();
		elemento118.setNumeroAtomico(118);
		elemento118.setSigla(EnumSiglaElemento.valueOf("Uuo"));
		elemento118.setNome("Ununóctio");
		elemento118.setMassaAtomica(0.0);
		elementos.add(elemento118);

	}

}
