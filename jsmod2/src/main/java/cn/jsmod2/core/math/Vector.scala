/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */

package cn.jsmod2.core.math

/**
  * scala vector
  *
  * 向量类，可以使用在游戏计算当中
  *
  * @author magiclu550#(code) jsmod2
  */

object Vector{
  val ZERO = new Vector(0, 0, 0)

  val ONE = new Vector(1, 1, 1)

  val FORWARD = new Vector(0, 0, 1)

  val BACK = new Vector(0, 0, -1)

  val UP = new Vector(0, 1, 0)

  val DOWN = new Vector(0, -1, 0)

  val RIGHT = new Vector(1, 0, 0)

  val LEFT = new Vector(-1, 0, 0)


  def distance(a: Vector, b: Vector): Double = (a-b).getMagnitude

  def lerp(a: Vector, b: Vector, t: Double): Vector = {
    var to = Math.min(t, 1)
    to = Math.max(to, 0)
    lerpUnclamped(a, b, to)
  }

  def lerpUnclamped(a: Vector, b: Vector, t: Double): Vector = a+(a-b)* t

  def min(a: Vector, b: Vector) = new Vector(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z, b.z))

  def max(a: Vector, b: Vector) = new Vector(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z))

}

class Vector (var x:Double,var y:Double,var z:Double){

  var sqrMagnitude: Double = x * x + y * y + z * z

  def -(vector: Vector): Vector = new Vector(this.x-vector.x,this.y-vector.y,this.z-vector.z)

  def +(vector: Vector): Vector = new Vector(this.x + vector.x, this.y + vector.y, this.z + vector.z)

  def *(vector: Vector): Vector = new Vector(this.x*vector.x,this.y*vector.y,this.z*this.z)

  def /(vector: Vector): Vector = new Vector(this.x/vector.x,this.y/vector.y,this.z/vector.z)

  def -(d:Double): Vector = new Vector(this.x-d,this.y-d,this.z-d)

  def +(d:Double): Vector = new Vector(this.x+d,this.y+d,this.z+d)

  def *(d:Double): Vector = new Vector(this.x*d,this.y*d,this.z*d)

  def /(d:Double): Vector = new Vector(this.x/d,this.y/d,this.z/d)

  def getMagnitude: Double = Math.sqrt(sqrMagnitude)

  def getNormalize: Vector = {
    val num = getMagnitude
    if (num > 9.99999974737875E-06) return this/num
    Vector.ZERO
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Vector]

  override def equals(other: Any): Boolean = other match {
    case that: Vector =>
      (that canEqual this) &&
        sqrMagnitude == that.sqrMagnitude &&
        x == that.x &&
        y == that.y &&
        z == that.z
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(sqrMagnitude, x, y, z)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  def getX() = x
  def getY()= y
  def getZ() = z

  def setX(x:Float) = this.x = x

  def setY(y:Float) = this.y = y

  def setZ(z:Float) = this.z = z

  override def toString() = "("+x+"-"+y+"-"+z+")"

}
