
open class Circuit(private val span: Int): Entity() {

    constructor(span: Int, offset: Int, iteration: Int): this(span) {
        this.offset = offset
        this.iteration = iteration
    }

    var offset = 0
    var iteration = 0
    var parentSpan = 0
    var inverseSpan = 0

    val instructions = ArrayList<Instruction>()
    val qbits: Array<Qbit> = Array(span) {
        Qbit(this, it)
    }

    val size: Int
        get() = qbits.size

    val last: Qbit
        get() = qbits.last()

    val first: Qbit
        get() = qbits.first()

    val all: QbitRange
        get() = QbitRange(this, (first upTo last))

    fun q(index: Int) = qbits[index]

    fun sub(start: Int= 0, end: Int?= null, block: Circuit.() -> Unit = {}): Circuit {
        val circuit = offspring(size = (end ?: span) - start, offset = start, iteration = 0)
        circuit.block()
        addChild(circuit)
        return circuit
    }

    fun loop(times: Int = 1, block: Circuit.() -> Unit = {}) {
        repeat(times) {
            this.block()
        }
    }

    fun ascend(step: Int = 1, block: Circuit.() -> Unit = {}) {
        for (i in 0 until span step step) {
            val circuit = offspring(size = i +1, offset = i, iteration = i)
            circuit.block()
            addChild(circuit)
        }
    }

    fun descend(block: Circuit.() -> Unit = {}) {
        for (i in (span-1) downTo 0) {
            val circuit = offspring(size = i + 1, offset = 0, iteration = i)
            circuit.block()
            addChild(circuit)
        }
    }

    private fun offspring(size: Int, offset: Int, iteration: Int): Circuit {
        return Circuit(size, offset, iteration).also {
            it.parentSpan = span
            it.inverseSpan = (1 + parentSpan) - size
        }
    }

    private fun mappedInstructions(offset: Int): ArrayList<Instruction> {
        return ArrayList(instructions.toMutableList().map { ins ->
            ins.origin = if (ins.origin != null) (ins.origin!! + offset) else null
            ins.targets = ins.targets?.map { it + offset }
            ins
        })
    }

    fun append(other: Circuit?): Circuit {
        if (other != null) addChild(other)
        return this
    }

    private fun addChild(child: Circuit) {
        instructions.addAll(
            child.mappedInstructions(offset)
        )
    }

    fun add(instruction: Instruction) {
        instructions.add(instruction)
    }

    override fun toString() = renderer.render(this)

    companion object {
        fun fromClassical(string: String, size: Int?= null): Circuit {
            val circuit = Circuit(span = size ?: string.length)
            string.toCharArray().forEachIndexed { i, it ->
                if (it.toString().toXGate()) circuit.q(i).x()
            }
            return circuit
        }
    }
}