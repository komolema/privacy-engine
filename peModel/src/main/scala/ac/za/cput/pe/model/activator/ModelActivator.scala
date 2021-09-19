package ac.za.cput.pe.model.activator

import ac.za.cput.pe.model.repository.PrivacyEngineRepository
import ac.za.cput.pe.model.schema.{DataOwner, PeDB}
import org.osgi.framework._
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{SessionFactory, Session}
import org.squeryl.PrimitiveTypeMode._

class ModelActivator extends BundleActivator {

  def start(context: BundleContext) = {
    val bundleNames = context.getBundles map (b => b.getSymbolicName)
    println("Installed bundles:" + bundleNames)

    startDatabaseSession(false)

  }

  def stop(context: BundleContext) = {}

  def startDatabaseSession(create:Boolean = false):Unit={

    def createSchema(c:Boolean):Unit = c match{
      case true =>transaction{
        PeDB.drop
        PeDB.create
      }
      case _ =>
    }

    //jdbc stuff & session creation stuff
    Class.forName("org.postgresql.Driver")
    SessionFactory.concreteFactory = Some(()=>
      Session.create(
        java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/pe","postgres"
          ,"12345"),new PostgreSqlAdapter
      )
    )

    createSchema(create)
  }

}
