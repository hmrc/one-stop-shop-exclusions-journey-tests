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

class ExclusionsSpec extends BaseSpec {

  private val exclusion = Exclusion
  private val auth      = Auth

  Feature("Exclusion journeys") {

    Scenario("Trader is moving to an EU country") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("exclusions", "100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects yes on the move-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the eu-country page")
      exclusion.checkJourneyUrl("eu-country")
      exclusion.selectCountry("Austria")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number ATU12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("ATU12345678")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.continue()

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }

    Scenario("Trader is no longer selling eligible goods") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("exclusions", "100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects no on the move-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")
      exclusion.enterDate("today")

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }

    Scenario("Trader is voluntarily leaving the service") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("exclusions", "100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects no on the move-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects no on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the leave-scheme page")
      exclusion.checkJourneyUrl("leave-scheme")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-using-service-date page")
      exclusion.checkJourneyUrl("stopped-using-service-date")
      exclusion.enterDate("today")

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }

    Scenario("Trader who has reversed their exclusion can leave the service again") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("exclusions", "100000029")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects no on the move-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects no on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the leave-scheme page")
      exclusion.checkJourneyUrl("leave-scheme")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-using-service-date page")
      exclusion.checkJourneyUrl("stopped-using-service-date")
      exclusion.enterDate("today")

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }
  }

}
