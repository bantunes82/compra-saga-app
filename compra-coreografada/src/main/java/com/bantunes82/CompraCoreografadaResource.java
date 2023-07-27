package com.bantunes82;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("compra-coreografada")
public class CompraCoreografadaResource {

	@Inject
	PedidoService pedidoService;


	@GET
	@Path("teste")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sage(){
		Long id = 0l;

		pedidoService.newPedido(++id, 20);
		pedidoService.newPedido(++id, 30);
		pedidoService.newPedido(++id, 40);
		pedidoService.newPedido(++id, 15);

		return Response.ok().build();
	}

}
