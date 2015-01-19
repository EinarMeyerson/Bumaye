package ea.grupo2.Bumaye.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;

import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.PeticionBatallaVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.Motor.BumayeInterface;
import ea.grupo2.Bumaye.Motor.NoEsTuTurnoException;
import ea.grupo2.Bumaye.Motor.NoTienesEseAtaqueException;
import ea.grupo2.Bumaye.Motor.NoTienesEseObjetoException;
import ea.grupo2.Bumaye.Motor.OperacionesBBDD;

@Path("/batalla")
public class BatallaResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	public String HolaBumaye(){
		return "Bumaye san!";
	} 

	@Path("/{idbatalla}/jugador/{idPersonaje}/ataque/{idataque}")
	@GET
	@Produces(MediaType.API_BATALLA)
	public BatallaVO ataqueBatalla (@PathParam("idbatalla") int idbatalla,@PathParam("idPersonaje") int idPersonaje, @PathParam("idataque") int idataque) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();

		System.out.print("Usuario realiza ataque\n");
		BatallaVO batallaVO = null;
		try{
			batallaVO = m.ResultadoAtaqueVO(idataque, idbatalla, idPersonaje);
		}
		catch(NoEsTuTurnoException | NoTienesEseAtaqueException e){
			throw e;
		}

		return batallaVO;
	}

	@Path("/{idbatalla}/user/{idPersonaje}/objeto/{nombreObjeto}")
	@GET
	@Produces(MediaType.API_BATALLA)
	public BatallaVO oBatalla (@PathParam("idbatalla") int idbatalla,@PathParam("idPersonaje") int idPersonaje, @PathParam("nombreObjeto") String nombreObjeto) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();

		BatallaVO batallaVO = null;
		try{
			batallaVO = m.ResultadoUtilizarObjetoVO(nombreObjeto, idbatalla, idPersonaje);
		}
		catch(NoEsTuTurnoException | NoTienesEseObjetoException e){
			throw e;
		}

		return batallaVO;
	}
	//CAMBIARLAAAAAAAAAAAAAA ES UNA CHAPUZA PARA HACER UNA PRUEBA


	@Path("/prueba/{iduser}")
	@GET
	@Produces(MediaType.API_BATALLA)
	public BatallaVO iniciarBatalla (@PathParam("iduser") int iduser) {
		BumayeInterface  m = new OperacionesBBDD();

		ArrayList<PersonajeVO> listaPersonajesVO = new ArrayList<PersonajeVO>();
		PersonajeVO u1 = m.getPersonaje(iduser);
		listaPersonajesVO.add(u1);

		PersonajeVO u2 = m.getPersonaje(1);
		listaPersonajesVO.add(u2);

		BatallaVO batallaVO = m.iniciarBatallaVO(listaPersonajesVO);

		return batallaVO;
	}

	@Path("/peticion/{idAtacante}/{nombredefensor}")
	@PUT
	public String peticionBatalla (@PathParam("idAtacante") int idAtacante, @PathParam("nombredefensor") String nombredefensor) {
		BumayeInterface  m = new OperacionesBBDD();
		//PersonajeVO personaje = m.getPersonajeCompleto(idAtacante);
		int iddefensor = m.devolvemosaIDuser(nombredefensor);
		System.out.print("$$$  Iddefensor encontrado: " + iddefensor+ "\n");
		String solicitud = m.crearPeticion(idAtacante, iddefensor);
		System.out.print(solicitud);
		return "Peticion realizada";
	}

	@Path("/aceptar/{iddefensor}")
	@GET
	@Produces(MediaType.API_BATALLA)
	public BatallaVO aceptarBatalla (@PathParam("iddefensor") int iddefensor) {
		BumayeInterface  m = new OperacionesBBDD();
		System.out.print("$$ Defensor acepta la batalla : " +iddefensor+ "\n");
		BatallaVO batallaVO = m.aceptarPeticion(iddefensor);
		return batallaVO;
	}
	
	@Path("/comprovar/{iddefensor}")
	@GET
	public String comprovar_peticionBatalla (@PathParam("iddefensor") int iddefensor) {
		BumayeInterface  m = new OperacionesBBDD();
		System.out.print("$$ Defensor acepta la batalla : " +iddefensor+ "\n");
		String comprovavion = m.comprobarPeticion(iddefensor);
		return comprovavion;
	}
	
	@Path("/verificar/{idAtacante}")
	@GET
	@Produces(MediaType.API_PETICION)
	public PeticionBatallaVO verificacionAceptada (@PathParam("idAtacante") int idAtacante) {
		BumayeInterface  m = new OperacionesBBDD();
		PeticionBatallaVO verificacion = m.verificar_aceptacion(idAtacante);
		System.out.print(" ~~ El atacante esta esperando la batalla por el momento : " +verificacion.getAceptada()+ "\n");
		return verificacion;
	}

	@Path("/aceptarAtacante/{idatacante}")
	@GET
	@Produces(MediaType.API_BATALLA)
	public BatallaVO aceptarBatalla_atacante (@PathParam("idatacante") int idatacante) {
		BumayeInterface  m = new OperacionesBBDD();
		System.out.print("$$ Atacante inicia la batalla : " +idatacante+ "\n");
		BatallaVO batallaVO = m.aceptarPeticion_atacante(idatacante);
		return batallaVO;
	}
	
	@Path("/{idbatalla}")
	@GET
	@Produces(MediaType.API_BATALLA)
	public BatallaVO batalla(@PathParam("idbatalla") int idbatalla) {
		BumayeInterface  m = new OperacionesBBDD();
		System.out.print("$$ Batalla solicitada para comprobacion : " +idbatalla+ "\n");
		
		BatallaVO batallaVO = m.getBatallaVO(idbatalla);
		return batallaVO;
	}
	
//	@Path("/fin/{idusuario}")
//	@GET
//	public String finalizar_batalla (@PathParam("idusuario") int idusuario) {
//		BumayeInterface  m = new OperacionesBBDD();
//		System.out.print("$$ Fin de Batalla\n");
//		String comprovavion = m.comprobarPeticion(iddefensor);
//		return comprovavion;
//	}



}
