package com.beachape.dropwizardscalascratchpad.resources

import java.util.concurrent.atomic.AtomicLong
import com.yammer.metrics.annotation.Timed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import com.beachape.dropwizardscalascratchpad.models._
import com.wordnik.swagger.annotations._

@Path("/greet")
@Api(value = "/greet", description = "Simple greeting")
@Produces(Array("application/json"))
class GreetResource {

  private val counter: AtomicLong = new AtomicLong

  @GET
  @Timed
  @ApiOperation(
    value = "Get back a greeting",
    notes = "Has an ever incrementing id in the response along with a user name if supplied as a query param",
    response = classOf[Greeting])
  @ApiImplicitParams(
   Array(
    new ApiImplicitParam(
      name = "name",
      value = "Name you want to be greeted by",
      required = false,
      dataType = "String",
      paramType = "query",
      defaultValue = "stranger")
   )
  )
  def greet(@QueryParam("name") name: String): Greeting =
  //def greet(@QueryParam("name") name: Option[String]): Greeting = breaks because Option isn't recognised
    Greeting(counter.incrementAndGet(), name)
}
