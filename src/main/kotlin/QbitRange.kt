class QbitRange(
    private val circuit: Circuit,
    private val range: IntProgression
) {

    constructor(
        circuit: Circuit,
        start: Int,
        end: Int,
        inverse: Boolean = false
    ) : this(
        circuit,
        if (!inverse) (start..end) else (end downTo start)
    )

    fun x(): QbitRange {
        range.forEach { add(X from it) }
        return this
    }

    infix fun cx(target: Qbit?): QbitRange {
        range.forEach { X from (it to target?.index) }
        return this
    }

    fun cp(target: Qbit?, theta: Angle): QbitRange {
        range.forEach { circuit.add(Instruction("CP", origin = it, target?.index, theta)) }
        return this
    }

    fun h(): QbitRange {
        range.forEach { circuit.add(H from it) }
        return this
    }

    private fun add(instruction: Instruction) {
        circuit.add(instruction)
    }

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