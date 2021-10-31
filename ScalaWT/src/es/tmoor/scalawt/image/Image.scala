package es.tmoor.scalawt.image
import es.tmoor.scalawt.SWTRoot
import es.tmoor.scalawt.graphics.GraphicsConfiguration
import java.awt

object Image {
  sealed class ScalingAlgorithm(private[scalawt] val asJava: Int) extends SWTRoot
  object Scale {
    object AreaAveraging extends ScalingAlgorithm(awt.Image.SCALE_AREA_AVERAGING)
    object Default extends ScalingAlgorithm(awt.Image.SCALE_DEFAULT)
    object Fast extends ScalingAlgorithm(awt.Image.SCALE_FAST)
    object Replicate extends ScalingAlgorithm(awt.Image.SCALE_REPLICATE)
    object Smooth extends ScalingAlgorithm(awt.Image.SCALE_SMOOTH)
  }
}
abstract class Image extends SWTRoot {
  private[scalawt] val asJava: awt.Image
  def flush(): Unit = asJava.flush()
  def accelerationPriority = asJava.getAccelerationPriority
  def capabilities(gc: GraphicsConfiguration) = asJava.getCapabilities(gc.asJava)
}