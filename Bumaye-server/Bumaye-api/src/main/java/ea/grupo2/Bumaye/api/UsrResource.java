package ea.grupo2.Bumaye.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.CombinacionVO;
import ea.grupo2.Bumaye.ClasesVO.EquipamientoVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCofreCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeLogeadoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.PuntoVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.Motor.BumayeInterface;
import ea.grupo2.Bumaye.Motor.NoExisteEsaCombinacionException;
import ea.grupo2.Bumaye.Motor.NoHayTantosObjetosException;
import ea.grupo2.Bumaye.Motor.NoTienesEseObjetoException;
import ea.grupo2.Bumaye.Motor.NoTienesEspacioEnInventarioException;
import ea.grupo2.Bumaye.Motor.OperacionesBBDD;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/user")
public class UsrResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@POST
	@Consumes(MediaType.API_USER)
	@Produces(MediaType.API_PERSONAJE)
	public PersonajeVO UserLogin (UsuarioVO user) {
		BumayeInterface  m = new OperacionesBBDD();
		PersonajeVO personaje = m.LoginUser(user);
		System.out.print("entramosen el POST login user");

		return personaje;
	}
	
	@Path("/{idusuario}")
	@GET
	@Produces(MediaType.API_USER)
	public PersonajeVO getPersonaje (@PathParam("idusuario") int idusuario) {
		BumayeInterface  m = new OperacionesBBDD();
		PersonajeVO pers = m.getPersonaje(idusuario);

		return pers;
	}
	@Path("/register")
	@POST
	@Consumes(MediaType.API_USER)
	@Produces(MediaType.API_PERSONAJE)
	public PersonajeVO UserRegister (UsuarioVO user) {
		BumayeInterface  m = new OperacionesBBDD();
		PersonajeVO personaje = m.RegistroUser(user);
		return personaje;
	}

	@Path("/lista/{idusuario}")
	@GET
	@Produces(MediaType.API_LOGEADOS)
	public List<PersonajeLogeadoVO> listaUsuarios (@PathParam("idusuario") int idusuario) {
		BumayeInterface  m = new OperacionesBBDD();
		List<PersonajeLogeadoVO> logeados = m.listPersonajes(idusuario);

		return logeados;
	}
	@Path("/listacofres")
	@GET
	@Produces(MediaType.API_OBJETOS)
	public List<CofreVO> listaCofres () {
		BumayeInterface  m = new OperacionesBBDD();
		List<CofreVO> cofres = m.listCofres();

		return cofres;
	}

	@Path("/listaobjetos/{idusuario}")
	@GET
	@Produces(MediaType.API_OBJETOS)
	public List<ObjetoCantidadVO> listaObjetosPersonaje (@PathParam("idusuario") int idusuario) {
		BumayeInterface  m = new OperacionesBBDD();
		List<ObjetoCantidadVO> objetoscantida = m.listaObjetosUsr(idusuario);
		System.out.print("entramosen elGET inventario");

		return objetoscantida;
	}

	@Path("/listaobjetoscofre/{idcofre}")
	@GET
	@Produces(MediaType.API_OBJETOS)
	public List<ObjetoCofreCantidadVO> listaObjetosCofre (@PathParam("idcofre") int idcofre) {
		BumayeInterface  m = new OperacionesBBDD();
		List<ObjetoCofreCantidadVO> objetoscantida = m.listaObjetosCofre(idcofre);

		return objetoscantida;
	}


	@Path("/combinacion")
	@POST
	@Consumes(MediaType.API_COMBINACION)
	@Produces(MediaType.API_PERSONAJE)
	public PersonajeVO combinacionObjeto (CombinacionVO objeto)throws Exception  {
		BumayeInterface  m = new OperacionesBBDD();
		ObjetoCantidadVO nuevobjeto=null;

		try{
			nuevobjeto = m.combinacionFinal(objeto.getIduser(), objeto.getCombo1(), objeto.getCombo2());
		}
		catch(NoTienesEseObjetoException | NoExisteEsaCombinacionException e){
			throw e;
		}
		PersonajeVO personaje = m.getPersonajeCompleto(objeto.getIduser());

		return personaje;
	}

	@Path("/{iduser}/añadirobjeto/{idobjeto}")
	@GET
	public String añadirObjetoInventario (@PathParam("iduser") int iduser,@PathParam("idobjeto") int idobjeto) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();
		String s;
		try{
			s = m.añadirObjetoInventarioVerificado(idobjeto, iduser);
		}
		catch(NoTienesEspacioEnInventarioException e){
			throw e;
		}

		return s;
	}

	@Path("/{iduser}/cofre/{idcofre}/objeto/{idobjeto}/cantidad/{cantidad}")
	@GET
	public String recogerObjetoCofre (@PathParam("iduser") int iduser,@PathParam("idobjeto") int idobjeto,
			@PathParam("idcofre") int idcofre,@PathParam("cantidad") int cantidad) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();
		String s;
		try{
			s = m.recogerObjetosCofre(iduser, idcofre, idobjeto, cantidad);
		}
		catch(NoHayTantosObjetosException e){
			throw e;
		}

		return s;
	}

	@Path("/equipar")
	@POST
	@Consumes(MediaType.API_EQUIPAMIENTO)
	@Produces(MediaType.API_PERSONAJE)
	public PersonajeVO userEquipamiento (EquipamientoVO equipo) {
		BumayeInterface  m = new OperacionesBBDD();

		System.out.print("Desequipar arma de id: " +equipo.getIdesequipada());
		System.out.print("Equipar arma de id: " +equipo.getIdarmequipada());
		String equipado= m.añadirArmasArmadurasEquipada(equipo.getIdarmequipada(), equipo.getIduser());
		m.updateAtributosEquipada_UserArmasArmaduras(equipo.getIdarmequipada(), equipo.getIduser());
		
		//en caso de que halla algo ya equipado lo desequipamos
		if (equipo.getIdesequipada()!=0)
		{

		String desequipado= m.desequiparArmasArmadurasEquipada(equipo.getIdesequipada(),equipo.getIduser());
		m.updateAtributosDesequipada_UserArmasArmaduras(equipo.getIdesequipada(), equipo.getIduser());
		}

		PersonajeVO personaje = m.getPersonajeCompleto(equipo.getIduser());
		return personaje;
	}
	
	@Path("/desequipar")
	@POST
	@Consumes(MediaType.API_EQUIPAMIENTO)
	@Produces(MediaType.API_PERSONAJE)
	public PersonajeVO userDesequipamiento (EquipamientoVO equipo) {
		BumayeInterface  m = new OperacionesBBDD();

		System.out.print("Desequipar arma de id: " +equipo.getIdesequipada());

		String desequipado= m.desequiparArmasArmadurasEquipada(equipo.getIdesequipada(),equipo.getIduser());
		m.updateAtributosDesequipada_UserArmasArmaduras(equipo.getIdesequipada(), equipo.getIduser());
		
		PersonajeVO personaje = m.getPersonajeCompleto(equipo.getIduser());
		return personaje;
	}
	
	@Path("/posicionnueva/{iduser}/{latitud}/{longitud}")
	@PUT
	public String CambioPosicion(@PathParam("iduser") int iduser,@PathParam("latitud") double latitud,@PathParam("longitud") double longitud) {
		BumayeInterface  m = new OperacionesBBDD();
//		double latitud = punt.getLatitud();
//		double longitud = punt.getLongitud();
		String s = m.CambiarPosicionUser(iduser, latitud, longitud);

		return s;
	}
}
