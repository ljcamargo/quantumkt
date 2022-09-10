import kotlin.math.pow

fun main(args: Array<String>) {
    println(
        flow(
            input = input(8) { all.x() },
            output = output(1) { readAll() }
        ) {
            all.x()
            descend {
                last.h()
                descend {
                    last.cp(
                        target = first,
                        theta = pi.div(2f.pow(1 + parentSpan - iteration))
                    )
                }
            }
        }
    )

    println(
        "010101".feeding {
            all.x()
            descend {
                last.h()
                descend {
                    last.cp(
                        target = first,
                        theta = pi.div(2f.pow(1 + parentSpan - iteration))
                    )
                }
            }
        }.readToOne()
    )
    /*val c = circuit(3) {
        all.x()
        descend {
            last.h()
            descend {
                last.cp(
                    target = first,
                    theta = pi.div(2f.pow(1 + parentSpan - iteration))
                )
            }
        }
    }*/
    //println(c.toString())
}