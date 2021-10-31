package es.tmoor.scalawt
import java.awt

object MenuContainer {
  implicit class JMenuContainerImplicits(jMenu: awt.MenuContainer) {
    def jMenuContainerAsScala: MenuContainer = new MenuContainer {
      private[scalawt] val asJava: awt.MenuContainer = jMenu
    }
  }
}
trait MenuContainer {
  private[scalawt] val asJava: awt.MenuContainer
}