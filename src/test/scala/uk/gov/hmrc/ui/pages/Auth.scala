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

import org.openqa.selenium.By
import org.scalatest.matchers.dsl.MatcherWords.not.startWith
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import uk.gov.hmrc.configuration.TestEnvironment

object Auth extends BasePage {

  private val authUrl: String       = TestEnvironment.url("auth-login-stub") + "/gg-sign-in"
  private val exclusionsUrl: String =
    TestEnvironment.url("one-stop-shop-exclusions-frontend") + "/pay-vat-on-goods-sold-to-eu/leave-one-stop-shop"
  private val returnsUrl: String    =
    TestEnvironment.url(
      "one-stop-shop-returns-frontend"
    ) + "/pay-vat-on-goods-sold-to-eu/northern-ireland-returns-payments"

  def loginUsingAuthorityWizard(journey: String, vrn: String): Unit = {

    getCurrentUrl should startWith(authUrl)

    if (journey == "exclusions") {
      sendKeys(By.name("redirectionUrl"), exclusionsUrl)
    } else {
      sendKeys(By.name("redirectionUrl"), returnsUrl)
    }

    selectByValue(By.id("affinityGroupSelect"), "Organisation")

    sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
    sendKeys(By.id("input-0-0-name"), "VRN")
    sendKeys(By.id("input-0-0-value"), vrn)

    sendKeys(By.id("enrolment[1].name"), "HMRC-OSS-ORG")
    sendKeys(By.id("input-1-0-name"), "VRN")
    sendKeys(By.id("input-1-0-value"), vrn)

    click(By.cssSelector("Input[value='Submit']"))

  }

}
