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
package com.mallowigi.config

import com.intellij.ide.ui.LafManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.ui.ColorUtil
import com.intellij.ui.JBColor
import com.intellij.util.ui.UIUtil
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.util.xmlb.annotations.Property
import com.mallowigi.config.listeners.AtomConfigNotifier
import com.mallowigi.config.ui.SettingsForm
import com.mallowigi.tree.arrows.ArrowsStyles
import org.jetbrains.annotations.NonNls

/** Atom file icons config. */
@Suppress("TooManyFunctions")
@State(name = "AtomFileIconsConfig", storages = [Storage("a-file-icons.xml")]) // NON-NLS
class AtomFileIconsConfig : PersistentStateComponent<AtomFileIconsConfig> {
  /** Is enabled new ui icons. */
  @Property
  var isEnabledNewUiIcons: Boolean = false

  /** Whether the file icons are enabled. */
  @Property
  var isEnabledIcons: Boolean = true

  /** Whether the folder icons are enabledd. */
  @Property
  var isEnabledDirectories: Boolean = true

  /** Whether the UI icons are enabled. */
  @Property
  var isEnabledUIIcons: Boolean = true

  /** Whether the monochrome icons are enabled. */
  @Property
  var isMonochromeIcons: Boolean = false

  /** The monochrome color. */
  @NonNls
  @Property
  var monochromeColor: String = DEFAULT_MONOCHROME
    private set

  /** Whether the PSI icons are enabled. */
  @Property
  var isEnabledPsiIcons: Boolean = true

  /** Whether file icons are hidden. */
  @Property
  var isHideFileIcons: Boolean = false

  /** Whether folder icons are hidden. */
  @Property
  var isHideFolderIcons: Boolean = false

  /** Whether the hollow folders are enabled. */
  @Property
  var isUseHollowFolders: Boolean = true

  /** Style of tree expand arrows. */
  @Property
  var arrowsStyle: ArrowsStyles = ArrowsStyles.MATERIAL

  /** Whether custom accent color is enabled. */
  @Property
  var isAccentColorEnabled: Boolean = false

  /** Custom accent color. */
  @NonNls
  @Property
  var accentColor: String = accentColorFromTheme
    private set

  /** Whether custom theme color is enabled. */
  @Property
  var isThemedColorEnabled: Boolean = false

  /** Custom theme color. */
  @NonNls
  @Property
  var themedColor: String = themedColorFromTheme
    private set

  /** Whether custom icon size is enabled. */
  @Property
  var hasCustomIconSize: Boolean = false

  /** Whether custom line height is enabled. */
  @Property
  var hasCustomLineHeight: Boolean = false

  /** Custom icon size. */
  @Property
  var customIconSize: Int = DEFAULT_ICON_SIZE

  /** Custom line height. */
  @Property
  var customLineHeight: Int = DEFAULT_LINE_HEIGHT

  /** Whether low power mode is enabled. */
  @Property
  var isLowPowerMode: Boolean = true

  /** Config state. */
  override fun getState(): AtomFileIconsConfig = this

  /** Load config state from XML. */
  override fun loadState(state: AtomFileIconsConfig): Unit = XmlSerializerUtil.copyBean(state, this)

  /** Fire event when settings are changed. */
  fun fireChanged() {
    ApplicationManager.getApplication().messageBus
      .syncPublisher(AtomConfigNotifier.TOPIC)
      .configChanged(this)
  }

  /**
   * Apply settings
   *
   * @param form
   */
  fun applySettings(form: SettingsForm) {
    isEnabledNewUiIcons = form.isEnabledNewUiIcons
    isEnabledIcons = form.isEnabledIcons
    isEnabledDirectories = form.isEnabledDirectories
    isEnabledUIIcons = form.isEnabledUIIcons
    isMonochromeIcons = form.isEnabledMonochromeIcons
    monochromeColor = form.monochromeColor
    isEnabledPsiIcons = form.isEnabledPsiIcons
    isHideFileIcons = form.isHiddenFileIcons
    isHideFolderIcons = form.isHiddenFolderIcons
    isUseHollowFolders = form.isHollowFoldersEnabled
    arrowsStyle = form.arrowsStyle
    isAccentColorEnabled = form.isAccentColorEnabled
    accentColor = form.accentColor
    isThemedColorEnabled = form.isThemedColorEnabled
    themedColor = form.themedColor
    hasCustomIconSize = form.hasCustomIconSize
    hasCustomLineHeight = form.hasCustomLineHeight
    customIconSize = form.customIconSize
    customLineHeight = form.customLineHeight
    isLowPowerMode = form.isLowPowerMode
    fireChanged()
  }

