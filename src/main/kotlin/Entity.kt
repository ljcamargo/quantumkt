open class Entity() {

    var index: Int = 0
    var uid: String?= null
    var name: String?= null
    var meta: String?= null
    var renderer: Renderer = StringRenderer()

    constructor(name: String) : this() {
        this.name = name
    }

}