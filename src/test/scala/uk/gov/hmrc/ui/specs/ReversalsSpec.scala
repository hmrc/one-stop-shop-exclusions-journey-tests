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

class ReversalsSpec extends BaseSpec {

  private val exclusion = Exclusion
  private val auth      = Auth

  Feature("Reversal journeys") {

    Scenario("Reversing a self exclusion for trader moving country") {

      Given("the trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("returns", "100000027")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the successful acknowledgement page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-acknowledgement")

    }

    Scenario("Reversing a self exclusion for trader voluntarily leaving service") {

      Given("the trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("returns", "600000018")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the successful acknowledgement page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-acknowledgement")

    }

    Scenario("Reversing a self exclusion for trader no longer supplying eligible goods") {

      Given("the trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("returns", "100000028")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the successful acknowledgement page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-acknowledgement")

    }

    Scenario("HMRC excluded trader is not able to access reversal journey") {

      Given("the excluded trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("returns", "100000030")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader manually navigates to the reversal journey")
      exclusion.goToReversalsJourney()

      Then("the trader is on the cancel-leave-scheme-error page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-error")

    }

  }
}
