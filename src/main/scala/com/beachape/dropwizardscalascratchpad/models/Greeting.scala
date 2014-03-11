package com.beachape.dropwizardscalascratchpad.models

import scala.beans.BeanProperty

case class Greeting(@BeanProperty val id: Long, @BeanProperty val name: String)