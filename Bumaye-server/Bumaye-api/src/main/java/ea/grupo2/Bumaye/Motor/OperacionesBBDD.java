package ea.grupo2.Bumaye.Motor;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;










import org.apache.commons.collections.iterators.ArrayListIterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;










import ea.grupo2.Bumaye.ClasesVO.ArmaArmaduraVO;
import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.ListBatallasVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeLogeadoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.hibernate.HibernateUtil;
import ea.grupo2.Bumaye.pojos.ArmasArmaduras;
import ea.grupo2.Bumaye.pojos.Ataques;
import ea.grupo2.Bumaye.pojos.Batalla;
import ea.grupo2.Bumaye.pojos.Objeto;
import ea.grupo2.Bumaye.pojos.UsrPersonaje;

public class OperacionesBBDD implements BumayeInterface{    

	ListBatallasVO listbatallas = ListBatallasVO.getInstance();

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

		/* OBJETOS  */
		//(nombre objeto, tipo, rareza, combo1, combo2, %exito)

		m.addObjeto(new Objeto ("Poción", "vida", 40, "Hierba", "Seta",90));
		m.addObjeto(new Objeto ("Hierba", null, 10, null, null,100));
		m.addObjeto(new Objeto ("Seta", null, 10, null, null,100));

		m.addObjeto(new Objeto ("Elixir", "ataque", 50, "Poción", "Runa roja",60));
		m.addObjeto(new Objeto ("Runa roja", null, 70, "Piedra", "Seta roja",70));
		m.addObjeto(new Objeto ("Piedra", null, 5, null, null,100));
		m.addObjeto(new Objeto ("Seta roja", null, 30, null, null,100));

		m.addObjeto(new Objeto ("Caldo", "defensa", 50, "Poción", "Runa azul",60));
		m.addObjeto(new Objeto ("Runa azul", null, 70, "Piedra", "Seta azul",70));
		m.addObjeto(new Objeto ("Seta azul", null, 30, null, null,100));

		m.addObjeto(new Objeto ("Min Bronce", null, 5, null, null,100));
		m.addObjeto(new Objeto ("Min Oro", null, 5, null, null,100));
		m.addObjeto(new Objeto ("Min Drag", null, 5, null, null,100));

		m.addObjeto(new Objeto ("Barra Bronce", null, 50, "Min Bronce", "Herreria",70));
		m.addObjeto(new Objeto ("Barra Oro", null, 50, "Min Oro", "Herreria",60));
		m.addObjeto(new Objeto ("Barra Drag", null, 50, "Min Drag", "Herreria",50));

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
		m.addUsrPersonaje(new UsrPersonaje("albert@hotmail.com",null,"spot","Spot",100,10,10));
		m.addUsrPersonaje(new UsrPersonaje("eianr@hotmail.com",null,"1234","Elcolmo",100,10,10));
		m.addUsrPersonaje(new UsrPersonaje("juan@hotmail.com",null,"1234","Ilcapone",100,10,10));
		System.out.print("Jugadores añadidos");

		      /* AÑADIR OBJETOS A PERSONAJES */
		      m.añadirObjetos(2, 1);
		      m.añadirObjetos(3, 1);
		      m.añadirObjetos(6, 1);
		      m.añadirObjetos(7, 1);
		      m.añadirObjetos(9, 1);
		      m.añadirObjetos(10, 1);
		      m.añadirObjetos(11, 1);
		      m.añadirObjetos(13, 1);
		      m.añadirObjetos(16, 1);
		      System.out.print("Objetos añadias a jugador1");
		      
		      m.añadirObjetos(2, 2);
		      m.añadirObjetos(3, 2);
		      m.añadirObjetos(6, 2);
		      m.añadirObjetos(7, 2);
		      m.añadirObjetos(9, 2);
		      m.añadirObjetos(10, 2);
		      m.añadirObjetos(11, 2);
		      m.añadirObjetos(13, 2);
		      m.añadirObjetos(16, 2);
		      System.out.print("Objetos añadias a jugador2");
		      
		      m.añadirObjetos(2, 3);
		      m.añadirObjetos(3, 3);
		      m.añadirObjetos(6, 3);
		      m.añadirObjetos(7, 3);
		      m.añadirObjetos(9, 3);
		      m.añadirObjetos(10, 3);
		      m.añadirObjetos(11, 3);
		      m.añadirObjetos(13, 3);
		      m.añadirObjetos(16, 3);
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

		m.añadirArmasArmaduras(20, 3);
		m.añadirArmasArmaduras(16, 3);
		m.añadirArmasArmaduras(17, 3);
		m.añadirArmasArmaduras(18, 3);
		m.añadirArmasArmaduras(19, 3);
		m.añadirArmasArmaduras(21, 3);
		System.out.print("Armaduras añadias a jugador3");

