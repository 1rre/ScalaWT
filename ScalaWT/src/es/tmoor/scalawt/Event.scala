package es.tmoor.scalawt
import java.awt

abstract class Event {
  private[scalawt] val asJava: awt.AWTEvent
}