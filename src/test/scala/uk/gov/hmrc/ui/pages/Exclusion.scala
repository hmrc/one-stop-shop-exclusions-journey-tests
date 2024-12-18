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

import org.junit.Assert
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import org.openqa.selenium.{By, Keys}
import org.scalatest.matchers.dsl.MatcherWords.not.startWith
import org.scalatest.matchers.should.Matchers.should
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

  def goToReversalsJourney(): Unit =
    get(exclusionsUrl + journeyUrl + "/cancel-leave-scheme")

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

  def waitForElement(by: By): Unit =
    new FluentWait(Driver.instance).until(ExpectedConditions.presenceOfElementLocated(by))

  def selectCountry(country: String): Unit = {
    val inputId = "value"
    sendKeys(By.id(inputId), country)
    waitForElement(By.id(inputId))
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

  def submit(): Unit =
    click(submitButton)

  def goToReturnsJourney(): Unit =
    get(returnsUrl)

  def checkReturnsJourneyUrl(page: String): Unit =
    getCurrentUrl should startWith(s"$returnsUrl/$returnsJourneyUrl/$page")

  def selectLink(link: String): Unit =
    click(By.id(link))

  def checkCancelLeaveSchemeError(): Unit = {
    val h1 = Driver.instance.findElement(By.tagName("h1")).getText
    println(h1)
    Assert.assertTrue(h1.equals("You can no longer cancel your request to leave the One Stop Shop scheme"))
  }

  def goToCYA(): Unit =
    get(s"$exclusionsUrl/check-your-answers")

  def clickBackButton(): Unit =
    Driver.instance
      .navigate()
      .back()
}
