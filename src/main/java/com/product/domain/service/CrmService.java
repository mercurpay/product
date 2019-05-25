package com.product.domain.service;

import com.product.domain.event.CrmEvent;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient
public interface CrmService {

  @POST
  @Path("/{orderId}/events")
  Void notifyEvent(@PathParam("orderId") String orderId, CrmEvent crmEvent);

}