package DemoCube

import Constants._

import scala.math._

class Vec(var i: Double, var j: Double, var k: Double) {
  def length = {
    scala.math.sqrt((i * i) + (j * j) + (k * k) )
  }

  //Return sum of 2 vecs
  def +(vec: Vec): Vec = {
    new Vec(this.i + vec.i, this.j + vec.j, this.k + vec.k)
  }

  //Add vec to this one
  def add(vec: Vec): Unit = {
    this.i += vec.i
    this.j += vec.j
    this.k += vec.k
  }

  def from(point: Point): Point = {
    new Point(point.x + this.i, point.y + this.j, point.z + this.k)
  }

  //Scale this vec in place
  def scale(scalar: Double): Unit = {
    this.i *= scalar
    this.j *= scalar
    this.k *= scalar
  }

  //Get new scaled vec
  def scaled(scalar: Double): Vec = {
    new Vec(this.i * scalar, this.j * scalar, this.k * scalar)
  }

  def angle(vec: Vec): Double = {
    acos((this dot vec) / (this.length * vec.length))
  }

  def dot(vec: Vec): Double = {
    (this.i * vec.i) + (this.j * vec.j) + (this.k * vec.k)
  }

  def cross(vec: Vec): Vec = {
    val x = this.j * vec.k - this.k * vec.j
    val y = this.k * vec.i - this.i * vec.k
    val z = this.i * vec.j - this.j * vec.i
    new Vec(x, y, z)
  }

  //Rotate this vector with matrix around given axis
  def rotate(axis: Char, radians: Double): Unit = {
    val cosine: Double = cos(radians / TickRate)
    val sine: Double = sin(radians / TickRate)
    val i = this.i
    val j = this.j
    val k = this.k
    if (axis == 'x') {
      this.j = (j * cosine) + (k * sine)
      this.k = (j * -sine) + (k * cosine)
    } else if (axis == 'y') {
      this.i = (i * cosine) - (k * sine)
      this.k = (i * sine) + (k * cosine)
    } else if (axis == 'z') {
      this.i = (i * cosine) + (j * sine)
      this.j = (i * -sine) + (j * cosine)
    }
  }

  def lineFrom(point: Point): Line = {
    new Line(point, this.from(point))
  }

  override def toString: String = s"Length: $length (${i}i, ${j}j, ${k}k)"
}

object Vec {
  import scala.util.Random._



  def random(size: Int) = {
    def randomNumber(range: Int) = {
      val number = nextInt(range)
      if (nextBoolean()) number else -number
    }
    new Vec(randomNumber(size), randomNumber(size), randomNumber(size))

  }
}
