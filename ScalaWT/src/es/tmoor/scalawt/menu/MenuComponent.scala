package es.tmoor.scalawt.menu

import es.tmoor.scalawt
import es.tmoor.scalawt.MenuContainer
import es.tmoor.scalawt.component.Component
import java.awt

abstract class MenuComponent {
  private[scalawt] val asJava: awt.MenuComponent
  final def dispatchEvent(e: scalawt.Event): Unit = asJava.dispatchEvent(e.asJava)
  def name: String = asJava.getName
  def name_=(s: String): Unit = asJava.setName(s)
  def parent: Option[MenuContainer] = Option(asJava.getParent).collect {
    case cmp: awt.Component with awt.MenuContainer =>
      new Component with MenuContainer {
        private[scalawt] val asJava: awt.Component = cmp
      }
    case other =>
      sys.error(s"Expected: Component/MenuContainer, got: ${other.getClass.getSimpleName}")
  }
  // TODO: accessibleContext
  // def accessibleContext: AccessibleContext

}