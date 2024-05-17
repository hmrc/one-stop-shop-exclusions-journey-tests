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

class ChangeAnswersSpec extends BaseSpec {

  private val exclusion = Exclusion
  private val auth      = Auth

  Feature("Exclusion journeys") {

    Scenario("Trader changes move date for moving to a different country exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("100000002")
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

      And("the trader clicks change on the check-your-answers page for move-date")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("move-date")

      And("the trader amends the move date to tomorrow")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("tomorrow")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.continue()

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }

    Scenario("Trader changes vat number for moving to a different country exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects yes on the move-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the eu-country page")
      exclusion.checkJourneyUrl("eu-country")
      exclusion.selectCountry("Belgium")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number BE0123456789 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("BE0123456789")

      And("the trader clicks change on the check-your-answers page for eu-vat-number")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("eu-vat-number")

      And("the trader amends the vat number")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("BE0123456999")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.continue()

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }

    Scenario("Trader changes country for moving to a different country exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("100000002")
      exclusion.checkJourneyUrl("move-country")

      When("the trader selects yes on the move-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the eu-country page")
      exclusion.checkJourneyUrl("eu-country")
      exclusion.selectCountry("Bulgaria")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number BG123456789 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("BG123456789")

      And("the trader clicks change on the check-your-answers page for eu-country")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("eu-country")

      And("the trader amends the country")
      exclusion.checkJourneyUrl("eu-country")
      exclusion.clearCountry()
      exclusion.selectCountry("Croatia")

      And("the trader enters VAT number HR01234567899 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("HR01234567899")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.continue()

      Then("the trader is on the successful page")
      exclusion.checkJourneyUrl("successful")

    }
  }

}
