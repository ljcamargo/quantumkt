abstract class Renderer {

    abstract fun render(angle: Angle): String
    abstract fun render(instruction: Instruction): String
    abstract fun render(qbit: Qbit): String
    abstract fun render(circuit: Circuit): String

}