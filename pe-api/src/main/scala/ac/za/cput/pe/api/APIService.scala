package ac.za.cput.pe.api

import javax.jws.WebService
import javax.ws.rs.{PathParam, Produces, GET, Path}

@Path("/V1")
@Produces(Array("application/json"))
@WebService
class APIService {

  @GET
  @Path("/canmove")
  def canMove(@PathParam userKey: String, @PathParam srcOrgKey: String,
              @PathParam destOrgKey: String):String = {
    "Move It"
  }
}


