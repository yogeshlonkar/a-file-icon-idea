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
@file:Suppress("Detekt.TooManyFunctions")

package com.mallowigi.config

import com.intellij.application.options.editor.CheckboxDescriptor
import com.intellij.ide.ui.OptionsSearchTopHitProvider.ApplicationLevelProvider
import com.intellij.ide.ui.search.BooleanOptionDescription
import com.intellij.ide.ui.search.OptionDescription
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.util.text.StringUtil
import com.intellij.ui.layout.PropertyBinding
import com.mallowigi.config.AtomSettingsBundle.message
import org.jetbrains.annotations.NonNls
import java.util.Collections

/** Text. */
@NlsContexts.Checkbox
fun getText(property: String): String =
  StringUtil.stripHtml(message("settings.titles.prefix", message(property)), false)

/** Config instance. */
fun getInstance(): AtomFileIconsConfig = AtomFileIconsConfig.instance

/** region Checkbox Definitions. */

/** Accent color enabled. */
fun cdAccentColorEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.accentColorCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isAccentColorEnabled },
                  { AtomFileIconsConfig.instance.isAccentColorEnabled = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Directory Icons. */
fun cdDirectoryIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.enableDirectoryIconsCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isEnabledDirectories },
                  { AtomFileIconsConfig.instance.isEnabledDirectories = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** File Icons. */
fun cdFileIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.enableFileIconsCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isEnabledIcons },
                  { AtomFileIconsConfig.instance.isEnabledIcons = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** PSI Icons. */
fun cdPsiIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.enablePSIIconsCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isEnabledPsiIcons },
                  { AtomFileIconsConfig.instance.isEnabledPsiIcons = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** UI Icons. */
fun cdUIIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.enableUIIconsCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isEnabledUIIcons },
                  { AtomFileIconsConfig.instance.isEnabledUIIcons = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Hide File Icons. */
fun cdHideFileIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.hideFileIconsCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isHideFileIcons },
                  { AtomFileIconsConfig.instance.isHideFileIcons = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Hide Folder Icons. */
fun cdHideFolderIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.hideFolderIconsCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isHideFolderIcons },
                  { AtomFileIconsConfig.instance.isHideFolderIcons = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Hollow Folders. */
fun cdHollowFoldersEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.hollowFoldersCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isUseHollowFolders },
                  { AtomFileIconsConfig.instance.isUseHollowFolders = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Monochrome Icons. */
fun cdMonochromeIconsEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.monochromeCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isMonochromeIcons },
                  { AtomFileIconsConfig.instance.isMonochromeIcons = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Themed Color. */
fun cdThemedColorEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.themedColorCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isThemedColorEnabled },
                  { AtomFileIconsConfig.instance.isThemedColorEnabled = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Custom Icon Size. */
fun cdCustomIconSizeEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.customIconSizeCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.hasCustomIconSize },
                  { AtomFileIconsConfig.instance.hasCustomIconSize = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Custom Line Height. */
fun cdCustomLineHeightEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.customLineHeightCheckbox.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.hasCustomLineHeight },
                  { AtomFileIconsConfig.instance.hasCustomLineHeight = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }

/** Low Power Mode. */
fun cdLowPowerModeEnabled(): BooleanOptionDescription = CheckboxDescriptor(
  getText("SettingsForm.lowPowerSwitch.text"),
  PropertyBinding({ AtomFileIconsConfig.instance.isLowPowerMode },
                  { AtomFileIconsConfig.instance.isLowPowerMode = it })
).asOptionDescriptor { AtomFileIconsConfig.instance.fireChanged() }
// endregion

/** Provide commands for Search Everything Top Hit commands. */
@Suppress("DialogTitleCapitalization")
class AtomConfigTopHitProvider : ApplicationLevelProvider {

  /** ID. */
  @NonNls
  override fun getId(): String = "atomconfig"

  /** Option list. */
  override fun getOptions(): Collection<OptionDescription> = OPTION_DESCRIPTIONS

  companion object {

    @NonNls
    private val OPTION_DESCRIPTIONS: Collection<OptionDescription> = Collections.unmodifiableCollection(
      listOf(
        cdAccentColorEnabled(),
        cdDirectoryIconsEnabled(),
        cdFileIconsEnabled(),
        cdPsiIconsEnabled(),
        cdUIIconsEnabled(),
        cdHideFileIconsEnabled(),
        cdHideFolderIconsEnabled(),
        cdHollowFoldersEnabled(),
        cdMonochromeIconsEnabled(),
        cdThemedColorEnabled(),
        cdCustomLineHeightEnabled(),
        cdCustomIconSizeEnabled(),
        cdLowPowerModeEnabled()
      )
    )

  }

}
