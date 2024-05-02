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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.{By, Keys}
import org.scalatest.matchers.dsl.MatcherWords.not.startWith
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import uk.gov.hmrc.configuration.TestEnvironment
import uk.gov.hmrc.selenium.webdriver.Driver

import java.time.LocalDate

object Exclusion extends BasePage {

  private val exclusionsUrl: String =
    TestEnvironment.url("one-stop-shop-exclusions-frontend") + "/pay-vat-on-goods-sold-to-eu"
  private val journeyUrl: String    = "/leave-one-stop-shop"

  private val returnsUrl: String        =
    TestEnvironment.url(
      "one-stop-shop-returns-frontend"
    ) + "/pay-vat-on-goods-sold-to-eu/northern-ireland-returns-payments"
  private val returnsJourneyUrl: String = "/your-account"

  def goToExclusionsJourney(): Unit =
    get(exclusionsUrl + journeyUrl)

  def checkJourneyUrl(page: String): Unit =
    getCurrentUrl should startWith(s"$exclusionsUrl/$journeyUrl/$page")

  def answerRadioButton(answer: String): Unit = {

    answer match {
      case "yes" => click(By.id("value"))
      case "no"  => click(By.id("value-no"))
      case _     => throw new Exception("Option doesn't exist")
    }
    click(continueButton)
  }

  def enterDate(day: String): Unit = {

    val date =
      if (day == "today") {
        LocalDate.now()
      } else {
        LocalDate.now().plusDays(1)
      }

    sendKeys(By.id("value.day"), date.getDayOfMonth.toString)
    sendKeys(By.id("value.month"), date.getMonthValue.toString)
    sendKeys(By.id("value.year"), date.getYear.toString)

    click(continueButton)
  }

  def enterDate(): Unit = {
    sendKeys(By.id("value.day"), LocalDate.now().getDayOfMonth.toString)
    sendKeys(By.id("value.month"), LocalDate.now().getMonthValue.toString)
    sendKeys(By.id("value.year"), LocalDate.now().getYear.toString)
    click(continueButton)
  }

  def selectCountry(country: String): Unit = {
    sendKeys(By.id("value"), country)
    click(By.cssSelector("li#value__option--0"))
    click(continueButton)
  }

  def clearCountry(): Unit = {
    val input = Driver.instance.findElement(By.id("value")).getAttribute("value")
    if (input != null) {
      for (n <- input)
        Driver.instance.findElement(By.id("value")).sendKeys(Keys.BACK_SPACE)
    }
  }

  def enterVatNumber(vatNumber: String): Unit = {
    sendKeys(By.id("value"), vatNumber)
    click(continueButton)
  }

  def selectChangeLink(link: String): Unit =
    click(By.cssSelector(s"a[href*=$link]"))

  def continue(): Unit =
    click(continueButton)

  def goToReturnsJourney(): Unit =
    get(returnsUrl + returnsJourneyUrl)

  def continue(): Unit =
    click(continueButton)

}
