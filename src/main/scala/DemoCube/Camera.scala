package DemoCube

import Constants._
import o1._

class Camera {
  var head = new Point(0, 0, - 10 * WorldSize)
  val headVectorSize = 1000
  val vectors = Vector(new Vec(headVectorSize, 0, 0), new Vec(0, headVectorSize, 0), new Vec(0, 0, headVectorSize))
  var lines = Buffer[Line]()

  val background = rectangle(ViewSize, ViewSize, BackGroundColor)

  def draw = {
    var view = background
    for (line <- lines) {
      view = view.place(line.toPic(vectors, head), line.a.relativePos(vectors, head))

    }
    this.lines.clear()
    view
  }

  def addLines(lines: Vector[Line]): Unit = {
    this.lines.appendAll(lines)
  }

  def move(vec: Vec) = {
    this.head = vec.from(head)
  }

  def lookLeft() = {
    this.vectors.foreach(_.rotate('y', 0.5))
  }

  def lookRight() = {
    this.vectors.foreach(_.rotate('y', -0.5))
  }
}
