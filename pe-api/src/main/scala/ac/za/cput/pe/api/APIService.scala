package ac.za.cput.pe.api

import javax.jws.WebService
import javax.ws.rs._

import ac.za.cput.pe.api.domain.{ErrorResponse, SharingResponse, SharingRequest}

@Path("/V1")
@Produces(Array("application/json"))
@WebService
class APIService {

  @POST
  @Path("/canmove")
  def canMove(request: SharingRequest):String = {
    "Move It"
  }
}


