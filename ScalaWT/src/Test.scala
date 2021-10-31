package es.tmoor.scalawt
import es.tmoor.scalawt.Component
import es.tmoor.scalawt.Component.EventType
import java.awt

object Test extends App {
  val frame = new java.awt.Frame
  val cmp = new Component {
    val asJava: awt.Component = frame
  }
  frame.setVisible(true)
  frame.setSize(640, 480)
  cmp(EventType.Moved) += (e => println(e.asJava))
}