package es.tmoor.scalawt.image

import es.tmoor.scalawt.SWTRoot
import java.awt

object BufferedImage {
  implicit class JBufferedImageImplicits(asJ: awt.image.BufferedImage) {
    def asScala: BufferedImage = new BufferedImage(asJ)
  }
}
class BufferedImage(private[scalawt] val asJava: awt.image.BufferedImage) extends Image with SWTRoot {

}