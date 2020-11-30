package DemoCube

import Constants._
import o1._

class Line(val a: Point, val b: Point) {
  def toVec = a.toVec(b)

  def toPic(vectors: Vector[Vec], head: Point) = {
    line(a.relativePos(vectors, head), b.relativePos(vectors, head), LineColor)
  }
}
