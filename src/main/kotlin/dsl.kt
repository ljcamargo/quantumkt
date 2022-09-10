fun circuit(
    length: Int,
    block: Circuit.() -> Unit = {}
): Circuit {
    val circuit = Circuit(length, 0,0)
    circuit.block()
    return circuit
}

fun String.feeding(
    length: Int?= null,
    block: Circuit.() -> Unit = {}
): Flow {
    val circuit = Circuit(length ?: this.length, 0,0)
    circuit.block()
    val input = Input.fromClassical(this, circuit.size)
    return Flow(input, circuit)
}

fun input(
    size: Int,
    block: Circuit.() -> Unit = {}
): Input {
    val circuit = Circuit(size, 0,0)
    circuit.block()
    return Input(circuit)
}

fun output(
    size: Int,
    block: Output.() -> Unit = {}
): Output {
    val output = Output(size)
    output.block()
    return output
}

fun flow(
    input: Input,
    output: Output,
    block: Circuit.() -> Unit = {}
): Flow {
    val flow = Flow(
        input = input,
        body = Circuit(input.circuit.size, 0,0),
        output=output
    )
    flow.circuit.block()
    return flow
}