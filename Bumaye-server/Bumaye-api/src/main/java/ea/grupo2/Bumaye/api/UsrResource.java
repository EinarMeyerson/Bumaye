package ea.grupo2.Bumaye.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeLogeadoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.Motor.BumayeInterface;
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
	    return personaje;
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
}