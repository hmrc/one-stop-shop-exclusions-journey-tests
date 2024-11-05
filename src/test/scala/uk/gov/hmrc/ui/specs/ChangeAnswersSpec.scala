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

  Feature("Change your answers journeys") {

    Scenario("Trader changes move date for moving to a different country exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects yes on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country")
      exclusion.selectCountry("Austria")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number ATU12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("ATU12345678")

      And("the trader clicks change on the check-your-answers page for move-date")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("move-date\\?waypoints\\=check-your-answers")

      And("the trader amends the move date to tomorrow")
      exclusion.checkJourneyUrl("move-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("tomorrow")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes vat number for moving to a different country exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects yes on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Belgium on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country")
      exclusion.selectCountry("Belgium")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number BE0123456789 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("BE0123456789")

      And("the trader clicks change on the check-your-answers page for eu-vat-number")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("eu-vat-number\\?waypoints\\=check-your-answers")

      And("the trader amends the vat number")
      exclusion.checkJourneyUrl("eu-vat-number\\?waypoints\\=check-your-answers")
      exclusion.enterVatNumber("BE0123456999")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes country for moving to a different country exclusions journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects yes on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country")
      exclusion.selectCountry("Bulgaria")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number BG123456789 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("BG123456789")

      And("the trader clicks change on the check-your-answers page for which-eu-country")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("which-eu-country\\?waypoints\\=check-your-answers")

      And("the trader amends the country")
      exclusion.checkJourneyUrl("which-eu-country\\?waypoints\\=check-your-answers")
      exclusion.clearCountry()
      exclusion.selectCountry("Croatia")

      And("the trader enters VAT number HR01234567899 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number\\?waypoints\\=check-your-answers")
      exclusion.enterVatNumber("HR01234567899")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes from moving country journey to voluntary journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects yes on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country")
      exclusion.selectCountry("Austria")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number ATU12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("ATU12345678")

      And("the trader clicks change on the check-your-answers page for moving-to-an-eu-country")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("moving-to-an-eu-country\\?waypoints\\=check-your-answers")

      And("the trader changes answer to no on moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("no")

      And("the trader selects no on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the leave-scheme page")
      exclusion.checkJourneyUrl("leave-scheme\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-using-service-date page")
      exclusion.checkJourneyUrl("stopped-using-service-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("today")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes from moving country journey to no longer selling eligible goods journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects yes on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("yes")

      And("the trader selects Austria on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country")
      exclusion.selectCountry("Austria")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number ATU12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("ATU12345678")

      And("the trader clicks change on the check-your-answers page for moving-to-an-eu-country")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("moving-to-an-eu-country\\?waypoints\\=check-your-answers")

      And("the trader changes answer to no on moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("no")

      And("the trader selects no on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("today")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes date for no longer selling eligible goods journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")
      exclusion.enterDate("today")

      And("the trader clicks change on the check-your-answers page for stopped selling goods date")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("stopped-selling-goods-date\\?waypoints\\=check-your-answers")

      And("the trader amends the stopped selling goods date to tomorrow")
      exclusion.checkJourneyUrl("stopped-selling-goods-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("tomorrow")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes from no longer selling eligible goods journey to moving to another country journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")
      exclusion.enterDate("today")

      And("the trader clicks change on the check-your-answers page for move country answer")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("moving-to-an-eu-country\\?waypoints\\=check-your-answers")

      And("the trader changes answer to yes on moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("yes")

      And("the trader selects Germany on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country\\?waypoints\\=check-your-answers")
      exclusion.clearCountry()
      exclusion.selectCountry("Germany")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("today")

      And("the trader enters VAT number DE123456789 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number\\?waypoints\\=check-your-answers")
      exclusion.enterVatNumber("DE123456789")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes from no longer selling eligible goods journey to moving to voluntary journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")
      exclusion.enterDate("today")

      And("the trader clicks change on the check-your-answers page for stopped selling goods answer")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("stop-selling-goods\\?waypoints\\=check-your-answers")

      And("the trader changes answer to no on stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the leave-scheme page")
      exclusion.checkJourneyUrl("leave-scheme\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-using-service-date page")
      exclusion.checkJourneyUrl("stopped-using-service-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("today")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes date for voluntarily leaving the service journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
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

      And("the trader clicks change on the check-your-answers page for stopped using service date")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("stopped-using-service-date\\?waypoints\\=check-your-answers")

      And("the trader amends the stopped using service date to tomorrow")
      exclusion.checkJourneyUrl("stopped-using-service-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("tomorrow")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes from voluntarily leaving the service journey to moving country journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
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

      And("the trader clicks change on the check-your-answers page for moving-to-an-eu-country")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("moving-to-an-eu-country\\?waypoints\\=check-your-answers")

      And("the trader changes answer to yes on moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("yes")

      And("the trader selects Denmark on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country\\?waypoints\\=check-your-answers")
      exclusion.clearCountry()
      exclusion.selectCountry("Denmark")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("today")

      And("the trader enters VAT number DK12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number\\?waypoints\\=check-your-answers")
      exclusion.enterVatNumber("DK12345678")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

    Scenario("Trader changes from voluntarily leaving the service journey to stopped selling goods journey") {

      Given("the trader accesses the OSS Exclusions Service")
      exclusion.goToExclusionsJourney()
      auth.loginUsingAuthorityWizard("user", "exclusions", "100000002")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
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

      And("the trader clicks change on the check-your-answers page for moving-to-an-eu-country")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.selectChangeLink("moving-to-an-eu-country\\?waypoints\\=check-your-answers")

      And("the trader changes answer to yes on moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country\\?waypoints\\=check-your-answers")
      exclusion.answerRadioButton("yes")

      And("the trader selects Denmark on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country\\?waypoints\\=check-your-answers")
      exclusion.selectCountry("Denmark")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date\\?waypoints\\=check-your-answers")
      exclusion.enterDate("today")

      And("the trader enters VAT number DK12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number\\?waypoints\\=check-your-answers")
      exclusion.enterVatNumber("DK12345678")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")

    }

  }

}
