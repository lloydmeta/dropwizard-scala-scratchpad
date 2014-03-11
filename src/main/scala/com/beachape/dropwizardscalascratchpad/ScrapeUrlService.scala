import com.beachape.dropwizardscalascratchpad.resources.GreetResource


import com.wordnik.swagger.jaxrs.config._
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider
import com.wordnik.swagger.config._
import com.wordnik.swagger.reader._
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader


import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.ScalaService
import com.yammer.dropwizard.config.{Environment, Bootstrap}
import com.yammer.dropwizard.bundles.ScalaBundle
import com.yammer.metrics.core.HealthCheck
import com.yammer.metrics.core.HealthCheck.Result
import scala.util.Random

object ScrapeUrlService extends ScalaService[ScrapeUrlServiceConfiguration] {

  def initialize(bootstrap: Bootstrap[ScrapeUrlServiceConfiguration]) {
    bootstrap.setName("scratchpad-service")
    bootstrap.addBundle(new AssetsBundle("/swagger"))
    bootstrap.addBundle(new ScalaBundle)

  }

  def run(configuration: ScrapeUrlServiceConfiguration, environment: Environment) {
    environment.addResource(new ApiListingResourceJSON)
    environment.addResource(new GreetResource)
    environment.addProvider(new ResourceListingProvider)
    environment.addProvider(new ApiDeclarationProvider)

    ScannerFactory.setScanner(new DefaultJaxrsScanner)
    ClassReaders.setReader(new DefaultJaxrsApiReader)
    val config = ConfigFactory.config

    config.setApiVersion("1.0")
    config.setBasePath("http://localhost:8080")

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