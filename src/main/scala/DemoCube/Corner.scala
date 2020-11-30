package DemoCube

class Corner(i: Double, j: Double, k: Double, val partOf: Cube) extends Vec(i, j, k) {
  def point = {
    this.from(partOf.center)
  }

  def velocity = {
    this.cross(partOf.spin) + partOf.velocity
  }
}
