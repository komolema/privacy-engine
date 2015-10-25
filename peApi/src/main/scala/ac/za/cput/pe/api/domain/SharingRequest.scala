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
package ac.za.cput.pe.api.domain


class SharingRequest(val dataHolderKey: Option[String],
                     val contextKey: Option[String],
                     val dataOwnerKey: Option[String],
                     val thirdPartyKey: Option[String],
                     val data: Map[String, String]){
  def SharingRequest = {}
}


trait Response
case class SharingResponse(status: String, message: String) extends Response
case class ErrorResponse(code: String, message: String) extends Response

object ErrorCodes {
  val ERR_SHARING_REQUEST_INVALID = new ErrorResponse("ERR1", "Sharing request object is invalid")
  val ERR_REQUEST_HALTED = new ErrorResponse("ERR2", "Executed policies halted the data sharing request")
  val ERR_HOLDER_AND_THIRDPARTY_INVALID = new ErrorResponse("ERR3", "Details for the data holder and\\or third party" +
    "invalid ")
}

