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

    Scenario("Excluded trader is not able to access self exclusion journey") {

      Given("the excluded trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("user", "returns", "600000013")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader manually navigates to the self-exclude journey")
      exclusion.goToExclusionsJourney()

      Then("the trader is on the already-left-scheme-error page")
      exclusion.checkJourneyUrl("already-left-scheme-error")

    }

    Scenario("Non-excluded trader is not able to access reversal journey") {

      Given("the excluded trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("user", "returns", "100000002")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader manually navigates to the self-exclude journey")
      exclusion.goToReversalsJourney()

      Then("the trader is on the cannot-use-this-service page")
      exclusion.checkJourneyUrl("cannot-use-this-service")

    }

    Scenario("Failure when reversing a self exclusion for trader no longer selling eligible goods") {

      Given("the trader accesses the OSS Returns Service")
      exclusion.goToReturnsJourney()
      auth.loginUsingAuthorityWizard("user", "returns", "100000300")
      exclusion.checkReturnsJourneyUrl("your-account")

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the failed submission page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-submission-failure")

    }

    Scenario("Check your answers page inaccessible in stopped selling eligible goods to EU/NI exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects no on the move-country page")
      exclusion.answerRadioButton("no")

      And("the trader is on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      When("the trader answers yes on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("yes")

      And("the trader is on the stopped-selling-goods-date")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")
      exclusion.enterDate("today")

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }

    Scenario("Check your answers page inaccessible in voluntary exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects no on the move-country page")
      exclusion.answerRadioButton("no")

      And("the trader is on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      And("the trader selects no on the stop-selling-goods page")
      exclusion.answerRadioButton("no")

      And("the trader is on the leave-scheme")
      exclusion.checkJourneyUrl("leave-scheme")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      And("the trader selects yes on the leave-scheme page")
      exclusion.checkJourneyUrl("leave-scheme")
      exclusion.answerRadioButton("yes")

      And("the trader is on the stopped-using-service-date")
      exclusion.checkJourneyUrl("stopped-using-service-date")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      And("the trader enters today's date on the stopped-using-service-date page")
      exclusion.checkJourneyUrl("stopped-using-service-date")
      exclusion.enterDate("today")

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

      When("the trader manually navigates to the check-your-answers page")
      exclusion.goToCYA()

      Then("the trader is on the cannot-access page")
      exclusion.checkJourneyUrl("cannot-access")

      And("the trader presses the back button the browser")
      exclusion.clickBackButton()

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }
  }
}
