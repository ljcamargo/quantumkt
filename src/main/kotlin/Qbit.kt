open class Qbit(private val circuit: Circuit): Entity() {

    constructor(circuit: Circuit, index: Int) : this(circuit) {
        this.index = index
    }

    fun x(): Qbit {
        add(X from index)
        return this
    }

    infix fun cx(target: Qbit?): Qbit {
        add(X from (index to target?.index))
        return this
    }

    fun cp(target: Qbit?, theta: Angle): Qbit {
        add(Instruction("CP", index, target?.index, theta))
        return this
    }

    fun h(): Qbit {
        add(H from index)
        return this
    }

    private fun add(instruction: Instruction) {
        circuit.add(instruction)
    }

    override fun toString() = renderer.render(this)

    companion object {
        const val X = "X"
        const val Y = "Y"
        const val Z = "Z"
        const val CX = "CX"
        const val CY = "CY"
        const val CZ = "CZ"
        const val CP = "CP"
        const val H = "H"
        const val READ = "READ"
    }
}