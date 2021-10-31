package es.tmoor.scalawt.component
import java.awt
import awt.event.ComponentEvent
import es.tmoor.scalawt
import scalawt.{ImageObserver, MenuContainer}
import es.tmoor.scalawt.menu._

object Component {
  implicit class JComponent2Scala(c: awt.Component) {
    def asScala: Component = new Component {
      private[scalawt] val asJava: awt.Component = c
    }
  }
  object Event {
    implicit class JComponentEvent2Scala(c: awt.event.ComponentEvent) {
      def asScala: Event = new Event(c.getComponent.asScala, c.getID, c)
    }
  }
  class Event(
      source: Component,
      id: Int,
      private[scalawt] val asJava: awt.event.ComponentEvent
  ) extends scalawt.Event {
    def component: Component = source
    def paramString: String = asJava.paramString
  }
}
abstract class Component extends MenuContainer with ImageObserver {
  import Component._
  import Component.Event._
  private class LambdaEventListener(
      onSize: Event => Unit = _ => {},
      onMove: Event => Unit = _ => {},
      onShow: Event => Unit = _ => {},
      onHide: Event => Unit = _ => {}
  ) extends awt.event.ComponentListener {
    override def componentResized(e: ComponentEvent): Unit = onSize(e.asScala)
    override def componentMoved(e: ComponentEvent): Unit = onMove(e.asScala)
    override def componentShown(e: ComponentEvent): Unit = onShow(e.asScala)
    override def componentHidden(e: ComponentEvent): Unit = onHide(e.asScala)
  }
  private[scalawt] val asJava: awt.Component
  sealed trait EventType {
    def +=(e: Event => Unit): Unit
  }
  object Moved extends EventType {
    def +=(e: Event => Unit): Unit = {
      asJava.addComponentListener(new LambdaEventListener(onMove = e))
    }
  }
  object Resized extends EventType {
    def +=(e: Event => Unit): Unit = {
      asJava.addComponentListener(new LambdaEventListener(onSize = e))
    }
  }
  object Hidden extends EventType {
    def +=(e: Event => Unit): Unit = {
      asJava.addComponentListener(new LambdaEventListener(onHide = e))
    }
  }
  object Shown extends EventType {
    def +=(e: Event => Unit): Unit = {
      asJava.addComponentListener(new LambdaEventListener(onShow = e))
    }
  }
  def +=(popup: PopupMenu): Unit = asJava.add(popup.asJava)
}
