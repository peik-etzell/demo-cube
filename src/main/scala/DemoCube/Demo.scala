package DemoCube

import Constants._
import o1._

object Demo extends App {
  val cube = new Cube(3.0)
  val container = new Cube(2 * WorldSize)
  val camera = new Camera

  val gui = new View(cube, TickRate, "Cube") {
    def makePic = {
      camera.draw
    }

    /*val keyMap = Map(
        Key.X -> (cube.xSpin += 0.01),
        Key.C -> (cube.ySpin += 0.01),
        Key.Z -> (cube.zSpin += 0.01),
        Key.Q -> (camera.lookLeft()),
        Key.E -> (camera.lookRight()),
        Key.W -> (camera.move(camera.vectors(2).scaled(0.01))),
        Key.A -> (camera.move(camera.vectors(0).scaled(-0.01))),
        Key.S -> (camera.move(camera.vectors(2).scaled(-0.01))),
        Key.D -> (camera.move(camera.vectors(0).scaled(0.01)))
      ).withDefaultValue(println("no such keymap"))*/

    override def onKeyDown(key: Key) = {
      if (key == Key.Q) {
        camera.lookLeft()
      } else if (key == Key.E) {
        camera.lookRight()
      } else if (key == Key.W) {
        camera.move(camera.vectors(2).scaled(0.0001))
      } else if (key == Key.A) {
        camera.move(camera.vectors(0).scaled(-0.0001))
      } else if (key == Key.S) {
        camera.move(camera.vectors(2).scaled(-0.0001))
      } else if (key == Key.D) {
        camera.move(camera.vectors(0).scaled(0.0001))
      }
    }

    override def onTick(): Unit = {
      camera.addLines(cube.lines)
      camera.addLines(container.lines)
      cube.tick()
    }
  }
  gui.start()

}
