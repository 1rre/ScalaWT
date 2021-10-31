package es.tmoor.scalawt.image

import es.tmoor.scalawt.SWTRoot
import java.awt

object ColourSpace {
  sealed class ColourSpaceFromID(n: Int) extends ColourSpace(awt.color.ColorSpace.getInstance(n))
  object CIEXYZ extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object Gray extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object LinearRGB extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object PYCC extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object SRGB extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  private def matchNColour(n: Int): Int = n match {
    case 2 => awt.color.ColorSpace.TYPE_2CLR
    case 3 => awt.color.ColorSpace.TYPE_3CLR
    case 4 => awt.color.ColorSpace.TYPE_4CLR
    case 5 => awt.color.ColorSpace.TYPE_5CLR
    case 6 => awt.color.ColorSpace.TYPE_6CLR
    case 7 => awt.color.ColorSpace.TYPE_7CLR
    case 8 => awt.color.ColorSpace.TYPE_8CLR
    case 9 => awt.color.ColorSpace.TYPE_9CLR
    case 10 => awt.color.ColorSpace.TYPE_ACLR
    case 11 => awt.color.ColorSpace.TYPE_BCLR
    case 12 => awt.color.ColorSpace.TYPE_CCLR
    case 13 => awt.color.ColorSpace.TYPE_DCLR
    case 14 => awt.color.ColorSpace.TYPE_ECLR
    case 15 => awt.color.ColorSpace.TYPE_FCLR
    case _ => sys.error(s"AWT does not support $n component colour spaces")
  }
  case class NColour(n: Int) extends ColourSpaceFromID(matchNColour(n))
  object Clr3 extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object Clr4 extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object Clr5 extends ColourSpaceFromID(awt.color.ColorSpace.CS_CIEXYZ)
  object CMY extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_CMY)
  object CMYK extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_CMYK)
  object Lab extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_Lab)
  object Luv extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_Luv)
  object RGB extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_RGB)
  object XYZ extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_XYZ)
  object YCbCr extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_YCbCr)
  object Yxy extends ColourSpaceFromID(awt.color.ColorSpace.TYPE_Yxy)
}

sealed abstract class ColourSpace(private[scalawt] val asJava: awt.color.ColorSpace)
    extends SWTRoot {
  def fromCIEXYZ(colourValue: Seq[Float]): Seq[Float] = asJava.fromCIEXYZ(colourValue.toArray)
  def fromRGB(colourValue: Seq[Float]): Seq[Float] = asJava.fromRGB(colourValue.toArray)
  def maxValue(component: Int): Float = asJava.getMaxValue(component)
  def minValue(component: Int): Float = asJava.getMinValue(component)
  def componentName(i: Int): String = asJava.getName(i)
  def nComponents: Int = asJava.getNumComponents
  def toCIEXYZ(colourValue: Seq[Float]): Seq[Float] = asJava.toCIEXYZ(colourValue.toArray)
  def toRGB(colourValue: Seq[Float]): Seq[Float] = asJava.toRGB(colourValue.toArray)
}
