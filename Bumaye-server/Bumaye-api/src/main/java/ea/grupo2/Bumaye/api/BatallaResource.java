package ea.grupo2.Bumaye.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.Motor.BumayeInterface;
import ea.grupo2.Bumaye.Motor.OperacionesBBDD;

@Path("/batalla")
public class BatallaResource {
	
	@GET
	public String HolaBumaye(){
		return "Bumaye san!";
	} 
//caca
	    /**
	     * Method handling HTTP GET requests. The returned object will be sent
	     * to the client as "text/plain" media type.
	     *
	     * @return String that will be returned as a text/plain response.
	     */
		@Path("/{idbatalla}/ataque/{idataque}")
		@GET
		@Produces(MediaType.API_BATALLA)
		public BatallaVO ataqueBatalla (@PathParam("idbatalla") int idbatalla, @PathParam("idataque") int idataque) {
			BumayeInterface  m = new OperacionesBBDD();
			
			BatallaVO batallaVO = m.ResultadoAtaqueVO(idataque, idbatalla);
			
		    return batallaVO;
		}
		
		
		//CAMBIARLAAAAAAAAAAAAAA ES UNA CHAPUZA PARA HACER UNA PRUEBA
		
		@Path("/prueba")
		@GET
		@Produces(MediaType.API_BATALLA)
		public BatallaVO iniciarBatalla () {
			BumayeInterface  m = new OperacionesBBDD();
			
			ArrayList<PersonajeVO> listaPersonajesVO = new ArrayList<PersonajeVO>();
	        UsuarioVO u1 = new UsuarioVO("Ilcapone", "1234", "palotes@pushi");
	        listaPersonajesVO.add(m.LoginUser(u1));
	 
	        UsuarioVO u2 = new UsuarioVO("Elcolmo", "1234", "mañana@quizas.si");
	        listaPersonajesVO.add(m.LoginUser(u2));
			
			BatallaVO batallaVO = m.iniciarBatallaVO(listaPersonajesVO);
			
		    return batallaVO;
		}
		
		
}