  /** Reset settings. */
  fun resetSettings() {
    isEnabledNewUiIcons = false
    isEnabledIcons = true
    isEnabledDirectories = true
    isEnabledUIIcons = true
    isMonochromeIcons = false
    monochromeColor = DEFAULT_MONOCHROME // NON-NLS
    isEnabledPsiIcons = true
    isHideFileIcons = false
    isHideFolderIcons = false
    isUseHollowFolders = true
    arrowsStyle = ArrowsStyles.MATERIAL
    isAccentColorEnabled = false
    accentColor = accentColorFromTheme
    isThemedColorEnabled = false
    themedColor = themedColorFromTheme
    hasCustomIconSize = false
    customIconSize = DEFAULT_ICON_SIZE
    hasCustomLineHeight = false
    customLineHeight = DEFAULT_LINE_HEIGHT
    isLowPowerMode = true
    fireChanged()
  }

  //region New UI Icons
  /** Is enabled icons changed. */
  fun isEnabledNewUiIconsChanged(isEnabledNewUiIcons: Boolean): Boolean =
    this.isEnabledNewUiIcons != isEnabledNewUiIcons

  /** Toggle enabled icons. */
  fun toggleNewUiIcons() {
    isEnabledNewUiIcons = !isEnabledNewUiIcons
  }
  //endregion

  //region File Icons
  /** Is enabled icons changed. */
  fun isEnabledIconsChanged(isEnabledIcons: Boolean): Boolean = this.isEnabledIcons != isEnabledIcons

  /** Toggle enabled icons. */
  fun toggleEnabledIcons() {
    isEnabledIcons = !isEnabledIcons
  }
  //endregion

  //region Directory Icons
  /** Is enabled directories changed. */
  fun isEnabledDirectoriesChanged(isEnabledDirectories: Boolean): Boolean =
    this.isEnabledDirectories != isEnabledDirectories

  /** Toggle directories icons. */
  fun toggleDirectoriesIcons() {
    isEnabledDirectories = !isEnabledDirectories
  }
  //endregion

  //region Monochrome Icons
  /** Is monochrome icons changed. */
  fun isMonochromeIconsChanged(isMonochrome: Boolean): Boolean = isMonochromeIcons != isMonochrome

  /** Toggle monochrome icons. */
  fun toggleMonochromeIcons() {
    isMonochromeIcons = !isMonochromeIcons
  }

  /** Is monochrome color changed. */
  fun isMonochromeColorChanged(monochromeColor: String): Boolean =
    this.monochromeColor.lowercase() != monochromeColor.lowercase()
  //endregion

  //region UI Icons
  /** Is enabled ui icons changed. */
  fun isEnabledUIIconsChanged(isEnabledUIIcons: Boolean): Boolean = this.isEnabledUIIcons != isEnabledUIIcons

  /** Toggle ui icons. */
  fun toggleUIIcons() {
    isEnabledUIIcons = !isEnabledUIIcons
  }
  //endregion

  //region PSI Icons
  /** Is enabled psi icons changed. */
  fun isEnabledPsiIconsChanged(isEnabledPsiIcons: Boolean): Boolean = this.isEnabledPsiIcons != isEnabledPsiIcons

  /** Toggle psi icons. */
  fun togglePsiIcons() {
    isEnabledPsiIcons = !isEnabledPsiIcons
  }
  //endregion

  //region Hollow Folders
  /** Is use hollow folders changed. */
  fun isUseHollowFoldersChanged(useHollowFolders: Boolean): Boolean = isUseHollowFolders != useHollowFolders

  /** Toggle use hollow folders. */
  fun toggleUseHollowFolders() {
    isUseHollowFolders = !isUseHollowFolders
  }
  //endregion

  //region Hide File Icons
  /** Is hide file icons changed. */
  fun isHideFileIconsChanged(hideFileIcons: Boolean): Boolean = isHideFileIcons != hideFileIcons

  /** Toggle hide file icons. */
  fun toggleHideFileIcons() {
    isHideFileIcons = !isHideFileIcons
  }
  //endregion

  //region Hide Folder Icons
  /** Is hide folder icons changed. */
  fun isHideFolderIconsChanged(hideFolderIcons: Boolean): Boolean = isHideFolderIcons != hideFolderIcons

