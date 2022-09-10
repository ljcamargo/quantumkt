infix fun Int.pow(value: Int): Int {
    return this.pow(value)
}

infix fun Qbit.until(other: Qbit): IntProgression {
    return this.index until other.index
}

infix fun Qbit.upTo(other: Qbit): IntProgression {
    return this.index..other.index
}

infix fun String.from(index: Int): Instruction {
    return Instruction(this, origin = index)
}

infix fun String.from(pair: Pair<Int, Int?>): Instruction {
    return Instruction(this, origin = pair.first, target = pair.second)
}

infix fun Input.feed(circuit: Circuit): Flow {
    return Flow(input = this, body = Circuit(circuit.size, 0,0))
}

infix fun Flow.then(output: Output): Flow {
    this.output = output
    return this
}

infix fun Circuit?.plus(other: Circuit?): Circuit? {
    return this?.append(other)
}

fun String.toXGate(): Boolean {
    return listOf("1","X", "x").contains(this)
}

fun String.asInput(size: Int?= null): Input {
    return Input.fromClassical(this, size)
}

infix fun String.feeding(circuit: Circuit): Flow {
    val input = Input.fromClassical(this, circuit.size)
    return Flow(input, circuit)
}

fun Flow.readToOne(): Flow {
    this.output = Output(this.size(), classicalSize = 1).apply { readAll() }
    return this
}

fun Flow.readEach(): Flow {
    this.output = Output(this.size()).apply { readAll() }
    return this
}

infix fun Flow.read(qbits: List<Int>): Flow {
    this.output = Output(this.size(), qbits.size).apply {
        qbits.forEachIndexed { index, qbit ->
            qbit writeTo index
        }
    }
    return this
}