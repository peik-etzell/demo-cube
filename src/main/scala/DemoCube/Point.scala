package DemoCube

import Constants._
import o1.world.Pos

class Point(val x: Double, val y: Double, val z: Double) {
  def lineTo(point: Point): Line = new Line(this, point)

  def toVec(point: Point): Vec = new Vec(point.x - this.x, point.y - this.y, point.z - this.z)

  def distance(point: Point) = {
    this.toVec(point).length
  }

  def xDiff(point: Point) = this.x - point.x
  def yDiff(point: Point) = this.y - point.y
  def zDiff(point: Point) = this.z - point.z

  def relativePos(vectors: Vector[Vec], head: Point): Pos = { //Projects 3d point to 2d plane relative to view
    val vector = head.toVec(this) // Vector from head to point
    vector.scale(1 / vector.length) //Make vector length 1
    val x = vector.dot(vectors(0))
    val y = vector.dot(vectors(1))
    Pos(x + ViewSize / 2, ViewSize / 2 - y) //Dotproduct with relative x, y -axis for x, y Pos in viewing plane
  }
}
