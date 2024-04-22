**This is the template README. Please update this with project specific content.**

# one-stop-shop-exclusions-journey-tests

UI test suite for the one-stop-shop-exclusions-frontend using WebDriver and <scalatest/cucumber>.


## Running the tests

Run tests as follows:

Prior to executing the tests ensure you have:
- Docker - to run mongo and browser (Chrome or Firefox) inside a container
- Appropriate [drivers installed](#installing-local-driver-binaries) - to run tests against locally installed Browser
- Installed/configured [service manager](https://github.com/hmrc/service-manager).

Run the following command to start services locally:

    Run at least 4.0 with a replica set:
    docker run --restart unless-stopped -d -p 27017-27019:27017-27019 --name mongo4 mongo:4.0 --replSet rs0`

    Connect to said replica set:
    docker exec -it mongo4 mongo

    When that console is there:
    rs.initiate()

    Start services via service manager:
    sm --start ONE_STOP_SHOP_ALL -r

Then execute the `run_tests.sh` script:

    ./run_tests.sh <environment> <browser-driver>

The `run_tests.sh` script defaults to the `local` environment with the locally installed `chrome` driver binary.  For a complete list of supported param values, see:
- `src/test/resources/application.conf` for **environment**
- [webdriver-factory](https://github.com/hmrc/webdriver-factory#2-instantiating-a-browser-with-default-options) for **browser-driver**

## Selenium Grid

You will need to run Selenium Grid via the instructions here - https://github.com/hmrc/docker-selenium-grid

## ZAP and Accessibility tests

ZAP and Accessibility tests are bundled together with the journey tests on Jenkins.

The reports for these can be accessed via the journey test build here - https://build.tax.service.gov.uk/job/One%20Stop%20Shop/job/one-stop-shop-exclusions-journey-tests/

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
