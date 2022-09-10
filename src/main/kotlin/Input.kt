class Input(val circuit: Circuit) {

    fun size() = circuit.size

    companion object {
        fun fromClassical(string: String, size: Int?= null): Input {
            return Input(Circuit.fromClassical(string, size))
        }
    }
}