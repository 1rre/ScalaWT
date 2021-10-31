package es.tmoor.scalawt
import java.awt
import awt.event.ComponentEvent
import es.tmoor.scalawt

object Component {
  implicit class JComponent2Scala(c: awt.Component) {
    def asScala: Component = new Component {
      val asJava = c
    }
  }
  object EventType extends Enumeration {
    val Moved, Shown, Hidden, Resized = Value
  }
  object Event {
    implicit class JComponentEvent2Scala(c: awt.event.ComponentEvent) {
      def asScala: Event = {
        new Event(c.getComponent.asScala, c.getID) {
          val asJava = c
        }
      }
    }
  }
  abstract class Event(source: Component, id: Int) extends scalawt.Event {
    val asJava: awt.event.ComponentEvent
    def component: Component = asJava.getComponent.asScala
    def paramString: String = asJava.paramString()
  }
}
abstract class Component {
  import Component._
  import Component.Event._
  val asJava: awt.Component
  private class NilEventListener extends awt.event.ComponentListener {
    override def componentResized(e: ComponentEvent): Unit = {}
    override def componentMoved(e: ComponentEvent): Unit = {}
    override def componentShown(e: ComponentEvent): Unit = {}
    override def componentHidden(e: ComponentEvent): Unit = {}
  }
  sealed abstract class EventAdder {
    def +=(ev: Event => Unit): Unit
  }
  private class HiddenEventAdder extends EventAdder {
    def +=(ev: Event => Unit): Unit = {
      asJava.addComponentListener(new NilEventListener {
        override def componentHidden(e: ComponentEvent): Unit = ev(e.asScala)
      })
    }
  }
  private class ShowEventAdder extends EventAdder {
    def +=(ev: Event => Unit): Unit = {
      asJava.addComponentListener(new NilEventListener {
        override def componentShown(e: ComponentEvent): Unit = ev(e.asScala)
      })
    }
  }
  private class ResizeEventAdder extends EventAdder {
    def +=(ev: Event => Unit): Unit = {
      asJava.addComponentListener(new NilEventListener {
        override def componentResized(e: ComponentEvent): Unit = ev(e.asScala)
      })
    }
  }
  private class MoveEventAdder extends EventAdder {
    def +=(ev: Event => Unit): Unit = {
      asJava.addComponentListener(new NilEventListener {
        override def componentMoved(e: ComponentEvent): Unit = ev(e.asScala)
      })
    }
  }
  def apply(on: EventType.Value): EventAdder = 
    on match {
      case EventType.Hidden => new HiddenEventAdder
      case EventType.Moved => new MoveEventAdder
      case EventType.Resized => new ResizeEventAdder
      case EventType.Shown => new ShowEventAdder
    }
  def +=(popup: PopupMenu): Unit = asJava.add(popup.asJava)
}
