class Output(private val size: Int, private val classicalSize: Int?= null) {
    var circuit = Circuit(size)
    val first = 0
    val last: Int get() = size - 1

    fun size() = circuit.size

    infix fun Qbit.writeTo(index: Int) {
        circuit.add(Qbit.READ from (this.index to index))
    }

    infix fun Int.writeTo(index: Int) {
        circuit.add(Qbit.READ from (this to index))
    }

    fun readAll() {
        if (classicalSize == null || classicalSize == circuit.size) {
            circuit.qbits.forEach {
                circuit.add(
                    Qbit.READ from (it.index to it.index)
                )
            }
        } else {
            circuit.qbits.forEach {
                circuit.add(
                    Qbit.READ from (it.index to it.index)
                )
            }
        }
    }

    fun readAllReverse() {
        if (size == circuit?.size) {
            circuit?.qbits?.reversed()?.forEach {
                circuit?.add(
                    Qbit.READ from (it.index to it.index)
                )
            }
        } else {
            circuit?.qbits?.reversed()?.forEach {
                circuit?.add(
                    Qbit.READ from (it.index to it.index)
                )
            }
        }
    }
}