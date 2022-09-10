import java.lang.Integer.max

class Flow(val circuit: Circuit) {

    private var input: Input?= null
    var output: Output?= null
    private var body: Circuit?= null

    constructor(input: Input, body: Circuit, output: Output?= null) : this(input.circuit) {
        this.input = input
        this.output = output
        this.body = body
    }

    fun size(): Int = listOf(input?.size() ?: 0, body?.size ?: 0, output?.size() ?: 0).max()

    private fun merged(): Circuit? {
        body?.size
        val max = listOf(
            input?.circuit?.size ?: 0,
            body?.size ?: 0,
            output?.circuit?.size ?: 0
        ).maxBy { it }
        return (input?.circuit ?: Circuit(max)) plus (body ?: Circuit(max)) plus output?.circuit
    }

    override fun toString(): String {
        return merged().toString()
    }
}