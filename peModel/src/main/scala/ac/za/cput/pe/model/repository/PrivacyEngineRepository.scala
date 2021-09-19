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

package ac.za.cput.pe.model.repository


import ac.za.cput.pe.model.schema.PeDB._
import ac.za.cput.pe.model.schema._
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.{Query, Session}

trait PrivacyEngineRepository{
  def persistShare(record: Share)
  def persistAuxillaryData(record: AuxillaryData)
  def contextExist(key: String): Boolean
  def organisationExist(key: String): Boolean
  def loadPoliciesForContextByOwner(contextKey: String, ownerKey: String): Seq[Policy]
}


sealed class PrivacyEngineRepositoryImpl(val session: Session) extends PrivacyEngineRepository {

  override def contextExist(key: String) =
    using(session){
      contexts.where(_.key === key).nonEmpty
    }

  override def organisationExist(key: String) =
    using(session) {
      organisations.where(_.key === key).nonEmpty
    }

  override def loadPoliciesForContextByOwner(contextKey: String, ownerKey: String) =
    using(session) {
      val q = from(PeDB.contexts, PeDB.policies, PeDB.dataOwners, PeDB.policyContexts)((c, p, o, pc) =>
        where(c.key === contextKey and o.key === ownerKey
          and c.id === pc.contextId and p.id === pc.policyId
        and o.id === pc.dataOwnerId).
          select(p)
          orderBy (p.id)
      )
      val result = q.toList
      result
    }


  override def persistShare(record: Share) =
    using(session) {
      shares.insertOrUpdate(record)
    }
  override def persistAuxillaryData(record: AuxillaryData) =
    using(session) {
      auxillaryData.insertOrUpdate(record)
    }
}

