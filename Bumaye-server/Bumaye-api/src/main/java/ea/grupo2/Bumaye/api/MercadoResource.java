package ea.grupo2.Bumaye.api;

import java.util.ArrayList;
import java.util.List;

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
import ea.grupo2.Bumaye.ClasesVO.CofreVO;
import ea.grupo2.Bumaye.ClasesVO.EquipamientoVO;
import ea.grupo2.Bumaye.ClasesVO.MercadoVO;
import ea.grupo2.Bumaye.ClasesVO.MercadoVenderVO;
import ea.grupo2.Bumaye.ClasesVO.ObjetoVO;
import ea.grupo2.Bumaye.ClasesVO.PersonajeVO;
import ea.grupo2.Bumaye.ClasesVO.UsuarioVO;
import ea.grupo2.Bumaye.Motor.BumayeInterface;
import ea.grupo2.Bumaye.Motor.NoEsTuTurnoException;
import ea.grupo2.Bumaye.Motor.NoEstasSuficientementeCercaException;
import ea.grupo2.Bumaye.Motor.NoExisteEsaEntradaException;
import ea.grupo2.Bumaye.Motor.NoHayTantaPastaEnTusBolsillosException;
import ea.grupo2.Bumaye.Motor.NoHayTantosObjetosException;
import ea.grupo2.Bumaye.Motor.NoTienesEseAtaqueException;
import ea.grupo2.Bumaye.Motor.NoTienesEseObjetoException;
import ea.grupo2.Bumaye.Motor.NoTienesEspacioEnInventarioException;
import ea.grupo2.Bumaye.Motor.NoTienesTantaCantidadDeEseObjetoException;
import ea.grupo2.Bumaye.Motor.OperacionesBBDD;
import ea.grupo2.Bumaye.pojos.Mercado;

@Path("/mercado")
public class MercadoResource {


	@Path("/entradas")
	@GET
	@Produces(MediaType.API_ENTRADA_MERCADO)
	public List<MercadoVO> listaEngtradasMercado () {
		BumayeInterface  m = new OperacionesBBDD();
		List<MercadoVO> entradas = m.listaMercado();
		System.out.print("entramosen elGET mercado");

		return entradas;
	}
	
	@Path("/{idUserCompador}/comprar/{identradamercado}/cantidad/{cantidad}")
	@GET
	public PersonajeVO comprarMercado (@PathParam("idUserCompador") int idUserCompador,@PathParam("identradamercado") int identradamercado, 
			@PathParam("cantidad") int cantidad) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();
		String s;
		PersonajeVO pers = null;
		try{
			s = m.compraObjetoMercado(identradamercado, cantidad, idUserCompador);
			pers = m.getPersonaje(idUserCompador);
		}
		catch(NoHayTantosObjetosException | NoTienesEspacioEnInventarioException | NoHayTantaPastaEnTusBolsillosException | NoExisteEsaEntradaException e){
			throw e;
		}
		System.out.print("entramosen el GET comprar");

		return pers;
	}
	
	@Path("/vender")
	@POST
	@Consumes(MediaType.API_ENTRADA_MERCADO)
	public List<MercadoVO> venderObjeto (MercadoVenderVO mercado) throws Exception {
		BumayeInterface  m = new OperacionesBBDD();
		List<MercadoVO> listm = null;
		try{
			System.out.print("mercado iduser "+ mercado.getIdpersonaje());
			System.out.print("mercado idobjeto "+ mercado.getIdobjeto());
			System.out.print("mercado cantidad "+ mercado.getCantidad());
			System.out.print("mercado precio "+ mercado.getPrecioUnidad());

			m.addEntradaMercado(mercado.getIdpersonaje(), mercado.getIdobjeto(), mercado.getCantidad(), mercado.getPrecioUnidad());
			listm =listaEngtradasMercado();
		}
		catch(NoTienesTantaCantidadDeEseObjetoException | NoTienesEseObjetoException |NoHayTantosObjetosException e){
			throw e;
		}
		System.out.print("entramosen el POST vender");

		return listm;
	}


}
