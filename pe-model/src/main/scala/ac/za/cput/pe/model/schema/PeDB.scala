package ac.za.cput.pe.model.schema

import org.joda.time.LocalDate
import org.squeryl.PrimitiveTypeMode._
import org.squeryl._
import org.squeryl.dsl.{ManyToOne, OneToMany}

object PeDB extends Schema {

  val organisation = table[Organisation]("organisation")
  val auxillaryData = table[AuxillaryData]("auxillary_data")
  val share = table[Share]("share")
  val context = table[Context]("context")
  val dataOwner = table[DataOwner]("data_owner")
  val policyContext = table[PolicyContext]("policy_context")
  val policy = table[Policy]("policy")

  val dataHolderToShare =
    oneToManyRelation(organisation, share)
      .via((o, s) => o.id === s.dataHolderId)
  val thirdPartyToShare =
    oneToManyRelation(organisation, share)
      .via((o, s) => o.id === s.thirdPartyId)

  val shareToAuxillaryData =
    oneToManyRelation(share, auxillaryData)
      .via((s, a) => s.id === a.shareId)

  val contextToShare =
    oneToManyRelation(context, share)
      .via((c, s) => c.id === s.contextId)

  val contextToPolicyContext =
    oneToManyRelation(context, policyContext)
      .via((c, pc) => c.id === pc.contextId)

  val policyToPolicyContext =
    oneToManyRelation(policy, policyContext)
      .via((p, pc) => p.id === pc.policyId)

  val dataOwnerToPolicyContext =
    oneToManyRelation(dataOwner, policyContext)
      .via((o, pc) => o.id === pc.dataOwnerId)

}

class BaseEntity extends KeyedEntity[Long] {
  val id: Long = 0
}

sealed class Organisation(val name: String, val key: String) extends BaseEntity {
  lazy val thirdPartyShares: OneToMany[Share] = PeDB.thirdPartyToShare.left(this)
  lazy val dataHolderShares: OneToMany[Share] = PeDB.dataHolderToShare.left(this)
}

sealed class Share(val date: LocalDate, val dataHolderId: Long, val thirdPartyId: Long, val contextId: Long)
  extends BaseEntity {
  lazy val dataHolder: ManyToOne[Organisation] = PeDB.dataHolderToShare.right(this)
  lazy val thirdParty: ManyToOne[Organisation] = PeDB.thirdPartyToShare.right(this)
  lazy val context: ManyToOne[Context] = PeDB.contextToShare.right(this)
}

sealed class AuxillaryData(val key: String, val value: String, val shareId: Long) extends BaseEntity {
  lazy val share: ManyToOne[Share] = PeDB.shareToAuxillaryData.right(this)
}

sealed class Context(val key: String, val name: String) extends BaseEntity {
  lazy val shares: OneToMany[Share] = PeDB.contextToShare.left(this)
  lazy val policyContexts: OneToMany[PolicyContext] = PeDB.contextToPolicyContext.left(this)
}

sealed class PolicyContext(val contextId: Long, val policyId: Long, val dataOwnerId: Long)
  extends BaseEntity {
  lazy val context: ManyToOne[Context] = PeDB.contextToPolicyContext.right(this)
  lazy val policy: ManyToOne[Policy] = PeDB.policyToPolicyContext.right(this)
  lazy val dataOwner: ManyToOne[DataOwner] = PeDB.dataOwnerToPolicyContext.right(this)

}

sealed class DataOwner(val name: String, val surname: String, val alias: String, val key: String) extends BaseEntity {
  lazy val policyContexts: OneToMany[PolicyContext] = PeDB.dataOwnerToPolicyContext.left(this)
}

sealed class Policy(val name: String) extends BaseEntity {
  lazy val policyContexts: OneToMany[PolicyContext] = PeDB.policyToPolicyContext.left(this)
}








