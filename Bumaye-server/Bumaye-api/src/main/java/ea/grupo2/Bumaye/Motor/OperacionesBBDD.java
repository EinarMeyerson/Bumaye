package ea.grupo2.Bumaye.Motor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.ListBatallasVO;
import ea.grupo2.Bumaye.ClasesVO.MercadoVO;
import ea.grupo2.Bumaye.ClasesVO.ListaPeticionesVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCofreCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeLogeadoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.PeticionBatallaVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.hibernate.HibernateUtil;
import ea.grupo2.Bumaye.pojos.ArmaArmaduraEquipada;
import ea.grupo2.Bumaye.pojos.ArmasArmaduras;
import ea.grupo2.Bumaye.pojos.Ataques;
import ea.grupo2.Bumaye.pojos.Batalla;
import ea.grupo2.Bumaye.pojos.Cofre;
import ea.grupo2.Bumaye.pojos.Mercado;
import ea.grupo2.Bumaye.pojos.Objeto;
import ea.grupo2.Bumaye.pojos.ObjetoCantidad;
import ea.grupo2.Bumaye.pojos.ObjetoCofreCantidad;
import ea.grupo2.Bumaye.pojos.UsrPersonaje;

public class OperacionesBBDD implements BumayeInterface{    

	ListBatallasVO listbatallas = ListBatallasVO.getInstance();
	ListaPeticionesVO listpeticiones = ListaPeticionesVO.getInstance();

	public static void main(String[] args) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();

		//        ArrayList<PersonajeVO> listaPersonajesVO = new ArrayList<PersonajeVO>();
		//        UsuarioVO u1 = new UsuarioVO("Ilcapone", "1234", "palotes@pushi");
		//        listaPersonajesVO.add(m.LoginUser(u1));
		// 
		//        UsuarioVO u2 = new UsuarioVO("Elcolmo", "1234", "mañana@quizas.si");
		//        listaPersonajesVO.add(m.LoginUser(u2));
		//         
		//        m.iniciarBatallaVO(listaPersonajesVO);
		//        m.getBatallaVO(1);

		/* COFRES  */
		//(idcofre, latitud, longitud)41.269861, 1.991578

		m.addCofre(new Cofre (1.980635,41.272458));
		m.addCofre(new Cofre (1.986450,41.281940));
		m.addCofre(new Cofre (1.991578,41.269861));


		/* OBJETOS  */
		//(nombre objeto, tipo, rareza, combo1, combo2, %exito)

		m.addObjeto(new Objeto ("pocion", "vida", 40, "hierba", "seta",90));
		m.addObjeto(new Objeto ("hierba", "basico", 10, null, null,100));
		m.addObjeto(new Objeto ("seta", "basico", 10, null, null,100));

		m.addObjeto(new Objeto ("elixir", "ataque", 50, "pocion", "runa_muerte",60));
		m.addObjeto(new Objeto ("runa_muerte", "runa", 70, "piedra", "calavera",70));
		m.addObjeto(new Objeto ("seta_roja", "basico", 30, null, null,100));

		m.addObjeto(new Objeto ("caldo", "defensa", 50, "pocion", "runa_azul",60));
		m.addObjeto(new Objeto ("runa_azul", "runa", 70, "piedra", "seta_azul",70));
		m.addObjeto(new Objeto ("seta_azul", "basico", 30, null, null,100));

		m.addObjeto(new Objeto ("min_bronce", "mineral", 5, null, null,100));
		m.addObjeto(new Objeto ("min_oro", "mineral", 20, null, null,100));
		m.addObjeto(new Objeto ("min_drag", "mineral", 30, null, null,100));

		m.addObjeto(new Objeto ("barra_bronce", "barra", 50, "min_bronce", "herreria",70));
		m.addObjeto(new Objeto ("barra_oro", "barra", 60, "min_oro", "herreria",60));
		m.addObjeto(new Objeto ("barra_drag", "barra", 70, "min_drag", "herreria",50));


		m.addObjeto(new Objeto ("calavera", "??????", 70, "piedra", "calavera",70));

		//16 max_runa_cristal_agua
		m.addObjeto(new Objeto ("pedrusco", "basico", 20, null, null,100));
		m.addObjeto(new Objeto ("piedra", "basico", 10, null, null,100));
		m.addObjeto(new Objeto ("runa_agua", "runa", 30, "piedra", "cuenco_agua",70));
		m.addObjeto(new Objeto ("runa_rayo", "runa", 30, "pedrusco", "trueno",70));
		m.addObjeto(new Objeto ("runa_hoja", "runa", 30, "pedrusco", "hierba",70));
		m.addObjeto(new Objeto ("runa_nieve", "runa", 30, "pedrusco", "nieve",70));

		m.addObjeto(new Objeto ("cristal", "basico", 60, null, null,100));

		m.addObjeto(new Objeto ("runa_cristal_agua", "runa", 70, "cristal", "runa_agua",60));
		m.addObjeto(new Objeto ("runa_cristal_hoja", "runa", 70, "cristal", "runa_hoja",60));
		m.addObjeto(new Objeto ("runa_cristal_nieve", "runa", 70, "cristal", "runa_nievel",60));
		m.addObjeto(new Objeto ("runa_cristal_rayo", "runa", 70, "cristal", "runa_rayo",60));

		m.addObjeto(new Objeto ("diamante", "basico", 80, null, null,100));

		m.addObjeto(new Objeto ("max_runa_cristal_agua", "runa", 90, "diamante", "runa_cristal_agua",50));
		m.addObjeto(new Objeto ("max_runa_cristal_hoja", "runa", 90, "diamante", "runa_cristal_hoja",50));
		m.addObjeto(new Objeto ("max_runa_cristal_nieve", "runa", 90, "diamante", "runa_cristal_nieve",50));
		m.addObjeto(new Objeto ("max_runa_cristal_rayo", "runa", 90, "diamante", "runa_cristal_rayo",50));

		m.addObjeto(new Objeto ("herreria", "herramienta", 100, null, null,90));
		
		//añadimos objetos a los cofres
		m.añadirObjetosCofre(2, 1);
		m.añadirObjetosCofre(2, 1);
		m.añadirObjetosCofre(3, 1);
		m.añadirObjetosCofre(3, 1);
		m.añadirObjetosCofre(4, 1);

		m.añadirObjetosCofre(5, 2);
		m.añadirObjetosCofre(6, 2);
		m.añadirObjetosCofre(7, 2);
		m.añadirObjetosCofre(7, 2);
		m.añadirObjetosCofre(8, 2);

		m.añadirObjetosCofre(9, 3);
		m.añadirObjetosCofre(10, 3);
		m.añadirObjetosCofre(10, 3);
		m.añadirObjetosCofre(11, 3);
		m.añadirObjetosCofre(12, 3);

		//33 la ultima


		System.out.print("Objetos añadidos a la base de datos");


		/* ARMAS Y ARMADURAS  */

