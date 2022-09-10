class StringRenderer: Renderer() {

    override fun render(angle: Angle): String {
        with(angle) {
            val first = if (symbol != null) symbol else (value ?: 0).toString()
            val second = if (multiplier != null) "*$multiplier" else "/$dividend"
            return "$first$second"
        }
    }

    override fun render(instruction: Instruction): String {
        with(instruction) {
            return "$name ${origin.toString()} ${targets.toString()} ${angles.toString()}"
        }
    }

    override fun render(qbit: Qbit): String {
        with(qbit) {
            return "--- Qbit $index ---"
        }
    }

    override fun render(circuit: Circuit): String {
        with(circuit) {
            val qbitsStr = qbits.fold("") { acc, curr -> "$acc\n$curr" }
            val insStr = instructions.fold("") { acc, curr -> "$acc\n$curr" }
            return "$qbitsStr\n\n$insStr"
        }
    }
}