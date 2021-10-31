package es.tmoor.scalawt.graphics

import es.tmoor.scalawt.SWTRoot
import es.tmoor.scalawt.image._
import es.tmoor.scalawt.image.BufferedImage._
import java.awt

protected abstract class GraphicsConfiguration extends SWTRoot{
  private[scalawt] val asJava: awt.GraphicsConfiguration = null
  def createCompatibleImage(width: Int, height: Int): BufferedImage = asJava.createCompatibleImage(width, height).asScala
}