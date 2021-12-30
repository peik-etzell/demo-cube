package DemoCube

import Constants._

import scala.math._

class Cube(val sideLength: Double) {
  //Used in checking corners trajectory
  val xAxis = new Vec(1, 0, 0)
  val yAxis = new Vec(0, 1, 0)
  val zAxis = new Vec(0, 0, 1)

  //Vectors
  var spin = Vec.random(5)
  var velocity = Vec.random(10)
  val gravity = new Vec(0, -Gravity/TickRate, 0)

  //Corner objects (Vecs)
  val l = 0.5 * sideLength
  val a = new Corner(l, l, l, this)
  val b = new Corner(l, l, -l, this)
  val c = new Corner(l, -l, l, this)
  val d = new Corner(-l, l, l, this)
  val e = new Corner(l, -l, -l, this)
  val f = new Corner(-l, -l, l, this)
  val g = new Corner(-l, l, -l, this)
  val h = new Corner(-l, -l, -l, this)

  //[Corner]
  val corners = Vector(a, b, c, d, e, f, g, h)

  //[Point]
  var center = new Point(0, 0, 0)


  //Defines the lines between the correct corners
  def lines = {
    Vector(
      new Line(a.point, b.point),
      new Line(a.point, c.point),
      new Line(a.point, d.point),
      new Line(b.point, e.point),
      new Line(b.point, g.point),
      new Line(c.point, e.point),
      new Line(c.point, f.point),
      new Line(d.point, f.point),
      new Line(d.point, g.point),
      new Line(e.point, h.point),
      new Line(f.point, h.point),
      new Line(g.point, h.point),
      //this.velocity.lineFrom(center),
      //this.a.velocity.lineFrom(a.point)
    )
  }

  //Reset
  def reset() = {
    this.spin = Vec.random(5)
    this.velocity = Vec.random(10)
    this.center = new Point(0, 0, 0)
  }

  //Calculates the collision semi-realistically
  def impact(corner: Corner, axis: Char) = {
    val cornerVel = corner.velocity
    val inverseVel = cornerVel.scaled(-1.0 / pow(sideLength, 2) )
    this.spin = inverseVel.cross(corner)
    this.spin.scale(Damping)
    axis match {
      // case 'x' => this.velocity.i *= -0.7
      // case 'y' => this.velocity.j *= -0.7
      // case 'z' => this.velocity.k *= -0.7
      case 'x' => this.velocity.i *= -1
      case 'y' => this.velocity.j *= -1
      case 'z' => this.velocity.k *= -1
    }
    // this.velocity add inverseVel.scaled(0.3)
    this.velocity.scale(Damping)
  }

  //Gravity
  def fall() = {
    this.velocity = this.velocity + this.gravity
  }
  //Move centerpoint according to velocity
  def move() = {
    this.center = this.velocity.scaled(1.0 / TickRate).from(this.center)
  }

  //Return corners with lowest and highest x, y, z coordinates
  def boundsX = {
    val x = corners.sortBy(_.point.x)
    (x.head, x.last)
  }
  def boundsY = {
    val y = corners.sortBy(_.point.y)
    (y.head, y.last)
  }
  def boundsZ = {
    val z = corners.sortBy(_.point.z)
    (z.head, z.last)
  }

  //Gets called every tick
  def tick() {
    // this.move()
    // this.fall()
    corners.foreach(_.rotate('x', spin.i))
    corners.foreach(_.rotate('y', spin.j))
    corners.foreach(_.rotate('z', spin.k))

    val x = boundsX
    val y = boundsY
    val z = boundsZ

    //Checks if any corner is outside the world AND has a trajectory going outwards
    if (boundsX._1.point.x < -WorldSize && boundsX._1.velocity.dot(xAxis) < 0) {
      impact(boundsX._1, 'x')
    } else if (boundsX._2.point.x > WorldSize && boundsX._2.velocity.dot(xAxis) > 0) {
      impact(boundsX._2, 'x')
    } else if (boundsY._1.point.y < -WorldSize && boundsY._1.velocity.dot(yAxis) < 0) {
      impact(boundsY._1, 'y')
    } else if (boundsY._2.point.y > WorldSize && boundsY._2.velocity.dot(yAxis) > 0) {
      impact(boundsY._2, 'y')
    } else if (boundsZ._1.point.z < -WorldSize && boundsZ._1.velocity.dot(zAxis) < 0) {
      impact(boundsZ._1, 'z')
    } else if (boundsZ._2.point.z > WorldSize && boundsZ._2.velocity.dot(zAxis) > 0) {
      impact(boundsZ._2, 'z')
    }
    // this.fall()
    this.move()
    this.fall()
  }
}
