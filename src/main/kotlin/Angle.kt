open class Angle: Entity() {
    var value: Float?= null
    var symbol: String?= null
    var dividend: Float?= null
    var multiplier: Float?= null

    fun mult(value: Int): Angle {
        multiplier = value.toFloat()
        return this
    }

    fun mult(value: Float): Angle {
        multiplier = value
        return this
    }

    infix fun div(value: Int): Angle {
        dividend = value.toFloat()
        return this
    }

    infix fun div(value: Float): Angle {
        dividend = value
        return this
    }

    override fun toString() = renderer.render(this)

}