package es.tmoor.scalawt
import java.awt

object ImageObserver {
  implicit class JImageObserverImplicits(jImg: awt.image.ImageObserver) {
    def jMenuContainerAsScala: ImageObserver = new ImageObserver {
      private[scalawt] val asJava: awt.image.ImageObserver = jImg
    }
  }
  sealed class ImageFlag(val asJava: Int)
  object Abort extends ImageFlag(awt.image.ImageObserver.ABORT)
  object AllBits extends ImageFlag(awt.image.ImageObserver.ALLBITS)
  object Error extends ImageFlag(awt.image.ImageObserver.ERROR)
  object FrameBits extends ImageFlag(awt.image.ImageObserver.FRAMEBITS)
  object Height extends ImageFlag(awt.image.ImageObserver.HEIGHT)
  object Properties extends ImageFlag(awt.image.ImageObserver.PROPERTIES)
  object SomeBits extends ImageFlag(awt.image.ImageObserver.SOMEBITS)
  object Width extends ImageFlag(awt.image.ImageObserver.WIDTH)
}
trait ImageObserver extends SWTRoot {
  import ImageObserver._
  private[scalawt] val asJava: awt.image.ImageObserver
  def imageUpdate(
      img: image.Image,
      flags: Seq[ImageFlag],
      bounds: (Int, Int, Int, Int)
  ): Boolean = {
    val (x, y, w, h) = bounds
    val intFlags = flags.foldLeft(0)(_ | _.asJava)
    asJava.imageUpdate(img.asJava, intFlags, x, y, w, h)
  }
}
