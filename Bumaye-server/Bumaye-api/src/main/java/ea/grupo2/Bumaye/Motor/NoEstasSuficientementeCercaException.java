package ea.grupo2.Bumaye.Motor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import ea.grupo2.Bumaye.api.MediaType;

@SuppressWarnings("serial")
public class NoEstasSuficientementeCercaException extends WebApplicationException {
	private final static String MESSAGE = "No estas a menos de 100m del cofre para abrirlo";

	public NoEstasSuficientementeCercaException() {
		super(Response
				.status(Response.Status.FORBIDDEN)
				.entity(new BatallaError(Response.Status.FORBIDDEN
						.getStatusCode(), MESSAGE))
				.type(MediaType.API_ERROR).build());
	}

}