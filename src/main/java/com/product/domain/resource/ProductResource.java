package com.product.domain.resource;

import com.product.domain.Product;
import com.product.domain.resource.data.CreateProductRequest;
import com.product.domain.service.ProductService;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/v1/products")
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
  public Product createOne(CreateProductRequest payload) {
    log.info("Creating product {}", payload);
    return productService.create(payload.toProduct());
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