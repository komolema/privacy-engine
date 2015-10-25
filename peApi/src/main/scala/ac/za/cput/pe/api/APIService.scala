/**
 * Copyright 2015 CPUT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ac.za.cput.pe.api

import javax.jws.WebService
import javax.ws.rs._
import javax.ws.rs.core.MediaType

import ac.za.cput.pe.api.domain.{ErrorCodes, ErrorResponse, SharingResponse, SharingRequest}
import ac.za.cput.pe.model.schema.AuxillaryData
import ac.za.cput.pe.policy.{ContextStatus, PolicyManager}

@Path("/V1")
@Produces(Array("application/json"))
@WebService
class APIService {

  private var policyManager: PolicyManager = _

  @POST
  @Path("/canmove")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  def canMove(request: SharingRequest): Either[Seq[ErrorResponse], SharingResponse] = {
    val validationResult = validateRequest(request)
    if(!validationResult)
      Left(Seq(ErrorCodes.ERR_SHARING_REQUEST_INVALID))
    else {
      if (policyManager.validateHolderAndThirdParty(request.dataHolderKey.get,
        request.thirdPartyKey.get) == false) {
        Left(Seq(ErrorCodes.ERR_HOLDER_AND_THIRDPARTY_INVALID))
      } else {
        val (halt, proceed) = policyManager.executeContextPolicies(request.contextKey.get, request.dataOwnerKey.get,
          request.data.map(d => new AuxillaryData(d._1, d._2, 0)).toSeq)
          .partition(_ == ContextStatus.HALT)

        if (halt.nonEmpty) {
          Left(halt.map(h => ErrorCodes.ERR_REQUEST_HALTED))
        }
        else {
          Right(SharingResponse("PROCEED", "PROCEED"))
        }
      }
    }
  }

  private def validateRequest(request: SharingRequest): Boolean = {
    request.contextKey.isDefined && request.dataOwnerKey.isDefined &&
    request.thirdPartyKey.isDefined && request.dataHolderKey.isDefined &&
    request.data.nonEmpty
  }

  def setPolicyManager(pm: PolicyManager) =
    policyManager = pm
}


