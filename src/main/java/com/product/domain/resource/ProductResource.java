package com.product.domain.resource;

import com.product.domain.Product;
import com.product.domain.resource.data.UpdateAndCreateProductRequest;
import com.product.domain.service.ProductService;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

  private final Logger log = LoggerFactory.getLogger(ProductResource.class);

  @Inject
  ProductService productService;

  @GET
  public Collection<Product> getAll() {
    log.info("Getting all products");
    return productService.getAll();
  }

  @GET
  @Path("/{id}")
  public Product getOne(@PathParam("id") String id) {
    log.info("Getting product {}", id);
    return productService.get(id);
  }

  @POST
  public Product createOne(UpdateAndCreateProductRequest payload) {
    log.info("Creating product {}", payload);
    return productService.create(payload.toProduct());
  }

  @PATCH
  @Path("/{id}")
  public Product createOne(@PathParam("id") String id, UpdateAndCreateProductRequest payload) {
    log.info("Updating product {}", id);
    return productService.update(id, payload.toProduct());
  }

  @DELETE
  public void deleteAll() {
    log.info("Deleting all products");
    productService.removeAll();
  }

  @DELETE
  @Path("/{id}")
  public void deleteOne(@PathParam("id") String id) {
    log.info("Deleting product {}", id);
    productService.remove(id);
  }

}