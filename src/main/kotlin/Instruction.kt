class Instruction(name: String): Entity(name) {

    var origin: Int?= null
    var targets: List<Int>?= null
    var angles: List<Angle>?= null

    constructor(
        name: String, origin: Int?, target: Int?=null, angle: Angle?=null): this(name) {
        this.origin = origin
        targets = target?.toList()
        angles = angle?.toList()
    }

    private fun <T>T?.toList() = if (this == null) listOf() else listOf(this)

    override fun toString() = renderer.render(this)

}