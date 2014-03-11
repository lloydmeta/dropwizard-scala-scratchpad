package com.beachape.dropwizardscalascratchpad.resources

import java.util.concurrent.atomic.AtomicLong
import com.yammer.metrics.annotation.Timed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import com.beachape.dropwizardscalascratchpad.models._

@Path("/greet")
@Produces(Array("application/json"))
class GreetResource {

  private val counter: AtomicLong = new AtomicLong

  @GET
  @Timed
  def greet(@QueryParam("name") name: Option[String]): Greeting =
    Greeting(counter.incrementAndGet(), name getOrElse "stranger")
}
