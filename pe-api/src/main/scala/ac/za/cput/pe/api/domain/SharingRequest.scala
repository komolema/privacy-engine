package ac.za.cput.pe.api.domain


case class SharingRequest(dataHolderKey: String, contextKey: String, dataOwnerKey: String,
                     thirdPartyKey: String)


case class SharingResponse(status: String, message: String)
case class ErrorResponse(code: String, message: String)

