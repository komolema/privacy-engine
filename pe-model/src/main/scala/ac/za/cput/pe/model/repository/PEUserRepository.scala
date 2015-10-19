package ac.za.cput.pe.model.repository


import ac.za.cput.pe.model.schema._
import ac.za.cput.pe.model.schema.PeDB._
import org.squeryl.PrimitiveTypeMode._

class PEUserRepository {

/*  def userSharingPreferences(user:DataOwner)={
    transaction {
      join(share, dataOwner.leftOuter)((c, p) =>
        where(user.key === p.map(_.key).get)
        select(c,p)
          on(c.peuserId === p.map(_.id))
        ).toList
    }
  }*/

}
