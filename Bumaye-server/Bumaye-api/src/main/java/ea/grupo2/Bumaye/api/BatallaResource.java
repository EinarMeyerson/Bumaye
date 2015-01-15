package ea.grupo2.Bumaye.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;

import ea.grupo2.Bumaye.ClasesVO.AtaqueVO;
import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
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




}