		//Armadura de Bronce (nombre, tipo, defensa, ataque)
		m.addArmasArmadurasBBDD(new ArmasArmaduras("espada_bronce","arma",0,10));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("casco_bronce","casco",5,5));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("guantes_bronce","guantes",5,5));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("coraza_bronce","coraza",10,7));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("perneras_bronce","perneras",5,5));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("botas_bronce","botas",5,5));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("arco_bronce","coraza",0,12));
		System.out.print("Armadura de Bronce añadida a la base de datos");

		//Armadura de Oro
		m.addArmasArmadurasBBDD(new ArmasArmaduras("espada_oro","arma",2,15));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("casco_oro","casco",10,7));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("guantes_oro","guantes",10,7));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("coraza_oro","coraza",15,10));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("perneras_oro","perneras",10,7));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("botas_oro","botas",10,7));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("garrote_oro","arma",0,15));
		System.out.print("Armadura de Oro añadida a la base de datos");

		//Armadura de Dragon
		m.addArmasArmadurasBBDD(new ArmasArmaduras("espada_dragon","arma",5,20));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("casco_dragon","casco",15,10));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("guantes_dragon","guantes",15,10));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("coraza_dragon","coraza",20,15));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("perneras_dragon","perneras",15,10));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("botas_dragon","botas",15,10));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("martillo_dragon","arma",0,30));
		System.out.print("Armadura de Dragon añadida a la base de datos");

		//Armadura de trapo (nombre, tipo, defensa, ataque)
		m.addArmasArmadurasBBDD(new ArmasArmaduras("palo_trapo","arma",0,5));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("casco_trapo","casco",3,2));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("guantes_trapo","guantes",3,2));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("coraza_trapo","coraza",7,4));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("perneras_trapo","perneras",3,2));
		m.addArmasArmadurasBBDD(new ArmasArmaduras("botas_trapo","botas",3,2));
		System.out.print("Armadura de Trapo añadida a la base de datos");


		/* ATAQUES */

		//ataques armas de bronce 
		//(nombre, atributoafectado, personajeafectado, factordaño, porcentajeacierto, vecesuso), arma/armadura)
		m.addAtaqueBBDD(new Ataques("Espadazo","vida",1,20,70,20),1);
		m.addAtaqueBBDD(new Ataques("Cabezazo","vida",1,5,90,2),2);
		m.addAtaqueBBDD(new Ataques("Defensa ferrea","defensa",0,20,75,2),4);
		m.addAtaqueBBDD(new Ataques("Flechazo","vida",1,10,60,30),7);
		System.out.print("Ataques de armas de bronce añadios a las armaduras");

		//ataques armas de oro
		m.addAtaqueBBDD(new Ataques("Corte","vida",1,30,80,25),8);
		m.addAtaqueBBDD(new Ataques("Pensador","defensa",0,25,75,4),9);
		m.addAtaqueBBDD(new Ataques("La Roca","defensa",0,40,80,3),11);
		m.addAtaqueBBDD(new Ataques("Varazo","vida",1,25,85,10),14);
		System.out.print("Ataques de armas de oro añadios a las armaduras");

		//ataques armas de dragon
		m.addAtaqueBBDD(new Ataques("Rajar","vida",1,40,90,30),15);
		m.addAtaqueBBDD(new Ataques("Mentalismo","defensa",0,30,80,6),16);
		m.addAtaqueBBDD(new Ataques("Escamas de Dragon","defensa",0,60,90,4),18);
		m.addAtaqueBBDD(new Ataques("Machacar","vida",1,60,60,20),21);
		System.out.print("Ataques de armas de dragon añadios a las armaduras");

		//ataques armas de trapo
		m.addAtaqueBBDD(new Ataques("Palazo","vida",1,30,60,20),22);
		m.addAtaqueBBDD(new Ataques("Cubrete","defensa",0,20,60,2),25);
		System.out.print("Ataques de armas de dragon añadios a las armaduras");


		//      /* PERSONAJES */
		m.addUsrPersonaje(new UsrPersonaje(null,"albert@hotmail.com","spot","Spot",100,10,10,41.215016, 1.727201,1000));
		m.addUsrPersonaje(new UsrPersonaje(null,"eianr@hotmail.com","1234","Elcolmo",100,10,10,41.376700, 2.114187,1000));
		m.addUsrPersonaje(new UsrPersonaje(null,"juan@hotmail.com","1234","Ilcapone",100,10,10, 42.571684, -0.547046,1000));
		System.out.print("Jugadores añadidos");

		/* AÑADIR OBJETOS A PERSONAJES */
		m.añadirObjetoInventarioVerificado(2, 1);
		m.añadirObjetoInventarioVerificado(3, 1);
		m.añadirObjetoInventarioVerificado(2, 1);
		m.añadirObjetoInventarioVerificado(3, 1);
		m.añadirObjetoInventarioVerificado(6, 1);
		m.añadirObjetoInventarioVerificado(7, 1);
		m.añadirObjetoInventarioVerificado(9, 1);
		m.añadirObjetoInventarioVerificado(10, 1);
		m.añadirObjetoInventarioVerificado(11, 1);
		m.añadirObjetoInventarioVerificado(13, 1);
		//		      m.añadirObjetos(16, 1);
		System.out.print("Objetos añadias a jugador1");


		m.añadirObjetoInventarioVerificado(2, 2);
		m.añadirObjetoInventarioVerificado(3, 2);
		m.añadirObjetoInventarioVerificado(6, 2);
		m.añadirObjetoInventarioVerificado(7, 2);
		m.añadirObjetoInventarioVerificado(9, 2);
		m.añadirObjetoInventarioVerificado(10, 2);
		m.añadirObjetoInventarioVerificado(11, 2);
		m.añadirObjetoInventarioVerificado(13, 2);
		//		      m.añadirObjetos(16, 2);
		System.out.print("Objetos añadias a jugador2");

		m.añadirObjetoInventarioVerificado(1, 3);
		m.añadirObjetoInventarioVerificado(1, 3);
		m.añadirObjetoInventarioVerificado(1, 3);
		m.añadirObjetoInventarioVerificado(1, 3);
		m.añadirObjetoInventarioVerificado(4, 3);
		m.añadirObjetoInventarioVerificado(4, 3);
		m.añadirObjetoInventarioVerificado(4, 3);
		m.añadirObjetoInventarioVerificado(4, 3);
		m.añadirObjetoInventarioVerificado(7, 3);
		m.añadirObjetoInventarioVerificado(7, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(12, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(15, 3);
		m.añadirObjetoInventarioVerificado(20, 3);
		m.añadirObjetoInventarioVerificado(30, 3);
		m.añadirObjetoInventarioVerificado(31, 3);
		m.añadirObjetoInventarioVerificado(32, 3);
		m.añadirObjetoInventarioVerificado(33, 3);

		System.out.print("Objetos añadias a jugador3");


		/* AÑADIR ARMADURAS A PERSONAJES */
		//(idarmadura/ iduser)
		m.añadirArmasArmaduras(1, 1);
		m.añadirArmasArmaduras(2, 1);
		m.añadirArmasArmaduras(3, 1);
		m.añadirArmasArmaduras(4, 1);
		m.añadirArmasArmaduras(5, 1);
		m.añadirArmasArmaduras(6, 1);
		System.out.print("Armaduras añadias a jugador1");

		m.añadirArmasArmaduras(14, 2);
		m.añadirArmasArmaduras(9, 2);
		m.añadirArmasArmaduras(10, 2);
		m.añadirArmasArmaduras(11, 2);
		m.añadirArmasArmaduras(12, 2);
		m.añadirArmasArmaduras(13, 2);
		System.out.print("Armaduras añadidas a jugador2");

		m.añadirArmasArmaduras(14, 3);
		m.añadirArmasArmaduras(9, 3);
		m.añadirArmasArmaduras(10, 3);
		m.añadirArmasArmaduras(11, 3);
		m.añadirArmasArmaduras(20, 3);
		m.añadirArmasArmaduras(16, 3);
		m.añadirArmasArmaduras(17, 3);
		m.añadirArmasArmaduras(18, 3);
		m.añadirArmasArmaduras(19, 3);
		m.añadirArmasArmaduras(21, 3);
		System.out.print("Armaduras añadias a jugador3");


		/* EQUIPAR ARMADURAS A PERSONAJES */
		m.añadirArmasArmadurasEquipada(1, 1);
		m.updateAtributosEquipada_UserArmasArmaduras(1, 1);
		m.añadirArmasArmadurasEquipada(2, 1);
		m.updateAtributosEquipada_UserArmasArmaduras(2, 1);
		m.añadirArmasArmadurasEquipada(3, 1);
		m.updateAtributosEquipada_UserArmasArmaduras(3, 1);
		m.añadirArmasArmadurasEquipada(4, 1);
		m.updateAtributosEquipada_UserArmasArmaduras(4, 1);
		m.añadirArmasArmadurasEquipada(5, 1);
		m.updateAtributosEquipada_UserArmasArmaduras(5, 1);
		m.añadirArmasArmadurasEquipada(6, 1);
		m.updateAtributosEquipada_UserArmasArmaduras(6, 1);
		System.out.print("Equipado el jugador1");

		m.añadirArmasArmadurasEquipada(14, 2);
		m.updateAtributosEquipada_UserArmasArmaduras(14, 2);
		m.añadirArmasArmadurasEquipada(9, 2);
		m.updateAtributosEquipada_UserArmasArmaduras(9, 2);
		m.añadirArmasArmadurasEquipada(10, 2);
		m.updateAtributosEquipada_UserArmasArmaduras(10, 2);
		m.añadirArmasArmadurasEquipada(11, 2);
		m.updateAtributosEquipada_UserArmasArmaduras(11, 2);
		m.añadirArmasArmadurasEquipada(12, 2);
		m.updateAtributosEquipada_UserArmasArmaduras(12, 2);
		m.añadirArmasArmadurasEquipada(13, 2);
		m.updateAtributosEquipada_UserArmasArmaduras(13, 2);
		System.out.print("Equipado el jugador2");

		m.añadirArmasArmadurasEquipada(20, 3);
		m.updateAtributosEquipada_UserArmasArmaduras(20, 3);
		m.añadirArmasArmadurasEquipada(16, 3);
		m.updateAtributosEquipada_UserArmasArmaduras(16, 3);
		m.añadirArmasArmadurasEquipada(17, 3);
		m.updateAtributosEquipada_UserArmasArmaduras(17, 3);
		m.añadirArmasArmadurasEquipada(18, 3);
		m.updateAtributosEquipada_UserArmasArmaduras(18, 3);
		m.añadirArmasArmadurasEquipada(19, 3);
		m.updateAtributosEquipada_UserArmasArmaduras(19, 3);
		m.añadirArmasArmadurasEquipada(21, 3);
		m.updateAtributosEquipada_UserArmasArmaduras(21, 3);
		System.out.print("Equipado el jugador3");
		
		//m.addEntradaMercado(idUser, idObjeto, cantidad, precioUnidad)
		m.addEntradaMercado(1, 2, 2, 100);
		m.addEntradaMercado(1, 6, 1, 200);
		m.addEntradaMercado(3, 4, 1, 100);

	}


	@Override
	public double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		Double latDistance = deg2rad(lat2 - lat1);
		Double lonDistance = deg2rad(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		return distance;
	}

	@Override
	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	@Override
	public int randInt() {

		int min =0;
		int max = 99;
		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;
		//      System.out.print(randomNum+"\n");
		return randomNum;
	}


	@Override
	public String addAtaqueBBDD(Ataques ataque, int idarmaarmadura) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="ataque rechazado";
		ArmasArmaduras arm = null;
		try{
			transaction = session.beginTransaction();   
			arm = (ArmasArmaduras)session.load(ArmasArmaduras.class, idarmaarmadura);

			ataque.setArmasarmaduras(arm);
			session.save(ataque);

			arm.addAtaque(ataque);
			session.saveOrUpdate(arm);

			if (arm != null) {
				transaction.commit();
				s="ataque acceptado";
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}

	@Override
	public String updateAtaqueBBDD(int idAtaque, Ataques ataque) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAtaqueBBDD(int idAtaque) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtaqueVO getAtaque(int idAtaque) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Ataques ataque = new Ataques();
		AtaqueVO ataquevo= null;
		try{
			transaction = session.beginTransaction();
			ataque = (Ataques)session.load(Ataques.class, idAtaque);
			if (ataque!= null) {

				ataquevo = new AtaqueVO(ataque.getIdAtaque(), ataque.getNombre(), ataque.getAtributoAfectado(), ataque.getFactorDaño(), ataque.getPorcentajeAcierto(), ataque.getJugadorAfectado() , ataque.getVecesUso());
				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return ataquevo;
	}

	@Override
	public List<AtaqueVO> listaAtaquesUsr(int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje u = new UsrPersonaje();
		List<AtaqueVO> ataquesVOfin = new ArrayList<AtaqueVO>();

		try{
			transaction = session.beginTransaction();
			u = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (u != null) {

				////
				List<ArmasArmaduras> armasarmaduras = u.getArmasarmaduras();              
				for (ArmasArmaduras arm: armasarmaduras) {
					//                  System.out.println(arm);
					List<AtaqueVO> ataques = (listaAtaquesArm(arm.getIdArmasArmaduras()));    
					for (AtaqueVO atac: ataques) {
						//                      System.out.println(atac.getNombreataque());
						ataquesVOfin.add(new AtaqueVO(atac.getIdataque(), atac.getNombreataque(), atac.getAtributoafectado(), atac.getFactor(), atac.getAcierto(), atac.getJugadorafectado(), atac.getVecesuso()));
					}

				}
				////

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return ataquesVOfin;
	}

	@Override
	public List<AtaqueVO> listaAtaquesArm(int idArm) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		ArmasArmaduras arma = new ArmasArmaduras();
		List<AtaqueVO> ataqueVO = new ArrayList<AtaqueVO>();

		try{
			transaction = session.beginTransaction();
			arma = (ArmasArmaduras)session.load(ArmasArmaduras.class, idArm);
			if (arma != null) {

				////
				List<Ataques> ataques = arma.getAtaques();                
				for (Ataques atac: ataques) {
					//                  System.out.println(atac);
					ataqueVO.add(new AtaqueVO(atac.getIdAtaque(), atac.getNombre(), atac.getAtributoAfectado(), atac.getFactorDaño(), atac.getPorcentajeAcierto(), atac.getJugadorAfectado(), atac.getVecesUso())); 
				}
				////

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return ataqueVO;
	}

	@Override
	public String addArmasArmadurasBBDD(ArmasArmaduras armasarmaduras) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			//colecionUsers.addUser(usuario);
			session.save(armasarmaduras);
			System.out.println("armasarmaduras añadido");
			transaction.commit();
			return "armasarmaduras añadido";
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			return "armasarmaduras no añadido";
		}
		finally {
			session.close();
		}

	}

	@Override
	public String updateArmasArmadurasBBDD(int idArmasArmaduras,
			ArmasArmaduras armasarmaduras) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteArmasArmadurasBBDD(int idArmasArmaduras) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String addUsrPersonaje(UsrPersonaje usrpersonaje) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			//colecionUsers.addUser(usuario);
			session.save(usrpersonaje);
			System.out.println("Usuario añadido");
			transaction.commit();
			return "Usuario añadido";
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			return "Usuario no añadido";
		}
		finally {
			session.close();
		}
	}

	@Override
	public String updateUsrPersonaje(int idUsrPersonaje,
			UsrPersonaje usrpersonaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUsrPersonaje(int idUsrPersonaje) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String añadirArmasArmaduras(int idarmaarmadura, int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="armasarmaduras rechazada";
		UsrPersonaje usrper = null;
		ArmasArmaduras armaarmadura = null;
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			armaarmadura = (ArmasArmaduras)session.load(ArmasArmaduras.class, idarmaarmadura);

			usrper.addArmasArmaduras(armaarmadura);
			//por defecto al añadir una armadura a un usuario la dejamos desequipada
			desequiparArmasArmadurasEquipada(armaarmadura.getIdArmasArmaduras(),usrper.getIduser());

			if (usrper != null) {
				transaction.commit();
				s="armasarmaduras acceptada";
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}

	@Override
	public PersonajeVO LoginUser(UsuarioVO userlog) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		PersonajeVO personajelog = null;
		List<ArmaArmaduraVO> armaduraspersonaje = new ArrayList<ArmaArmaduraVO>();
		List<ObjetoCantidadVO> listobjetos = new ArrayList<ObjetoCantidadVO>();
		try{
			transaction = session.beginTransaction();
			System.out.print("Usuario: " + userlog.getUsername() + " Contraseña : " + userlog.getPass());
			Query query = session.createQuery("from UsrPersonaje as u where u.nombre='" + userlog.getUsername() + "' and u.password='" + userlog.getPass()+ "'");
			UsrPersonaje u = (UsrPersonaje) query.uniqueResult();
			//UsrPersonaje us  = (UsrPersonaje)session.load(UsrPersonaje.class, userlog.getUsername());
			if (u != null) {
				transaction.commit();
				personajelog = new PersonajeVO(u.getIduser(),u.getIdGCM(), u.getNombre(), u.getVida(), u.getDefensa(), u.getAtaque(), u.getLatitud(), u.getLongitud(), u.getLaPasta());
				for (ArmasArmaduras arm: u.getArmasarmaduras()) {
					//Sacar armaduras y ataques y pasarselos al personaje

					//miramos si la armadura esta equipada o desequipada
					int equipamiento= verificarArmaArmaduraEquipada(arm.getIdArmasArmaduras(),u.getIduser());

					ArmaArmaduraVO armunica= new ArmaArmaduraVO(arm.getIdArmasArmaduras(), arm.getNombre(), arm.getTipo(), arm.getDefensa(), equipamiento ,arm.getAtaque());
					//sacar ataques
					List<Ataques> ataques = arm.getAtaques();
					for (Ataques atac: ataques) {
						AtaqueVO ataquevo = new AtaqueVO(atac.getIdAtaque(), atac.getNombre(), atac.getAtributoAfectado(), atac.getFactorDaño(), atac.getPorcentajeAcierto(), atac.getJugadorAfectado(), atac.getVecesUso());
						System.out.print("***********Lista de ataques: " + ataquevo.getNombreataque());
						armunica.addAtaques(ataquevo);
					}
					armaduraspersonaje.add(armunica);


				}
				personajelog.setArmasarmaduras(armaduraspersonaje);


				//Añadimos los objetos al personaje VO
				for (Objeto objeto: u.getInventario()) {
					Query query2 = session.createQuery("from ObjetoCantidad where idUser= :iduser and idobjeto= :idobjeto");
					query2.setParameter("idobjeto",objeto.getIdobjeto());
					query2.setParameter("iduser", personajelog.getIduser());            
					ObjetoCantidad objetocantidad = (ObjetoCantidad) query2.uniqueResult();
					ObjetoCantidadVO objcan= new ObjetoCantidadVO(objeto.getIdobjeto(), objeto.getNombre(), objeto.getRareza(), objeto.getTipo(), objeto.getCombo1(), objeto.getCombo2(), objeto.getExito(), objetocantidad.getCantidad());

					listobjetos.add(objcan);
				}
				personajelog.setInventario(listobjetos);				
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return personajelog;
	}

	@Override
	public int EfectuarAtaque(int idataque) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int i = 0;
		float probabilidad = 0;
		Ataques ataque = null;
		try{
			transaction = session.beginTransaction();   
			ataque = (Ataques)session.load(Ataques.class, idataque);
			probabilidad = ataque.getPorcentajeAcierto();
			// En este momento siempre va ser mayor que 0 porque no hacemos ningun UPDATE.
			if(ataque.getVecesUso()>0)
			{
				if (probabilidad >= randInt()) {
					transaction.commit();
					System.out.println("El ataque se va a realizar");
					i=1;
				}
				else{
					System.out.println("El ataque no se va a realizar po culpa de la probabilidad");

				}
			}
			else
			{
				System.out.println("El ataque no se va a realizar porque se te ha gastado");

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return i;
	}



	@Override
	public BatallaVO ResultadoAtaqueVO(int idataque, int idbatallaVO, int idPersonajeVO) throws Exception{

		AtaqueVO ataque = getAtaque(idataque);
		BatallaVO batallaVO = getBatallaVO(idbatallaVO);
		int mod = batallaVO.getTurno() % batallaVO.getListajugadores().size();
		int mod2 = (batallaVO.getTurno()+1) % batallaVO.getListajugadores().size();
		int posicionBatalla = batallaVO.getPosicionPersonajeVO(idPersonajeVO);
		if(VerificarTurno(idbatallaVO, posicionBatalla)==true){
			System.out.print("entramos dentros if VerificarTurno \n");

			if(VerificarAtaque(idataque, idPersonajeVO)==true)
			{
				int i = EfectuarAtaque(ataque.getIdataque());
				//Nos metemos en la funcion EfectuarAtaque para saber si el ataque se va a efectuar(i=1) o no (i=0)
				if (i==1){
					BatallaVO returnBatallaVO = RealizarAtaque(idbatallaVO, idataque, mod, mod2);
					PasaTurno(idbatallaVO);
					return returnBatallaVO;
				}
				//La funcion EfectuarAtaque devuelve 0 por lo que el ataque no se va a efectuar
				else{
					PasaTurno(idbatallaVO);

					return batallaVO;
				}
			}
			else
			{
				throw new NoTienesEseAtaqueException();
			}
		}
		else{
			throw new NoEsTuTurnoException();

		}
	}

	//CAMBIAR HA CLASE BATLLA CON ATRIBUTOS NUEVOS ********************************************************
	@Override
	public String UpdateAtributosBatalla(int iduser, String atributo, float nuevoAtrib) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="update rechazado";
		//      UsrPersonaje usrper = null;
		try{
			transaction = session.beginTransaction();   
			//          usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			Query query = session.createQuery("update UsrPersonaje set "+atributo+"= :uatributo where iduser= :id");
			query.setParameter("id",iduser);
			query.setParameter("uatributo", nuevoAtrib);            
			int result = query.executeUpdate();
			if (result >0 ) {
				transaction.commit();
				System.out.print("Update realizado");
			}

		}

		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		System.out.print("");
		return s;
	}


	@Override
	public void PasaTurno(int idBatallaVO) {

		BatallaVO batallaVO = getBatallaVO(idBatallaVO);
		batallaVO.setTurno(batallaVO.getTurno()+1);
		System.out.println("PasaTurno********************** \n");

	}


	@Override
	public String addbatallafinBBDD(Batalla batalla) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			//colecionUsers.addUser(usuario);
			session.save(batalla);
			System.out.println("Usuario añadido");
			transaction.commit();
			return "Usuario añadido";
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			return "Usuario no añadido";
		}
		finally {
			session.close();
		}
		//Batalla guardada;


	}



	@Override
	public PersonajeVO getPersonaje(int idPersonaje) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje personaje = new UsrPersonaje();
		PersonajeVO personajevo= null;
		try{
			transaction = session.beginTransaction();
			personaje = (UsrPersonaje)session.load(UsrPersonaje.class, idPersonaje);
			if (personaje!= null) {

				personajevo = new PersonajeVO(personaje.getIduser(),personaje.getIdGCM(), personaje.getNombre(), personaje.getVida(), personaje.getDefensa(), personaje.getAtaque(), personaje.getLatitud(), personaje.getLongitud(), personaje.getLaPasta());
				transaction.commit();

			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return personajevo;
	}


	@Override
	public BatallaVO updateAtributosBatallaVO(int idbatallaVO,int idPersonajeActuali, String atributo, float nuevoatributo) {

		BatallaVO batallaVO = getBatallaVO(idbatallaVO);
		PersonajeVO personajeVO = batallaVO.getPersonajeVO(idPersonajeActuali);
		personajeVO.getActualizacionAtributo(atributo, nuevoatributo);

		return batallaVO;
	}



	@Override
	public BatallaVO getBatallaVO(int idBatalla) {

		BatallaVO batallaVO = listbatallas.getBatallaVO(idBatalla); 

		return batallaVO;
	}



	@Override
	public BatallaVO iniciarBatallaVO(ArrayList<PersonajeVO> listaPersonajesVO) {

		BatallaVO batallaVO= (new BatallaVO(listbatallas.getcount()+1, 0, listaPersonajesVO.size(), listaPersonajesVO));
		listbatallas.addBatallaVO(batallaVO);
		return batallaVO;
	}



	@Override
	public PersonajeVO RegistroUser(UsuarioVO userregistrado) {
		//Comprobamos que el nombre del usuario no esta ya cogido

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		PersonajeVO personajeregistrado = null;
		List<ArmaArmaduraVO> armaduraspersonaje = new ArrayList<ArmaArmaduraVO>();
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from UsrPersonaje as u where u.nombre='" + userregistrado.getUsername() + "'");
			UsrPersonaje u = (UsrPersonaje) query.uniqueResult();
			//UsrPersonaje us  = (UsrPersonaje)session.load(UsrPersonaje.class, userlog.getUsername());
			if (u != null) {
				System.out.print("*Usuario ya existente");
				transaction.commit();
			}
			else
			{
				transaction.commit();
				addUsrPersonaje(new UsrPersonaje(userregistrado.getIdGCM(),userregistrado.getEmail(), userregistrado.getPass(), userregistrado.getUsername(),100,10,10, userregistrado.getLatitud(),userregistrado.getLongitud(), 1000));

				//Volvemos a realizar la query para solicitar el id del usuario recien creado
				Query queri = session.createQuery("from UsrPersonaje as u where u.nombre='" + userregistrado.getUsername() + "'");
				UsrPersonaje p = (UsrPersonaje) queri.uniqueResult();

				System.out.print(p.getNombre());

				if (p != null) {


					//le añadimos las armaduras basicas al jugador que se registra
					añadirArmasArmaduras(22, p.getIduser());
					añadirArmasArmaduras(23, p.getIduser());
					añadirArmasArmaduras(24, p.getIduser());
					añadirArmasArmaduras(25, p.getIduser());
					añadirArmasArmaduras(26, p.getIduser());
					añadirArmasArmaduras(27, p.getIduser());

					//Creamos la Clase PersonajeVO para mandarle al usuario todos sus atributos

					UsuarioVO nuevousuariologeado = new UsuarioVO(p.getIdGCM(),p.getNombre(),p.getPassword(), userregistrado.getEmail(), userregistrado.getLatitud(),userregistrado.getLongitud(),userregistrado.getLaPasta());

					personajeregistrado = LoginUser(nuevousuariologeado);

				}


			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return personajeregistrado;

	}



	@Override
	public boolean VerificarTurno(int idBatallaVO, int posicionBatalla) {

		BatallaVO batallaVO = getBatallaVO(idBatallaVO);
		int mod = batallaVO.getTurno() % batallaVO.getNoJugadores();
		System.out.print("mod: "+mod+" posicion: "+posicionBatalla);
		if (mod == posicionBatalla){
			return true;
		}
		else{
			return false;
		}
	}



	@Override
	public BatallaVO RealizarAtaque(int idbatallaVO, int idataque, int mod, int mod2) {

		AtaqueVO ataque = getAtaque(idataque);
		BatallaVO batallaVO = getBatallaVO(idbatallaVO);
		BatallaVO UbatallaVO = getBatallaVO(idbatallaVO);

		if (ataque.getJugadorafectado() ==1) {
			String atributo = ataque.getAtributoafectado();

			switch(atributo){

			case "vida":
				float dañoVida =(((batallaVO.getListajugadores().get(mod).getAtaque()/batallaVO.getListajugadores().get(mod2).getDefensa())*ataque.getFactor())/100)*30;
				System.out.print("Daño vida: "+dañoVida+"\n");
				float nuevaVida=0;
				if (dañoVida>batallaVO.getListajugadores().get(mod2).getVida()){
					nuevaVida=0;
				}
				else{
					nuevaVida = batallaVO.getListajugadores().get(mod2).getVida()-dañoVida;
				}
				System.out.print("Nombre updateatrib: "+batallaVO.getListajugadores().get(mod2).getNombre()+"\n");
				UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod2).getIduser(), "vida", nuevaVida);


			case "ataque":
				float dañoAtaque = (ataque.getFactor()/100)*batallaVO.getListajugadores().get(mod2).getAtaque();
				float nuevoAtaque = batallaVO.getListajugadores().get(mod2).getAtaque()-dañoAtaque;
				UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod2).getIduser(), "ataque", nuevoAtaque);

			case "defensa":
				float dañoDefensa = (ataque.getFactor()/100)*batallaVO.getListajugadores().get(mod2).getDefensa();
				float nuevoDefensa = batallaVO.getListajugadores().get(mod2).getDefensa()-dañoDefensa;
				UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod2).getIduser(), "defensa", nuevoDefensa);

			}

		}
		else{
			String atributo = ataque.getAtributoafectado();

			switch(atributo){
			case "ataque":
				float aumentoAtaque = (ataque.getFactor()/100)*batallaVO.getListajugadores().get(mod).getAtaque();
				float nuevoAtaque = batallaVO.getListajugadores().get(mod).getAtaque()+aumentoAtaque;
				UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod).getIduser(), "ataque", nuevoAtaque);


			case "defensa":
				float aumentoDefensa = (ataque.getFactor()/100)*batallaVO.getListajugadores().get(mod).getDefensa();
				float nuevoDefensa = batallaVO.getListajugadores().get(mod).getDefensa()+aumentoDefensa;
				UbatallaVO= updateAtributosBatallaVO(idbatallaVO, batallaVO.getListajugadores().get(mod).getIduser(), "defensa", nuevoDefensa);

			}

		}
		return UbatallaVO;

	}

	@Override
	public List<PersonajeLogeadoVO> listPersonajes(int idUsersolicita) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<PersonajeLogeadoVO> personajeslogeados = new ArrayList<PersonajeLogeadoVO>();

		try{
			transaction = session.beginTransaction();
			List<UsrPersonaje> u = (List<UsrPersonaje>)session.createQuery("from UsrPersonaje").list();
			PersonajeVO PersonajeSolicita = getPersonaje(idUsersolicita);

			if (u != null) {               
				for (UsrPersonaje userlogeado: u) {

					if (userlogeado.getIduser()!=idUsersolicita)
					{
						PersonajeLogeadoVO p = new PersonajeLogeadoVO(userlogeado.getIduser(), userlogeado.getNombre(), userlogeado.getVida(), userlogeado.getDefensa(), userlogeado.getAtaque(), userlogeado.getLatitud(),userlogeado.getLongitud());
						
						if (distance(PersonajeSolicita.getLatitud(), p.getLat(), PersonajeSolicita.getLongitud(), p.getLng())<=100000000)
						{
							
							personajeslogeados.add(p);
						}
					}

				}
				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return personajeslogeados;
	}
	@Override
	public List<CofreVO> listCofres() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<CofreVO> cofres = new ArrayList<CofreVO>();

		try{
			transaction = session.beginTransaction();
			List<Cofre> u = (List<Cofre>)session.createQuery("from Cofre").list();
			if (u != null) {               
				for (Cofre cof: u) {

					CofreVO p = new CofreVO(cof.getIdcofre(),cof.getLongitud(),cof.getLatitud());
					cofres.add(p);

				}
				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return cofres;
	}


	@Override
	public boolean VerificarAtaque(int idataque, int idPersonajeVO) throws Exception {
		PersonajeVO personajeVO = getPersonaje(idPersonajeVO);
		personajeVO.setAtaques(listaAtaquesUsr(idPersonajeVO));
		if ( personajeVO.getAtaqueVO(idataque) != null){
			return true;
		}
		else{
			return false;
		}
	}



	@Override
	public String addObjeto(Objeto objeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			//colecionUsers.addUser(usuario);
			session.save(objeto);
			transaction.commit();
			return "Objeto añadido";
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			return "Objeto no añadido";
		}
		finally {
			session.close();
		}
	}



	@Override
	public String añadirObjetos(int idobjeto, int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="Objeto rechazado";
		UsrPersonaje usrper = null;
		Objeto objeto = null;
		ObjetoCantidad objetocantidad = new ObjetoCantidad();
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (usrper != null) {
				objeto = (Objeto)session.load(Objeto.class, idobjeto);
				usrper.addInventario(objeto);


				Query query = session.createQuery("from ObjetoCantidad where idUser= :iduser and idobjeto= :idobjeto");
				query.setParameter("idobjeto",idobjeto);
				query.setParameter("iduser", iduser);            
				objetocantidad = (ObjetoCantidad) query.uniqueResult();
				if (objetocantidad !=null ) {

					Query query2 = session.createQuery("update ObjetoCantidad set cantidad= :cantidad where idobjeto= :idobjeto and idUser= :iduser");
					query2.setParameter("cantidad",objetocantidad.getCantidad()+1);
					query2.setParameter("idobjeto",idobjeto);
					query2.setParameter("iduser", iduser);            
					if (query2.executeUpdate() >0 ) {
					}
				}

				else{
					ObjetoCantidad objetocantidad2 = new ObjetoCantidad();
					objetocantidad2.setCantidad(1);
					objetocantidad2.setObjeto(objeto);;
					objetocantidad2.setUsrPersonaje(usrper);;
					session.save(objetocantidad2);

				}


				transaction.commit();
				s="Objeto acceptado";
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}



	@Override
	public List<ObjetoCantidadVO> listaObjetosUsr(int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje u = new UsrPersonaje();
		List<ObjetoCantidadVO> ObjetoVOfin = new ArrayList<ObjetoCantidadVO>();

		try{
			transaction = session.beginTransaction();
			u = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (u != null) {

				////
				List<ObjetoCantidad> inventario = u.getObjetocantidad();              
				for (ObjetoCantidad obj: inventario) {
					System.out.print("obj: "+obj.getObjeto().getNombre() +"\n");
					Query query2 = session.createQuery("from ObjetoCantidad where idUser= :iduser and idobjeto= :idobjeto");
					query2.setParameter("idobjeto",obj.getObjeto().getIdobjeto());
					query2.setParameter("iduser", u.getIduser());            
					ObjetoCantidad objetocantidad = (ObjetoCantidad) query2.uniqueResult();
					ObjetoVOfin.add(new ObjetoCantidadVO(obj.getObjeto().getIdobjeto(), obj.getObjeto().getNombre(), 
							obj.getObjeto().getRareza(), obj.getObjeto().getTipo(), 
							obj.getObjeto().getCombo1(), obj.getObjeto().getCombo2(), 
							obj.getObjeto().getExito(), objetocantidad.getCantidad()));

				}
				////

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return ObjetoVOfin;
	}



	@Override		
	public ObjetoCantidadVO combinacion(int iduser, String objeto1, String objeto2) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		ObjetoCantidadVO objvo= null;
		if(VerificarObjeto(objeto1, iduser)==true && VerificarObjeto(objeto2, iduser)==true){

			try{
				transaction = session.beginTransaction();   
				Query query = session.createQuery("select idobjeto from Objeto where (combo1= :objeto1 and combo2= :objeto2) or (combo1= :objeto2 and combo2= :objeto1)");
				query.setParameter("objeto1",objeto1);
				query.setParameter("objeto2", objeto2);            
				if (query.uniqueResult() != null) {
					int results = (Integer)query.uniqueResult();
					Objeto obj = (Objeto)session.load(Objeto.class, results);
					objvo = new ObjetoCantidadVO(obj.getIdobjeto(), obj.getNombre(), obj.getRareza(), obj.getTipo(), obj.getCombo1(), obj.getCombo2(), obj.getExito(), 1);
					transaction.commit();
				}
				else{
					throw new NoExisteEsaCombinacionException();

				}

			}

			catch(HibernateException e)
			{
				transaction.rollback();
				e.printStackTrace();
			}
			finally {
				session.close();
			}

			return objvo;

		}
		else{
			throw new NoTienesEseObjetoException();

		}
	}

	@Override
	public boolean VerificarObjeto(String nombreObjeto , int idPersonajeVO) throws Exception {
		PersonajeVO personajeVO = getPersonaje(idPersonajeVO);
		personajeVO.setInventario(listaObjetosUsr(idPersonajeVO));
		if ( personajeVO.getObjetoCantidadVO(nombreObjeto) != null){
			System.out.print("Si que lo tiene" + nombreObjeto);
			return true;
		}
		else{

			System.out.print("no lo tiene");
			return false;
		}
	}



	@Override
	public String añadirObjetosCofre(int idobjeto, int idCofre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="Objetocofre rechazado";
		Cofre cofre = null;
		Objeto objeto = null;
		ObjetoCofreCantidad objetocofrecantidad = new ObjetoCofreCantidad();

		try{
			transaction = session.beginTransaction();   
			cofre = (Cofre)session.load(Cofre.class, idCofre);
			if (cofre != null) {
				objeto = (Objeto)session.load(Objeto.class, idobjeto);
				cofre.addlistaobjetos(objeto);;


				Query query = session.createQuery("from ObjetoCofreCantidad where idCofre= :idcofre and idobjeto= :idobjeto");
				query.setParameter("idobjeto",idobjeto);
				query.setParameter("idcofre", idCofre);            
				objetocofrecantidad = (ObjetoCofreCantidad) query.uniqueResult();
				if (objetocofrecantidad !=null ) {

					Query query2 = session.createQuery("update ObjetoCofreCantidad set cantidad= :cantidad where idobjeto= :idobjeto and idCofre= :idcofre");
					query2.setParameter("cantidad",objetocofrecantidad.getCantidad()+1);
					query2.setParameter("idobjeto",idobjeto);
					query2.setParameter("idcofre", idCofre);            
					if (query2.executeUpdate() >0 ) {
					}
				}

				else{
					ObjetoCofreCantidad objetocofrecantidad2 = new ObjetoCofreCantidad();
					objetocofrecantidad2.setCantidad(1);
					objetocofrecantidad2.setNombreObjeto(objeto.getNombre());
					objetocofrecantidad2.setObjeto(objeto);
					objetocofrecantidad2.setCofre(cofre);
					session.save(objetocofrecantidad2);
				}
				transaction.commit();
				s="Objeto cofre acceptado";
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}



	@Override
	public List<ObjetoCofreCantidadVO> listaObjetosCofre(int idCofre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Cofre cofre = new Cofre();
		List<ObjetoCofreCantidadVO> ObjetocofreVOfin = new ArrayList<ObjetoCofreCantidadVO>();

		try{
			transaction = session.beginTransaction();
			cofre = (Cofre)session.load(Cofre.class, idCofre);
			if (cofre != null) {

				////
				List<ObjetoCofreCantidad> inventariocofre = cofre.getObjetoscofrecantidads();              
				for (ObjetoCofreCantidad objcofre: inventariocofre) {
					Query query2 = session.createQuery("from ObjetoCofreCantidad where idCofre= :idcofre and idobjeto= :idobjeto");
					query2.setParameter("idobjeto",objcofre.getObjeto().getIdobjeto());
					query2.setParameter("idcofre",cofre.getIdcofre());            
					ObjetoCofreCantidad objetocofrecantidad = (ObjetoCofreCantidad) query2.uniqueResult();
					ObjetocofreVOfin.add(new ObjetoCofreCantidadVO(objcofre.getIdobjetocofrecantidad(), 
							objetocofrecantidad.getCantidad(), objcofre.getObjeto().getIdobjeto(), 
							objcofre.getCofre().getIdcofre(), objcofre.getObjeto().getNombre()));

				}
				////

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return ObjetocofreVOfin;
	}



	@Override
	public String addCofre(Cofre cofre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			//colecionUsers.addUser(usuario);
			session.save(cofre);
			transaction.commit();
			return "Cofre añadido";
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			return "Cofre no añadido";
		}
		finally {
			session.close();
		}
	}



	@Override
	public String eliminarObjetosCofre(int idUser, int idCofre,
			int idObjeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="No has podido recoger nada";
		UsrPersonaje usrper = null;
		Objeto objeto = null;
		Cofre cofre = null;
		ObjetoCofreCantidad objetocofrecantidad = new ObjetoCofreCantidad();

		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, idUser);
			if (usrper != null) {
				objeto = (Objeto)session.load(Objeto.class, idObjeto);

				Query query = session.createQuery("from ObjetoCofreCantidad where idcofre= :idcofre and idobjeto= :idobjeto");
				query.setParameter("idobjeto",idObjeto);
				query.setParameter("idcofre", idCofre);            
				objetocofrecantidad = (ObjetoCofreCantidad) query.uniqueResult();
				if (objetocofrecantidad !=null ) {

					Query query2 = session.createQuery("update ObjetoCofreCantidad set cantidad= :cantidad where idobjeto= :idobjeto and idcofre= :idcofre");
					query2.setParameter("cantidad",objetocofrecantidad.getCantidad()-1);
					query2.setParameter("idobjeto",idObjeto);
					query2.setParameter("idcofre", idCofre);            
					if (query2.executeUpdate() >0 ) {
					}
					cofre = (Cofre)session.load(Cofre.class, idCofre);
					cofre.removelistaobjetos(objeto);
				}	
				transaction.commit();
				s="Has recogido unos objetos";
			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}



	@Override
	public String recogerObjetosCofre(int idUser, int idCofre, int idObjeto,
			int cantidad) throws Exception {
		if (VerificarCantidadCofre(idCofre, idObjeto, cantidad)==true){
			for(int i=0; i<cantidad; i++)
			{
				eliminarObjetosCofre(idUser, idCofre, idObjeto);
				añadirObjetoInventarioVerificado(idObjeto, idUser);
			}
			limpiezaObjetosCofre();
			return "Has recogido con exito los objetos";

		}
		else{
			throw new NoHayTantosObjetosException();
		}
	}



	@Override
	public boolean VerificarCantidadCofre(int idCofre, int idObjeto, int cantidad) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		ObjetoCofreCantidad objetocofrecantidad = null;
		boolean s = false;
		try{
			Query query = session.createQuery("from ObjetoCofreCantidad where idcofre= :idcofre and idobjeto= :idobjeto");
			query.setParameter("idobjeto",idObjeto);
			query.setParameter("idcofre", idCofre);            
			objetocofrecantidad = (ObjetoCofreCantidad) query.uniqueResult();

			if (objetocofrecantidad.getCantidad() <cantidad){
				s= false;

			}
			else{
				s= true;
			}
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return s;
	}

	@Override
	public String añadirObjetoInventarioVerificado(int idobjeto, int iduser) throws Exception{
		if (VerificarCapacidadInventario(iduser,idobjeto)==true){
			añadirObjetos(idobjeto, iduser);	
			return "Has recogido con exito los objetos";
		}
		else{
			throw new NoTienesEspacioEnInventarioException();

		}
	}



	@Override
	public String limpiezaObjetosCofre() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();   
			//          usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			Query query = session.createQuery("delete from ObjetoCofreCantidad where cantidad= :cantidad");
			query.setParameter("cantidad",0);
			if (query.executeUpdate() >0 ) {
				transaction.commit();
			}

		}

		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return "Limpieza cofres realizada";

	}

	@Override
	public boolean VerificarCapacidadInventario(int idUser, int idObjeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean s= false;
		try{
			transaction = session.beginTransaction();   
			//          usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			Query query = session.createQuery("from ObjetoCantidad where idUser= :idUser");
			query.setParameter("idUser",idUser);
			List<ObjetoCantidad> inventario = query.list();
			Query query2 = session.createQuery("from ObjetoCantidad where idUser= :idUser and idobjeto= :idobjeto");
			query2.setParameter("idUser",idUser);
			query2.setParameter("idobjeto",idObjeto);
			ObjetoCantidad objetoCantidad = (ObjetoCantidad)query2.uniqueResult();

			if (inventario.size() <20 || objetoCantidad !=null) {
				s= true;
				transaction.commit();
			}

		}

		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return s;
	}

	@Override
	public String añadirArmasArmadurasEquipada(int idarmaarmadura, int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="fallo al equipar armadura";
		UsrPersonaje usrper = null;
		ArmasArmaduras armaarmadura=null;
		ArmaArmaduraEquipada equipada= new ArmaArmaduraEquipada();
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (usrper != null) {

				armaarmadura = (ArmasArmaduras)session.load(ArmasArmaduras.class, idarmaarmadura);

				Query query = session.createQuery("from ArmaArmaduraEquipada where idUser= :iduser and idArmasArmaduras= :idArmasArmaduras");
				query.setParameter("idArmasArmaduras",idarmaarmadura);
				query.setParameter("iduser", iduser);            
				equipada = (ArmaArmaduraEquipada) query.uniqueResult();
				if (equipada !=null ) {

					Query query2 = session.createQuery("update ArmaArmaduraEquipada set equipada= :equipada where idArmasArmaduras= :idArmasArmaduras and idUser= :iduser");
					query2.setParameter("equipada",1);
					query2.setParameter("idArmasArmaduras",idarmaarmadura);
					query2.setParameter("iduser", iduser);            
					if (query2.executeUpdate() >0 ) {
						s="Armadura equipada\n";
					}
				}

				else{
					ArmaArmaduraEquipada equipada2 = new ArmaArmaduraEquipada();
					equipada2.setEquipada(1);
					equipada2.setArmasArmaduras(armaarmadura);;
					equipada2.setUsrPersonaje(usrper);;
					session.save(equipada2);
					s="Armadura equipada\n";

				}

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}


		System.out.println(s);
		return s;
	}



	@Override
	public String desequiparArmasArmadurasEquipada(int idarmaarmadura,
			int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="fallo al desequipar armadura";
		UsrPersonaje usrper = null;
		ArmasArmaduras armaarmadura=null;
		ArmaArmaduraEquipada equipada= new ArmaArmaduraEquipada();
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (usrper != null) {

				armaarmadura = (ArmasArmaduras)session.load(ArmasArmaduras.class, idarmaarmadura);

				Query query = session.createQuery("from ArmaArmaduraEquipada where idUser= :iduser and idArmasArmaduras= :idArmasArmaduras");
				query.setParameter("idArmasArmaduras",idarmaarmadura);
				query.setParameter("iduser", iduser);            
				equipada = (ArmaArmaduraEquipada) query.uniqueResult();
				if (equipada !=null ) {

					Query query2 = session.createQuery("update ArmaArmaduraEquipada set equipada= :equipada where idArmasArmaduras= :idArmasArmaduras and idUser= :iduser");
					query2.setParameter("equipada",0);
					query2.setParameter("idArmasArmaduras",idarmaarmadura);
					query2.setParameter("iduser", iduser);            
					if (query2.executeUpdate() >0 ) {
						s="Armadura desequipada\n";

					}
				}

				else{
					ArmaArmaduraEquipada equipada2 = new ArmaArmaduraEquipada();
					equipada2.setEquipada(0);
					equipada2.setArmasArmaduras(armaarmadura);;
					equipada2.setUsrPersonaje(usrper);;
					session.save(equipada2);
					s="Armadura desequipada\n";

				}

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}


		System.out.println(s);
		return s;
	}



	@Override
	public int verificarArmaArmaduraEquipada(int idarmaarmadura, int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="fallo al verificar armadura";
		UsrPersonaje usrper = null;
		ArmaArmaduraEquipada equipada= new ArmaArmaduraEquipada();
		int equipadaarmadura=0;
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (usrper != null) {

				Query query = session.createQuery("from ArmaArmaduraEquipada where idUser= :iduser and idArmasArmaduras= :idArmasArmaduras");
				query.setParameter("idArmasArmaduras",idarmaarmadura);
				query.setParameter("iduser", iduser);            
				equipada = (ArmaArmaduraEquipada) query.uniqueResult();
				if (equipada !=null ) {
					s="farmadura verificada";
					if (equipada.getEquipada()==1)
					{
						equipadaarmadura=1;

					}
					else
					{
						equipadaarmadura=0;
					}
				}

				else{
					equipadaarmadura=0;

				}

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return equipadaarmadura;
	}



	@Override
	public List<ArmaArmaduraVO> listaArmasArmadurasUsr(int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje u = new UsrPersonaje();
		List<ArmaArmaduraVO> ArmaArmaduraVOfin = new ArrayList<ArmaArmaduraVO>();

		try{
			transaction = session.beginTransaction();
			u = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (u != null) {

				////
				List<ArmaArmaduraEquipada> armequipadas = u.getArmaarmaduraequipada();              
				for (ArmaArmaduraEquipada armequip: armequipadas) {
					System.out.print("obj: "+armequip.getArmasArmaduras().getNombre() +"\n");
					Query query2 = session.createQuery("from ArmaArmaduraEquipada where idUser= :iduser and idarmaarmadura= :idarmaarmadura");
					query2.setParameter("idarmaarmadura",armequip.getArmasArmaduras().getIdArmasArmaduras());
					query2.setParameter("iduser", u.getIduser());            
					ArmaArmaduraEquipada armequipadisima = (ArmaArmaduraEquipada) query2.uniqueResult();
					ArmaArmaduraVOfin.add(new ArmaArmaduraVO(armequip.getArmasArmaduras().getIdArmasArmaduras(), armequip.getArmasArmaduras().getNombre(), armequip.getArmasArmaduras().getTipo(), armequip.getArmasArmaduras().getDefensa(),armequipadisima.getEquipada() ,armequipadisima.getArmasArmaduras().getAtaque()));

				}
				////

				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return ArmaArmaduraVOfin;
	}



	@Override
	public PersonajeVO getPersonajeCompleto(int idPersonaje) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje u = new UsrPersonaje();
		PersonajeVO personajevo= null;
		List<ArmaArmaduraVO> armaduraspersonaje = new ArrayList<ArmaArmaduraVO>();
		List<ObjetoCantidadVO> listobjetos = new ArrayList<ObjetoCantidadVO>();
		try{
			transaction = session.beginTransaction();
			u = (UsrPersonaje)session.load(UsrPersonaje.class, idPersonaje);
			if (u != null) {
				transaction.commit();
				personajevo = new PersonajeVO(u.getIduser(),u.getIdGCM(), u.getNombre(), u.getVida(), u.getDefensa(), u.getAtaque(), u.getLatitud(), u.getLongitud(), u.getLaPasta());
				for (ArmasArmaduras arm: u.getArmasarmaduras()) {
					//Sacar armaduras y ataques y pasarselos al personaje

					//miramos si la armadura esta equipada o desequipada
					int equipamiento= verificarArmaArmaduraEquipada(arm.getIdArmasArmaduras(),u.getIduser());

					ArmaArmaduraVO armunica= new ArmaArmaduraVO(arm.getIdArmasArmaduras(), arm.getNombre(), arm.getTipo(), arm.getDefensa(), equipamiento ,arm.getAtaque());
					//sacar ataques
					List<Ataques> ataques = arm.getAtaques();
					for (Ataques atac: ataques) {
						AtaqueVO ataquevo = new AtaqueVO(atac.getIdAtaque(), atac.getNombre(), atac.getAtributoAfectado(), atac.getFactorDaño(), atac.getPorcentajeAcierto(), atac.getJugadorAfectado(), atac.getVecesUso());
						System.out.print("***********Lista de ataques: " + ataquevo.getNombreataque());
						armunica.addAtaques(ataquevo);
					}
					armaduraspersonaje.add(armunica);


				}
				personajevo.setArmasarmaduras(armaduraspersonaje);


				//Añadimos los objetos al personaje VO
				for (Objeto objeto: u.getInventario()) {
					Query query2 = session.createQuery("from ObjetoCantidad where idUser= :iduser and idobjeto= :idobjeto");
					query2.setParameter("idobjeto",objeto.getIdobjeto());
					query2.setParameter("iduser", personajevo.getIduser());            
					ObjetoCantidad objetocantidad = (ObjetoCantidad) query2.uniqueResult();
					ObjetoCantidadVO objcan= new ObjetoCantidadVO(objeto.getIdobjeto(), objeto.getNombre(), objeto.getRareza(), objeto.getTipo(), objeto.getCombo1(), objeto.getCombo2(), objeto.getExito(), objetocantidad.getCantidad());

					listobjetos.add(objcan);
				}
				personajevo.setInventario(listobjetos);				
			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return personajevo;
	}


	@Override
	public ObjetoCantidadVO combinacionFinal(int iduser, String objeto1, String objeto2)
			throws Exception {

		ObjetoCantidadVO objetocombo = combinacion(iduser, objeto1, objeto2);
		int cantidad1 = getObjeto(objeto1, iduser).getCantidad();
		int cantidad2 = getObjeto(objeto2, iduser).getCantidad();

		if ((VerificarCapacidadInventario(iduser,objetocombo.getIdobjeto())==true) || (cantidad1 ==1 && cantidad2 ==1)){
			eliminarObjetosInventario(iduser, getObjeto(objeto1, iduser).getIdobjeto());
			eliminarObjetosInventario(iduser, getObjeto(objeto2, iduser).getIdobjeto());
			limpiezaObjetosInventario();
			añadirObjetos(objetocombo.getIdobjeto(), iduser);	
			return objetocombo;
		}
		else{
			throw new NoTienesEspacioEnInventarioException();

		}			

	}

	@Override
	public String eliminarObjetosInventario(int idUser, int idObjeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="No has podido recoger nada";
		UsrPersonaje usrper = null;
		Objeto objeto = null;
		ObjetoCantidad objetocantidad = new ObjetoCantidad();

		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, idUser);
			if (usrper != null) {
				objeto = (Objeto)session.load(Objeto.class, idObjeto);
				System.out.print("tipo: "+objeto.getTipo()+"\n");
				Query query = session.createQuery("from ObjetoCantidad where idUser= :idUser and idobjeto= :idobjeto");
				query.setParameter("idobjeto",idObjeto);
				query.setParameter("idUser", idUser);            
				objetocantidad = (ObjetoCantidad) query.uniqueResult();
				if (objetocantidad !=null) {
					if(objeto.getTipo().equals("herramienta")==true){
						System.out.print("entramos dentro del if herramienta\n");

					}
					else{
						System.out.print("entramos dentro del else herramienta\n");
						Query query2 = session.createQuery("update ObjetoCantidad set cantidad= :cantidad where idobjeto= :idobjeto and idUser= :idUser");
						query2.setParameter("cantidad",objetocantidad.getCantidad()-1);
						query2.setParameter("idobjeto",idObjeto);
						query2.setParameter("idUser", idUser);            
						if (query2.executeUpdate() >0 ) {
						}
						usrper.removeinventario(objeto);
					}
				}

				transaction.commit();
				s="Has eliminado unos objtos de tu inventarios";
			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return s;
	}

	@Override
	public String limpiezaObjetosInventario() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();   
			//          usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			Query query = session.createQuery("delete from ObjetoCantidad where cantidad= :cantidad");
			query.setParameter("cantidad",0);
			if (query.executeUpdate() >0 ) {
				transaction.commit();
			}

		}

		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return "Limpieza inventarios realizada";

	}

	@Override
	public ObjetoCantidadVO getObjeto(String objeto, int idUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		ObjetoCantidadVO objcanVO= null;		
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Objeto where nombre= :nombre");
			query.setParameter("nombre",objeto);
			Objeto obj = (Objeto) query.uniqueResult();	

			Query query2 = session.createQuery("from ObjetoCantidad where idUser= :iduser and idobjeto= :idobjeto");
			query2.setParameter("idobjeto",obj.getIdobjeto());
			query2.setParameter("iduser",idUser);            
			ObjetoCantidad objetocantidad = (ObjetoCantidad) query2.uniqueResult();

			if (objetocantidad!= null) {
				objcanVO= new ObjetoCantidadVO(obj.getIdobjeto(), obj.getNombre(), obj.getRareza(), obj.getTipo(), obj.getCombo1(), obj.getCombo2(), obj.getExito(), objetocantidad.getCantidad());
				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return objcanVO;
	}


	@Override
	public boolean VerificarDistanciaCofre(int iduser, int idcofre) {

		PersonajeVO pers = getPersonaje(iduser);
		CofreVO cofre = getCofre(idcofre);
		boolean verif=false;
		if (distance(pers.getLatitud(), cofre.getLatitud(), pers.getLongitud(), cofre.getLongitud())<=100){
			verif = true;
		}
		else{
			throw new NoEstasSuficientementeCercaException();

		}	
		return verif;
	}


	@Override
	public CofreVO getCofre(int idcofre) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Cofre cofre = new Cofre();
		CofreVO cofreVO= null;
		try{
			transaction = session.beginTransaction();
			cofre = (Cofre)session.load(Cofre.class, idcofre);
			if (cofre!= null) {

				cofreVO = new CofreVO(idcofre, cofre.getLatitud(), cofre.getLongitud());
				transaction.commit();

			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return cofreVO;
	}


	@Override
	public void updateAtributosEquipada_UserArmasArmaduras(int idarmaarmadura, int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje usrper = null;
		ArmasArmaduras armaarmadura = null;
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			armaarmadura = (ArmasArmaduras)session.load(ArmasArmaduras.class, idarmaarmadura);			

			//          updateAtributos(armaarmadura, iduser);
			try{
				Query query = session.createQuery("update UsrPersonaje set ataque= :uataque" +", defensa= :udefensa" +"  where iduser= :id");
				query.setParameter("id",iduser);
				query.setParameter("uataque", usrper.getAtaque()+armaarmadura.getAtaque());
				query.setParameter("udefensa", usrper.getDefensa()+armaarmadura.getDefensa());
				int result = query.executeUpdate();
				if (result >0 ) {
					//              transaction.commit();
					System.out.print("Update realizado , ataque (+" + armaarmadura.getAtaque()+ ") , defensa(+"+armaarmadura.getDefensa()+")\n" );
				}
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			}
			finally {
				System.out.print("finally\n");
			}


			session.saveOrUpdate(usrper);

			if (usrper != null) {
				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}


	@Override
	public void updateAtributosDesequipada_UserArmasArmaduras(int idarmaarmadura, int iduser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		UsrPersonaje usrper = null;
		ArmasArmaduras armaarmadura = null;
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			armaarmadura = (ArmasArmaduras)session.load(ArmasArmaduras.class, idarmaarmadura);			

			//          updateAtributos(armaarmadura, iduser);
			try{
				Query query = session.createQuery("update UsrPersonaje set ataque= :uataque" +", defensa= :udefensa" +"  where iduser= :id");
				query.setParameter("id",iduser);
				query.setParameter("uataque", usrper.getAtaque()-armaarmadura.getAtaque());
				query.setParameter("udefensa", usrper.getDefensa()-armaarmadura.getDefensa());
				int result = query.executeUpdate();
				if (result >0 ) {
					//              transaction.commit();
					System.out.print("Update realizado , ataque (-" + armaarmadura.getAtaque()+ ") , defensa(-"+armaarmadura.getDefensa()+")\n" );
				}
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			}
			finally {
				System.out.print("finally\n");
			}


			session.saveOrUpdate(usrper);

			if (usrper != null) {
				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}


	@Override
	public String CambiarPosicionUser(int iduser, double latitud,
			double longitud) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="update posicion rechazado";
		try{
			transaction = session.beginTransaction(); 
			System.out.print("Ubicacion de user "+iduser+", latitud: "+latitud+" longitud: "+longitud);

			Query query = session.createQuery("update UsrPersonaje set latitud= :latitud, longitud= :longitud where iduser= :id");
			query.setParameter("id",iduser);
			query.setParameter("latitud", latitud);   
			query.setParameter("longitud", longitud);           

			int result = query.executeUpdate();
			if (result >0 ) {
				transaction.commit();
				System.out.print("Update posicion realizado");
			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}

		return s;

	}


	@Override
	public String crearPeticion(int idatacante , int iddefensor) {
		// TODO Auto-generated method stub
		String solicitud="Solicitud enviada";
		int verificar;
		verificar= comprovacion_solo_una_atacante(idatacante);
		if (verificar==1)
		{

			System.out.print("Creando peticion idatacante: " + idatacante+ " iddefensor: " + iddefensor);
			PeticionBatallaVO peticion= (new PeticionBatallaVO(idatacante, iddefensor));
			listpeticiones.addPeticionVO(peticion);
		}
		else
		{
			solicitud = "Estas pendiente de una batalla";
		}
		return solicitud;
	}


	@Override
	public int comprovacion_solo_una_atacante(int idatacante) {
		// TODO Auto-generated method stub
		int verificacion=0;
		PeticionBatallaVO peticion = listpeticiones.getPeticionAtacanteVO(idatacante); 
		if (peticion==null)
		{
			System.out.print("El atacante no esta en ninguna batalla puede hacer una peticion\n");
			verificacion=1;
		}
		return verificacion;
	}


	@Override
	public PeticionBatallaVO comprovacion_peticion(int iddefensor) {
		// TODO Auto-generated method stub
		PeticionBatallaVO peticion = listpeticiones.getPeticionDefensorVO(iddefensor); 
		return peticion;
	}


	@Override
	public BatallaVO aceptarPeticion(int iddefensor) {
		// TODO Auto-generated method stub
		PeticionBatallaVO peticion;
		BatallaVO batallaaceptada= new BatallaVO();
		ArrayList<PersonajeVO> listaPersonajesVO = new ArrayList<PersonajeVO>();
		peticion= comprovacion_peticion(iddefensor);
		if (peticion!=null)
		{
			peticion.setAceptada("Si");
			System.out.print("Aceptando peticion idatacante: " + peticion.getIddefensor()+ " iddefensor: " + peticion.getIdatacante());

			listaPersonajesVO.add(getPersonaje(peticion.getIddefensor()));
			listaPersonajesVO.add(getPersonaje(peticion.getIdatacante()));
			batallaaceptada = iniciarBatallaVO(listaPersonajesVO);
		}
		else
		{
			System.out.print("No hay ninguna peticion para este usuario");
		}

		return batallaaceptada;
	}
	
	public String comprobarPeticion(int iddefensor) {
		// TODO Auto-generated method stub
		PeticionBatallaVO peticion;
		String Comprobacion="No";
		peticion= comprovacion_peticion(iddefensor);
		if (peticion!=null)
		{
			Comprobacion="Si";
			System.out.print("Comprobando peticion idatacante: " + peticion.getIddefensor()+ " iddefensor: " + peticion.getIdatacante());

		}
		else
		{
			System.out.print("No hay ninguna peticion para este usuario");
		}

		return Comprobacion;
	}

	@Override
	public int devolvemosaIDuser(String nombrePersonaje) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int iduser= 0;
		try{
			transaction = session.beginTransaction();
			Query query = session.createQuery("select iduser from UsrPersonaje where nombre= :nombre");
			query.setParameter("nombre",nombrePersonaje);
			iduser = (int)query.uniqueResult();	

			if (iduser!= 0) {

				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return iduser;
	}

	@Override
	public BatallaVO UtilizarObjeto(int idbatallaVO, String nombreObj, int mod) {

		BatallaVO batallaVO = getBatallaVO(idbatallaVO);
		BatallaVO UbatallaVO = getBatallaVO(idbatallaVO);
		int idUser = batallaVO.getListajugadores().get(mod).getIduser();
		ObjetoCantidadVO objcanVO = getObjeto(nombreObj, idUser);


		String atributo = objcanVO.getTipo();

		switch(atributo){

		case "vida":
			float dañoVida =objcanVO.getRareza()/10;
			System.out.print("Daño vida: "+dañoVida+"\n");
			float nuevaVida = batallaVO.getListajugadores().get(mod).getVida()+dañoVida;
			if (nuevaVida>100){
				nuevaVida=100;
			}
			UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod).getIduser(), "vida", nuevaVida);


		case "ataque":
			float dañoAtaque = objcanVO.getRareza()/10;
			float nuevoAtaque = batallaVO.getListajugadores().get(mod).getAtaque()+dañoAtaque;
			UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod).getIduser(), "ataque", nuevoAtaque);

		case "defensa":
			float dañoDefensa = objcanVO.getRareza()/10;
			float nuevoDefensa = batallaVO.getListajugadores().get(mod).getDefensa()+dañoDefensa;
			UbatallaVO= updateAtributosBatallaVO(idbatallaVO,batallaVO.getListajugadores().get(mod).getIduser(), "defensa", nuevoDefensa);

		}



		return UbatallaVO;

	}

	@Override
	public BatallaVO ResultadoUtilizarObjetoVO(String nombreObj, int idbatallaVO, int idPersonajeVO) throws Exception{

		BatallaVO batallaVO = getBatallaVO(idbatallaVO);
		int mod = batallaVO.getTurno() % batallaVO.getListajugadores().size();
		int posicionBatalla = batallaVO.getPosicionPersonajeVO(idPersonajeVO);

		int idUser = batallaVO.getListajugadores().get(mod).getIduser();
		ObjetoCantidadVO objcanVO = getObjeto(nombreObj, idUser);

		if(VerificarTurno(idbatallaVO, posicionBatalla)==true){
			System.out.print("entramos dentros if VerificarTurno \n");

			if(VerificarObjeto(nombreObj, idPersonajeVO)==true)
			{
				int i = EfectuarObjeto(objcanVO.getIdobjeto());
				//Nos metemos en la funcion EfectuarAtaque para saber si el ataque se va a efectuar(i=1) o no (i=0)
				if (i==1){
					BatallaVO returnBatallaVO = UtilizarObjeto(idbatallaVO, nombreObj, mod);
					PasaTurno(idbatallaVO);
					return returnBatallaVO;
				}
				//La funcion EfectuarAtaque devuelve 0 por lo que el ataque no se va a efectuar
				else{
					PasaTurno(idbatallaVO);

					return batallaVO;
				}
			}
			else
			{
				throw new NoTienesEseObjetoException();
			}
		}
		else{
			throw new NoEsTuTurnoException();

		}
	}

	@Override
	public int EfectuarObjeto(int idobjeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		int i = 0;
		float probabilidad = 0;
		Objeto objeto = null;
		try{
			transaction = session.beginTransaction();   
			objeto = (Objeto)session.load(Objeto.class, idobjeto);
			probabilidad = objeto.getExito();
			// En este momento siempre va ser mayor que 0 porque no hacemos ningun UPDATE.
			if (probabilidad >= randInt()) {
				transaction.commit();
				System.out.println("El ataque se va a realizar");
				i=1;
			}
			else{
				System.out.println("El ataque no se va a realizar po culpa de la probabilidad");

			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return i;
	}


	@Override
	public String addEntradaMercado(int idUser, int idObjeto, int cantidad,
			int precioUnidad) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="entrada mercado rechazado";
		UsrPersonaje usr = null;
		Objeto obj = null;
		try{
			transaction = session.beginTransaction();   
			usr = (UsrPersonaje)session.load(UsrPersonaje.class, idUser);
			obj = (Objeto)session.load(Objeto.class, idObjeto);
			
			ObjetoCantidadVO objCantidadVO= getObjeto(obj.getNombre(), idUser);
			if(objCantidadVO != null){
				if (objCantidadVO.getCantidad()>=cantidad){
					
					Mercado mercado = new Mercado();
					mercado.setCantidad(cantidad);
					mercado.setObjeto(obj);
					mercado.setPrecioUnidad(precioUnidad);
					mercado.setPersonaje(usr);
					session.save(mercado);
		
					if (usr != null || obj != null) {
						for(int i=0;i<cantidad;i++){
							eliminarObjetosInventario(idUser, idObjeto);
						}
						limpiezaObjetosInventario();
						transaction.commit();
						s="entrada mercado acceptado";
					}
				}
				else{
					throw new NoTienesTantaCantidadDeEseObjetoException();
				}
			}
			else{
				throw new NoTienesEseObjetoException();
			}
			

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}
	
	@Override
	public MercadoVO getMercadoVO(int idEntradaMercado) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Mercado mercado = new Mercado();
		MercadoVO mercadoVO= null;
		try{
			transaction = session.beginTransaction();
			mercado = (Mercado)session.load(Mercado.class, idEntradaMercado);
			if (mercado!= null) {
				
				PersonajeVO persVO = getPersonaje(mercado.getPersonaje().getIduser());
				ObjetoVO objVO = getObjeto( mercado.getObjeto().getIdobjeto());
				mercadoVO = new MercadoVO(mercado.getIdEntradaMercado(), mercado.getCantidad(), mercado.getPrecioUnidad(),persVO,objVO );
				transaction.commit();

			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
			throw new NoExisteEsaEntradaException();

		}
		finally {
			session.close();
		}
		return mercadoVO;
	}

	@Override
	public String compraObjetoMercado(int idEntradaMercado, int cantidad, int idUserCompador)throws Exception {
		
		MercadoVO mercadoVO = getMercadoVO(idEntradaMercado);
		int TotalPrecioCompra = mercadoVO.getPrecioUnidad() * cantidad;
		String s;
		if(VerificarCantidadCompra(idEntradaMercado, cantidad)==true){
			if (VerificarCompra(TotalPrecioCompra, idUserCompador)==true){
				if(VerificarCapacidadInventario(idUserCompador, mercadoVO.getObjeto().getIdobjeto())==true){	
					s= compraObjetoUser(idEntradaMercado, cantidad, idUserCompador);
					for(int i=0; i<cantidad; i++)
					{

						añadirObjetoInventarioVerificado(mercadoVO.getObjeto().getIdobjeto(), idUserCompador);
					}
					limpiezaEntradaMercado();
				}
				else{
					throw new NoTienesEspacioEnInventarioException();
				}
			}
			else{
				throw new NoEstasSuficientementeCercaException();
			}
		}
		else{
			throw new NoHayTantosObjetosException();
		}

		return s;
	}


	@Override
	public boolean VerificarCompra(int totalCompra, int idUser)
			throws Exception {
		boolean verif = false;
		PersonajeVO perso = getPersonaje(idUser);
		if (perso.getLaPasta()>=totalCompra){
			verif = true;
			
		}
		else{
			throw new NoHayTantaPastaEnTusBolsillosException();

		}
		return verif;
		
	}


	@Override
	public String compraObjetoUser(int idEntradaMercado, int cantidad,
			int idUserCompador) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="No has podido comprar los objetos";
		UsrPersonaje usrper = null;
		Mercado mercado = null;
		Objeto objeto = null;

		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, idUserCompador);
			if (usrper != null) {
				mercado = (Mercado)session.load(Mercado.class, idEntradaMercado);

				Query query = session.createQuery("update Mercado set cantidad= :cantidad where idEntradaMercado= :idEntradaMercado");
				query.setParameter("idEntradaMercado",idEntradaMercado);
				query.setParameter("cantidad",mercado.getCantidad()-cantidad);

				if (query.executeUpdate() >0) {

					Query query2 = session.createQuery("update UsrPersonaje set laPasta= :laPasta where iduser= :iduser");
					query2.setParameter("laPasta",usrper.getLaPasta()-mercado.getPrecioUnidad()*cantidad);
					query2.setParameter("iduser",idUserCompador);
					if (query2.executeUpdate() >0 ) {

					}
					Query query3 = session.createQuery("update UsrPersonaje set laPasta= :laPasta where iduser= :iduser");
					query3.setParameter("laPasta",mercado.getPersonaje().getLaPasta()+mercado.getPrecioUnidad()*cantidad);
					query3.setParameter("iduser",mercado.getPersonaje().getIduser());
					if (query3.executeUpdate() >0 ) {

					}
				}	
				transaction.commit();
				s="Has podido comprar los objetos";
			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		System.out.println(s);
		return s;
	}

	@Override
	public boolean VerificarCantidadCompra(int idEntradaMercado, int cantidad) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Mercado mercado = null;
		boolean s = false;
		try{
			Query query = session.createQuery("from Mercado where idEntradaMercado= :idEntradaMercado");
			query.setParameter("idEntradaMercado",idEntradaMercado);
			mercado = (Mercado) query.uniqueResult();


			if (mercado.getCantidad() <cantidad){
				s= false;
			}
			else{
				s= true;
			}
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return s;
	}


	@Override
	public String limpiezaEntradaMercado() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();   
			//          usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			Query query = session.createQuery("delete from Mercado where cantidad= :cantidad");
			query.setParameter("cantidad",0);
			if (query.executeUpdate() >0 ) {
				transaction.commit();
			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return "Limpieza mercado realizada";
	}


	@Override
	public ObjetoVO getObjeto(int idObjeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Objeto obj = new Objeto();
		ObjetoVO objVO= null;
		try{
			transaction = session.beginTransaction();
			obj = (Objeto)session.load(Objeto.class, idObjeto);
			if (obj!= null) {

				objVO = new ObjetoVO(idObjeto, obj.getNombre(), obj.getRareza(), obj.getTipo(), obj.getCombo1(), obj.getCombo2(), obj.getExito());
				transaction.commit();

			}
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return objVO;
	}


	@Override
	public List<MercadoVO> listaMercado() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<MercadoVO> entradasMercado = new ArrayList<MercadoVO>();
		System.out.print("Usuario solicita entradas mercado\n" );

		try{
			transaction = session.beginTransaction();
			List<Mercado> m = (List<Mercado>)session.createQuery("from Mercado").list();
			if (m!= null) {               
				for (Mercado merc: m) {

					PersonajeVO persVO = getPersonaje(merc.getPersonaje().getIduser());
					ObjetoVO objVO = getObjeto(merc.getObjeto().getIdobjeto());
					MercadoVO mVO = new MercadoVO(merc.getIdEntradaMercado(), merc.getCantidad(), merc.getPrecioUnidad(), persVO, objVO);
					entradasMercado.add(mVO);

				}
				transaction.commit();
			}

		}
		catch(HibernateException e)
		{
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return entradasMercado;
	}


	@Override
	public PeticionBatallaVO verificar_aceptacion (int idatacante) {
		// TODO Auto-generated method stub
		PeticionBatallaVO peticion = listpeticiones.getPeticionAtacanteVO(idatacante); 
		return peticion;
	}


	@Override
	public BatallaVO aceptarPeticion_atacante(int atacante) {
		// TODO Auto-generated method stub
		PeticionBatallaVO peticion;
		BatallaVO batallaaceptada= new BatallaVO();
		peticion= comprovacion_peticion_atacante(atacante);
		if (peticion!=null)
		{
			
			System.out.print("Atacante tambien comienza la batalla\n");
			System.out.print("Aceptando peticion idatacante: " + peticion.getIddefensor()+ " iddefensor: " + peticion.getIdatacante()+ "\n");
			//sacar la batalla que ya esta inicializada
			batallaaceptada= listbatallas.getBatallaVO_byIdAtacante(atacante);
			
			if (peticion.getAceptada().equals("Si"))
			{
				peticion.setAceptada("No");
				eliminar_peticion(atacante);
				System.out.print("Peticion eliminada , id: "+atacante + " estado de peticion: "+peticion.getAceptada()+"\n");
			}
		}
		else
		{
			System.out.print("No hay ninguna peticion para este usuario\n");
		}

		return batallaaceptada;
	}


	@Override
	public PeticionBatallaVO comprovacion_peticion_atacante(int idatacante) {
		PeticionBatallaVO peticion = listpeticiones.getPeticionAtacanteVO(idatacante); 
		return peticion;
	}


	@Override
	public void eliminar_peticion(int idatacante) {
		// TODO Auto-generated method stub
		PeticionBatallaVO peticion = listpeticiones.getPeticionAtacanteVO(idatacante); 
	    listpeticiones.remove_peticion(peticion);
	}
}
