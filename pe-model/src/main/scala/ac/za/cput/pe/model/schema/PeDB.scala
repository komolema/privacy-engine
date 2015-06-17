package ac.za.cput.pe.model.schema

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.customtypes.TimestampField
import org.squeryl.dsl.{ManyToOne, OneToMany, CompositeKey2}

object PeDB extends Schema {

  val policy = table[Policy]("policy")
  val category = table[Category]("category")
  val organisation = table[Organisation]("organisation")
  val sharedDataLog = table[SharedDataLog]("shared_data_log")
  val peuser = table[PEUser]("peuser")
  val context = table[Context]("context")
  val configuration = table[Configuration]("configuration")
  val organisationCategories =
    manyToManyRelation(organisation,category)
    .via[OrganisationCategory]((o,c,oc)=>(o.id === oc.organisationId,c.id === oc.categoryId))
  val organisationToContext =
    oneToManyRelation(organisation,context)
      .via((o,c) => o.id === c.organisationId)
  val policyToConfiguration =
    oneToManyRelation(policy,configuration)
      .via((p,c) => p.id === c.policyId)
  val peuserToConfiguration =
    oneToManyRelation(peuser,configuration)
      .via((p,c) => p.id === c.peuserId)
  val contextToConfiguration =
    oneToManyRelation(context,configuration)
      .via((c1,c2) => c1.id === c2.contextId)
}

class BaseEntity extends KeyedEntity[Long]{
  val id: Long = 0
}

sealed class Organisation(val name:String, val key:String) extends BaseEntity{
  lazy val contexts:OneToMany[Context] = PeDB.organisationToContext.left(this)
}

sealed class Context(val name:String, val organisationId:Long) extends BaseEntity{
  lazy val organisation:ManyToOne[Organisation] = PeDB.organisationToContext.right(this)
  lazy val configurations:OneToMany[Configuration] = PeDB.contextToConfiguration.left(this)
}


sealed class Category(val name:String) extends BaseEntity{
}

sealed class OrganisationCategory(val organisationId:Long, val categoryId:Long)
  extends KeyedEntity[CompositeKey2[Long,Long]]{
  def id = compositeKey(organisationId,categoryId)
}

sealed class SharedDataLog(val key:String, val time:TimestampField) extends BaseEntity{
}

sealed class PEUser(val name:String, val surname:String, val alias:String, val key:String) extends BaseEntity{
  lazy val configurations:OneToMany[Configuration] = PeDB.peuserToConfiguration.left(this)
}

sealed class Policy(val name:String, val description:String, val handler:String) extends BaseEntity{
  lazy val configurations:OneToMany[Configuration] = PeDB.policyToConfiguration.left(this)
}

sealed class Configuration(val dateOfConfiguration:TimestampField, val peuserId:Long, val contextId:Long, val policyId:Long) extends BaseEntity{
  lazy val peuser:ManyToOne[PEUser] = PeDB.peuserToConfiguration.right(this)
  lazy val context:ManyToOne[Context] = PeDB.contextToConfiguration.right(this)
  lazy val policy:ManyToOne[Policy] = PeDB.policyToConfiguration.right(this)
}






