package es.tmoor.scalawt
import es.tmoor.scalawt.component.Component
import es.tmoor.scalawt.component.Component.Event
import java.awt

object Test extends App {
  val frame = new java.awt.Frame
  val cmp = new Component {
    val asJava: awt.Component = frame
  }
  frame.setVisible(true)
  frame.setSize(640, 480)
  cmp.Moved += (e => println(e.asJava))
}