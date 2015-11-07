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


package ac.za.cput.pe.policy

import ac.za.cput.pe.model.repository.PrivacyEngineRepository
import ac.za.cput.pe.model.schema.AuxillaryData
import ac.za.cput.pe.policy.ContextStatus.ContextState
import ac.za.cput.pe.policy.handler.{PolicyHandler, RejectPolicyHandler, AllowPolicyHandler}

trait PolicyManager {
  def validateHolderAndThirdParty(dataHolderKey: String, thirdPartyKey: String): Boolean


  def executeContextPolicies(contextKey: String, ownerKey: String, data: Seq[AuxillaryData]): Seq[ContextState]
}

sealed class PolicyManagerImpl extends PolicyManager {

  private var privacyEngineRepository: PrivacyEngineRepository = _

  private val policies = Map("ALLOW" -> new AllowPolicyHandler,
    "REJECT" -> new RejectPolicyHandler)

  override def validateHolderAndThirdParty(dataHolderKey: String, thirdPartyKey: String): Boolean ={
    privacyEngineRepository.organisationExist(dataHolderKey) &&
    privacyEngineRepository.organisationExist(thirdPartyKey)
  }
  override def executeContextPolicies(contextKey: String, ownerKey: String, data: Seq[AuxillaryData]): Seq[ContextState] = {
    privacyEngineRepository.contextExist(contextKey) match {
      case true =>
        privacyEngineRepository.loadPoliciesForContextByOwner(contextKey, ownerKey)
          .map(p => executePolicy(p.name, data)).toList
      case false =>
        Seq.empty
    }
  }

  private def executePolicy(policyName: String, data: Seq[AuxillaryData]): ContextStatus.ContextState = {
    val policyNameUpper = policyName.toUpperCase
    policies(policyNameUpper) match {
      case handler: PolicyHandler =>
        handler.handle(data)
      case _ =>
        ContextStatus.UNDEFINED
    }
  }

  def setPrivacyEngineRepository(repo: PrivacyEngineRepository) =
    privacyEngineRepository = repo
}


