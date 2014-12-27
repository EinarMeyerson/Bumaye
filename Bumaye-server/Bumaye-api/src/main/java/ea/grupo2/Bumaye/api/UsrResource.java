package ea.grupo2.Bumaye.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ea.grupo2.Bumaye.ClasesVO.BatallaVO;
import ea.grupo2.Bumaye.ClasesVO.CombinacionVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoCantidadVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeLogeadoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.Motor.BumayeInterface;
import ea.grupo2.Bumaye.Motor.NoEsTuTurnoException;
import ea.grupo2.Bumaye.Motor.NoTienesEseAtaqueException;
import ea.grupo2.Bumaye.Motor.NoTienesEseObjetoException;
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
	
	@Path("/listaobjetos/{idusuario}")
	@GET
	@Produces(MediaType.API_OBJETOS)
	public List<ObjetoCantidadVO> listaObjetosPersonaje (@PathParam("idusuario") int idusuario) {
		BumayeInterface  m = new OperacionesBBDD();
		List<ObjetoCantidadVO> objetoscantida = m.listaObjetosUsr(idusuario);
		
	    return objetoscantida;
	}
	
	@Path("/listaobjetoscofre/{idcofre}")
	@GET
	@Produces(MediaType.API_OBJETOS)
	public List<ObjetoCantidadVO> listaObjetosCofre (@PathParam("idcofre") int idcofre) {
		BumayeInterface  m = new OperacionesBBDD();
		List<ObjetoCantidadVO> objetoscantida = m.listaObjetosCofre(idcofre);
		
	    return objetoscantida;
	}
	
	
	@Path("/combinacion")
	@POST
    @Consumes(MediaType.API_COMBINACION)
	@Produces(MediaType.API_OBJETOS)
	public ObjetoCantidadVO combinacionObjeto (CombinacionVO objeto)throws Exception  {
		BumayeInterface  m = new OperacionesBBDD();
		ObjetoCantidadVO nuevobjeto=null;
		
		try{
			nuevobjeto = m.combinacion(objeto.getIduser(), objeto.getCombo1(), objeto.getCombo2());
		}
		catch(NoTienesEseObjetoException e){
			throw e;
		}
		
		
	    return nuevobjeto;
	}
}
