package ea.grupo2.Bumaye.Motor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import ea.grupo2.Bumaye.api.MediaType;

@SuppressWarnings("serial")
public class NoTienesEspacioEnInventarioException extends WebApplicationException {
	private final static String MESSAGE = "No tienes mas espacio en tu inventario";

	public NoTienesEspacioEnInventarioException() {
		super(Response
				.status(Response.Status.FORBIDDEN)
				.entity(new BatallaError(Response.Status.FORBIDDEN
						.getStatusCode(), MESSAGE))
				.type(MediaType.API_ERROR).build());
	}

}