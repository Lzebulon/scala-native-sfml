package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Font.*

import graphics.Rect
import stdlib.String.stringToStdString

class Font private[sfml] (private[sfml] val font: Ptr[sfFont]) extends Resource:

    override def close(): Unit =
        Font.close(font)()
        Resource.close(font)

    def this() =
        this(Resource { (r: Ptr[sfFont]) => ctor(r) })

    final def loadFromFile(filename: String): Boolean =
        Zone { implicit z => sfFont_loadFromFile(font, filename) }

object Font:
    extension (font: Ptr[sfFont])
        private[sfml] def close(): Unit =
            dtor(font)
