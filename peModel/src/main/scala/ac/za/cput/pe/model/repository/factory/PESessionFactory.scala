package ac.za.cput.pe.model.repository.factory

import org.squeryl.Session
import org.squeryl.adapters.PostgreSqlAdapter

sealed class PESessionFactory{}
object PESessionFactory {

  def createSession: Session ={
    Class.forName("org.postgresql.Driver")
      Session.create(
        java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/pe","postgres"
          ,"12345"),new PostgreSqlAdapter
      )
  }
}
