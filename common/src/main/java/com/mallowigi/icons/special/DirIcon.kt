/*
 * The MIT License (MIT)
 *
 *  Copyright (c) 2015-2022 Elior "Mallowigi" Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package com.mallowigi.icons.special

import icons.AtomIcons
import java.awt.Component
import java.awt.Graphics
import javax.swing.Icon

/**
 * Dir icon
 *
 * @property closedIcon
 * @property openedIcon
 */
open class DirIcon(val closedIcon: Icon, val openedIcon: Icon) : Icon {
  internal constructor() : this(AtomIcons.Nodes2.FolderOpen, AtomIcons.Nodes2.FolderOpen)

  constructor(icon: Icon) : this(icon, icon)

  /** Paint icon. */
  override fun paintIcon(c: Component?, g: Graphics, x: Int, y: Int): Unit = closedIcon.paintIcon(c, g, x, y)

  /** Icon width. */
  override fun getIconWidth(): Int = closedIcon.iconWidth

  /** Icon height. */
  override fun getIconHeight(): Int = closedIcon.iconHeight

}
