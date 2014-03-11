import com.beachape.dropwizardscalascratchpad.resources.GreetResource

import com.yammer.dropwizard.ScalaService
import com.yammer.dropwizard.config.{Environment, Bootstrap}
import com.yammer.dropwizard.bundles.ScalaBundle
import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result
import scala.util.Random

object ScrapeUrlService extends ScalaService[ScrapeUrlServiceConfiguration] {

  def initialize(bootstrap: Bootstrap[ScrapeUrlServiceConfiguration]) {
    bootstrap.setName("scratchpad-service")
    bootstrap.addBundle(new ScalaBundle)
  }

  def run(configuration: ScrapeUrlServiceConfiguration, environment: Environment) {
    environment.addResource(new GreetResource)
    environment.addHealthCheck(HealthMock)
  }

}

object HealthMock extends HealthCheck("MockHealth") {

  import HealthCheck.Result.{healthy, unhealthy}

  def check: Result =  if (Random.nextInt(100) <= 50) {
    healthy("all is good")
  } else {
    unhealthy("boohoo")
  }

}