  /** Toggle hide folder icons. */
  fun toggleHideFolderIcons() {
    isHideFolderIcons = !isHideFolderIcons
  }
  //endregion

  //region Arrows Style
  /** Is arrows style changed? */
  fun isArrowsStyleChanged(arrowsStyle: ArrowsStyles): Boolean = this.arrowsStyle != arrowsStyle
  //endregion

  //region Accent Color
  /** Is accent color changed. */
  fun isAccentColorChanged(accentColor: String): Boolean = this.accentColor.lowercase() != accentColor.lowercase()

  /** Is accent color enabled changed. */
  fun isAccentColorEnabledChanged(enabled: Boolean): Boolean = this.isAccentColorEnabled != enabled

  /** Get current accent color. */
  fun getCurrentAccentColor(): String {
    if (isAccentColorEnabled) return accentColor
    return accentColorFromTheme
  }

  //endregion

  //region Themed Color
  /** Is themed color changed. */
  fun isThemedColorChanged(themedColor: String): Boolean = this.themedColor.lowercase() != themedColor.lowercase()

  /** Is themed color enabled changed. */
  fun isThemedColorEnabledChanged(enabled: Boolean): Boolean = this.isThemedColorEnabled != enabled

  /** Get current themed color. */
  fun getCurrentThemedColor(): String {
    if (isThemedColorEnabled) return themedColor
    return themedColorFromTheme
  }
  //endregion

  //region Custom Icon size
  /** Is custom icon size changed? */
  fun isHasCustomIconSizeChanged(isCustomIconSize: Boolean): Boolean = hasCustomIconSize != isCustomIconSize

  /** Toggle big icons. */
  fun toggleHasCustomIconSize() {
    hasCustomIconSize = !hasCustomIconSize
  }
  //endregion

  //region Custom Icon size
  /** Is custom icon size changed? */
  fun isCustomIconSizeChanged(newSize: Int): Boolean = newSize != customIconSize

  //endregion

  //region Custom Line Height

  /** Is custom line height changed? */
  fun isHasCustomLineHeightChanged(newHeight: Boolean): Boolean = hasCustomLineHeight != newHeight

  /** Is custom line height changed? */
  fun hasCustomLineHeightChanged(newSize: Int): Boolean = newSize != customLineHeight

  //endregion

  //region Low Power mode
  /** Is low power mode changed. */
  fun isLowPowerModeChanged(lowPower: Boolean): Boolean = isLowPowerMode != lowPower

  /** Toggle low power mode. */
  fun toggleLowPowerMode() {
    isLowPowerMode = !isLowPowerMode
  }
  //endregion

  companion object {
    /** Default Icon Size. */
    const val DEFAULT_ICON_SIZE: Int = 16

    /** min icon size */
    const val MIN_ICON_SIZE: Int = 12

    /** Max icon size. */
    const val MAX_ICON_SIZE: Int = 24

    /** Default Line Height. */
    const val DEFAULT_LINE_HEIGHT: Int = 20

    /** Min Line Hieght. */
    const val MIN_LINE_HEIGHT: Int = 16

    /** Max Line Height. */
    const val MAX_LINE_HEIGHT: Int = 60

    /** Default Monochrome. */
    const val DEFAULT_MONOCHROME: String = "546E7A" // NON-NLS

    /** Instance of the Config service. */
    @JvmStatic
    val instance: AtomFileIconsConfig
      get() = ApplicationManager.getApplication().getService(AtomFileIconsConfig::class.java)

    private val accentColorFromTheme: String
      get() = getAccentFromTheme()

    private val themedColorFromTheme: String
      get() = getThemedFromTheme()

    /** Extract accent color from current theme. */
    @Suppress("HardCodedStringLiteral")
    private fun getAccentFromTheme(): String {
      val namedKey = when (LafManager.getInstance().currentLookAndFeel?.name) {
        "IntelliJ Light" -> "ActionButton.focusedBorderColor"
        "Light"          -> "ActionButton.focusedBorderColor"
        "Darcula"        -> "Button.select"
        else             -> "Link.activeForeground"
      }

      val namedColor = JBColor.namedColor(
        namedKey,
        UIUtil.getButtonSelectColor() ?: UIUtil.getListSelectionForeground(true)
      )
      return ColorUtil.toHex(namedColor)
    }

    /** Extract themed color from current theme. */
    private fun getThemedFromTheme(): String =
      ColorUtil.toHex(JBColor.namedColor("Tree.foreground", UIUtil.getLabelForeground()))
  }
}
