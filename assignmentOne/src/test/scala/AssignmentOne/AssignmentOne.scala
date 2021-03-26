package AssignmentOne

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._
import locallibrary.CommandLineProperties._

class AssignmentOne extends Simulation{
  val httpProtocol = http
    .baseUrl(baseURL)
    .inferHtmlResources()
    .acceptHeader("image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36")

  val headers_0 = Map(
    "accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "cache-control" -> "no-cache",
    "pragma" -> "no-cache",
    "sec-ch-ua" -> """Google Chrome";v="89", "Chromium";v="89", ";Not A Brand";v="99""",
    "sec-ch-ua-mobile" -> "?0",
    "sec-fetch-dest" -> "document",
    "sec-fetch-mode" -> "navigate",
    "sec-fetch-site" -> "none",
    "sec-fetch-user" -> "?1",
    "upgrade-insecure-requests" -> "1")

  val csvFeeder = csv("data/testIds.csv").circular

  val scn = scenario("GetDetails")

    .repeat(repeatCount) {
      pace(pacing seconds)
      .feed(csvFeeder)
        .exec(http("request_0")
          .get("/posts?userId=${userId}&id=${id}")
          .headers(headers_0)
          .check(status.in(200, 201, 202, 302, 304))
          .check(status.not(404)))
    }

//  setUp(scn.inject(constantConcurrentUsers(1).during(10 seconds)).protocols(httpProtocol))
  setUp(scn.inject(atOnceUsers(userCount))

    .protocols(httpProtocol))
}
