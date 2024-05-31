/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.pages.{Auth, Exclusion}

class KickoutsSpec extends BaseSpec {

  private val exclusion = Exclusion
  private val auth      = Auth

  Feature("Kickout journeys") {

    Scenario("Excxluded trader is not able to access self exclusion journey") {

      Given("the excluded trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("returns", "600000013")
      exclusion.checkJourneyUrl("your-account")

      When("the trader manually navigates to the self-exclude journey")
      exclusion.goToExclusionsJourney()

      Then("the trader is on the already-left-scheme-error page")
      exclusion.checkJourneyUrl("already-left-scheme-error")

    }
  }
}