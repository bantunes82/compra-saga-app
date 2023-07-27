package com.bantunes82;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("compra-orquestrada")
public class CompraOrquestradaResource {

	@Inject
	CreditoService creditoService;

	@Inject
	PedidoService pedidoService;

	@GET
	@Path("teste")
	@Produces(MediaType.TEXT_PLAIN)
	public Response saga() {
		//credito = 100
		Long id = 0l;

		comprar(++id, 20);
		comprar(++id, 30);
		comprar(++id, 30);
		comprar(++id, 25);

		return Response.ok().build();
	}


	private void comprar(Long id, int valor){
		pedidoService.newPedido(id);
		try {
			creditoService.newPedidoValor(id, valor);
			System.out.println("Pedido " + id + " registrado no valor de " + valor + ". Saldo disponível: " + creditoService.getCreditoTotal());
		} catch (IllegalStateException e){
			pedidoService.cancelPedido(id);
			System.err.println("Pedido " + id + " estornado no valor de " + valor);
		}
	}



}