		//      //Ilcapone pide batalla a Elcolmo
		//      m.iniciarbatalla(new Batalla(1,2,2));
		//      System.out.print("Jugadores listos para la batalla");
		//      
		//Elcolmo usa mazazo
		//m.batalla(1);
	}



	public static int randInt() {

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
			//          updateAtributos(armaarmadura, iduser);
			try{
				Query query = session.createQuery("update UsrPersonaje set ataque= :uataque" +", defensa= :udefensa" +"  where iduser= :id");
				query.setParameter("id",iduser);
				query.setParameter("uataque", usrper.getAtaque()+armaarmadura.getAtaque());
				query.setParameter("udefensa", usrper.getDefensa()+armaarmadura.getDefensa());
				int result = query.executeUpdate();
				if (result >0 ) {
					//              transaction.commit();
					System.out.print("Update realizado");
				}
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			}
			finally {
				System.out.print("finally");
			}


			session.saveOrUpdate(usrper);

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
		List<ObjetoVO> listobjetos = new ArrayList<ObjetoVO>();
		try{
			transaction = session.beginTransaction();
			System.out.print("Usuario: " + userlog.getUsername() + " Contraseña : " + userlog.getPass());
			Query query = session.createQuery("from UsrPersonaje as u where u.nombre='" + userlog.getUsername() + "' and u.password='" + userlog.getPass()+ "'");
			UsrPersonaje u = (UsrPersonaje) query.uniqueResult();
			//UsrPersonaje us  = (UsrPersonaje)session.load(UsrPersonaje.class, userlog.getUsername());
			if (u != null) {
				transaction.commit();
				personajelog = new PersonajeVO(u.getIduser(),u.getIdGCM(), u.getNombre(), u.getVida(), u.getDefensa(), u.getAtaque());
				for (ArmasArmaduras arm: u.getArmasarmaduras()) {
					//Sacar armaduras y ataques y pasarselos al personaje
					ArmaArmaduraVO armunica= new ArmaArmaduraVO(arm.getIdArmasArmaduras(), arm.getNombre(), arm.getTipo(), arm.getDefensa(), arm.getAtaque());
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
					//Sacar armaduras y ataques y pasarselos al personaje
					ObjetoVO obj= new ObjetoVO(objeto.getIdobjeto(), objeto.getNombre(), objeto.getRareza(), objeto.getTipo(), objeto.getCombo1(), objeto.getCombo2(), objeto.getExito());
					
					listobjetos.add(obj);
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
		System.out.println("Usuario logeado");
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
		// TODO Auto-generated method stub

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

				personajevo = new PersonajeVO(personaje.getIduser(),personaje.getIdGCM(), personaje.getNombre(), personaje.getVida(), personaje.getDefensa(), personaje.getAtaque());
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
		// TODO Auto-generated method stub
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
				addUsrPersonaje(new UsrPersonaje(userregistrado.getEmail(),userregistrado.getIdGCM(), userregistrado.getPass(), userregistrado.getUsername(),100,10,10));

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

					UsuarioVO nuevousuariologeado = new UsuarioVO(p.getNombre(),p.getIdGCM(),p.getPassword(), userregistrado.getEmail());

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
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<PersonajeLogeadoVO> personajeslogeados = new ArrayList<PersonajeLogeadoVO>();
		System.out.print("Usuario con id: " + idUsersolicita + " solicita lista de jugadores\n" );

		try{
			transaction = session.beginTransaction();
			List<UsrPersonaje> u = (List<UsrPersonaje>)session.createQuery("from UsrPersonaje").list();
			if (u != null) {               
				for (UsrPersonaje userlogeado: u) {

					if (userlogeado.getIduser()!=idUsersolicita)
					{

						System.out.print("Lista de usuarios: " + userlogeado.getIduser() + "\n");
						PersonajeLogeadoVO p = new PersonajeLogeadoVO(userlogeado.getIduser(), userlogeado.getNombre(), userlogeado.getVida(), userlogeado.getDefensa(), userlogeado.getAtaque());
						personajeslogeados.add(p);
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
	public boolean VerificarAtaque(int idataque, int idPersonajeVO) throws Exception {
		PersonajeVO personajeVO = getPersonaje(idPersonajeVO);
		personajeVO.setAtaques(listaAtaquesUsr(idPersonajeVO));
		if ( personajeVO.getAtaqueVO(idataque) != null){
			System.out.print("entramos dentro del if de verificar ataque \n");
			return true;
		}
		else{
			System.out.print("entramos dentro del else de verificar ataque \n");

			return false;
		}
	}



	@Override
	public String addObjeto(Objeto objeto) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			//colecionUsers.addUser(usuario);
			session.save(objeto);
			System.out.println("Objeto añadido");
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
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String s="Objeto rechazado";
		UsrPersonaje usrper = null;
		Objeto objeto = null;
		try{
			transaction = session.beginTransaction();   
			usrper = (UsrPersonaje)session.load(UsrPersonaje.class, iduser);
			if (usrper != null) {
				objeto = (Objeto)session.load(Objeto.class, idobjeto);
				usrper.addInventario(objeto);
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


}
