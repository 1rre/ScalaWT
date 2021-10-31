package es.tmoor.scalawt.image
import java.awt
import es.tmoor.scalawt.SWTRoot

object ColourModel {
}
abstract class ColourModel(pixelBits: Int) extends SWTRoot {
  protected def this(
      pixelBits: Int,
      bits: Seq[Int],
      colourSpace: ColourSpace,
      hasAlpha: Boolean,
      isAlphaPremultiplied: Boolean,
      transparancy: Int,
      transferType: Int
  )
  private[scalawt] val asJava: awt.image.ColorModel = new awt.image.ColorModel(pixelBits) {
private[scalawt] val asJava: awt.image.ColorModel
  }
}