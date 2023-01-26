# TextraTypist
![Animated preview](images/logo_animated.gif)

A text-display library centered around a label that prints over time, with both effects and styles.

In other words, this brings more features to text rendering in libGDX.

What does this look like? A little something like this...

![Still preview](https://i.imgur.com/wZ9NhJ2.png)

Or perhaps like this...

![Animated preview](images/preview.gif)

If you'd rather watch a video than read this text,
[Raymond "raeleus" Buckley made a video covering most of TextraTypist](https://www.youtube.com/watch?v=4rLoa_jycN8)!
It covers some things this file doesn't, such as usage of Skin Composer, so it's a good watch regardless.

## It's got labels!

There's a "normal" label here in the form of TextraLabel, which acts almost exactly like Label in scene2d.ui, but
allows the styles covered below. A lot of usage may prefer TypingLabel, though!

TypingLabel is a fairly normal scene2d.ui widget, and extends TextraLabel. However, it puts letters up on
the screen one at a time, unless it is told to skip ahead. This is a nostalgic effect found in many older text-heavy
games, and it looks like a typewriter is putting up each letter at some slower-than-instantaneous rate.

## It's got effects!

Yes, it has more than the typewriter mode! Text can hang above and then drop into place. It can jump up and down in a
long wave. It can waver and shudder, as if it is sick. It can blink in different colors, move in a gradient smoothly
between two colors, or go across a whole rainbow. Lots of options; lots of fun. Effects are almost the same as in
typing-label, but there have been some changes. You can check [the TextraTypist wiki](https://github.com/tommyettinger/textratypist/wiki/Examples)
for more information.

As of 0.7.5, there are many new effects. Jolt, Spiral, Spin, Crowd, Shrink, Emerge, Heartbeat, Carousel, Squash, Scale,
Rotate, Attention, Highlight, Link, Trigger, and Stylist are all new to TextraTypist (not in typing-label). You can see
usage instructions and sample GIFs at
[the TextraTypist wiki's Tokens page](https://github.com/tommyettinger/textratypist/wiki/Tokens). Most of these effects
make use of the smooth scaling and rotation options that effects can use starting in TextraTypist 0.5.1 . Some make use
of mouse tracking, new in 0.7.0, such as how Link only responds to a click on a range of text.

You may want to create `TypingLabel`s even where you don't need the typing effect, because `TextraLabel` doesn't handle
any effects. You can call `skipToTheEnd()` on a TypingLabel or (in 0.7.0 and up) on some other classes to allow a
TypingLabel to be used for still text with effects.

## And now, it's got style!

This library extends what the original typing-label can do -- it allows styles to be applied to text, such as bold,
underline, oblique, superscript, etc. Related to styles are scale changes, which can shrink or enlarge text without
changing your font, and the "font family" feature. A font can be assigned a "family" of other fonts and names to use to
refer to them; this acts like a normal style, but actually changes what Font is used to draw. The full list of styles is
long, but isn't as detailed as the effect tokens. You can enable styles with something like libGDX color markup, in
square brackets like `[*]`, or you can use `{STYLE=BOLD}` to do the same thing. Tags and style names are both
case-insensitive, but color names are case-sensitive. The full list of styles:

- `[*]` toggles bold mode. Can use style names `*`, `B`, `BOLD`, `STRONG`.
- `[/]` toggles oblique mode (like italics). Can use style names `/`, `I`, `OBLIQUE`, `ITALIC`.
- `[^]` toggles superscript mode (and turns off subscript or midscript mode). Can use style names `^`, `SUPER`, `SUPERSCRIPT`.
- `[=]` toggles midscript mode (and turns off superscript or subscript mode). Can use style names `=`, `MID`, `MIDSCRIPT`.
- `[.]` toggles subscript mode (and turns off superscript or midscript mode). Can use style names `.`, `SUB`, `SUBSCRIPT`.
- `[_]` toggles underline mode. Can use style names `_`, `U`, `UNDER`, `UNDERLINE`.
- `[~]` toggles strikethrough mode. Can use style names `~`, `STRIKE`, `STRIKETHROUGH`.
- `[!]` toggles all upper case mode (replacing any other case mode). Can use style names `!`, `UP`, `UPPER`.
- `[,]` toggles all lower case mode (replacing any other case mode). Can use style names `,`, `LOW`, `LOWER`.
- `[;]` toggles capitalize each word mode (replacing any other case mode). Can use style names `;`, `EACH`, `TITLE`.
- `[%DDD]`, where DDD is a percentage from 0 to 375, scales text to that multiple. Can be used with `{SIZE=150%}`, `{SIZE=%25}`, or similarly, `{STYLE=200%}` or `{STYLE=%125}`.
- `[%]` on its own sets text to the default 100% scale. Can be used with `{STYLE=%}`.
- `[%?MODE]` removes the scale and sets a special mode; modes are listed below.
- `[%^MODE]` removes the scale and sets a special mode at the same time as small-caps mode; modes are listed below.
- `[@Name]`, where Name is a key/name in this Font's `family` variable, switches the current typeface to the named one. Can be used with `{STYLE=@Name}`.
- `[@]` on its own resets the typeface to this Font, ignoring its family. Can be used with `{STYLE=@}`.
- `[#HHHHHHHH]`, where HHHHHHHH is a hex RGB888 or RGBA8888 int color, changes the color. This is a normal `{COLOR=#HHHHHHHH}` tag.
- `[COLORNAME]`, where COLORNAME is a color name or description that will be looked up externally, changes the color.
  - By default, this looks up COLORNAME with `ColorUtils.describe()`, which tries to find any colors from `Palette` by
    name, and also allows describing mixes of colors or simple changes like "light" or "dull".
    - Palette contains all the UPPER_CASE names from libGDX's `Colors` class, and also about 50 additional lowercase
      color names (from [colorful-gdx](https://github.com/tommyettinger/colorful-gdx)).
      - You can preview `Palette` [by hue](https://tommyettinger.github.io/textratypist/ColorTableHue.html), [by lightness](https://tommyettinger.github.io/textratypist/ColorTableLightness.html), or [by name](https://tommyettinger.github.io/textratypist/ColorTableAlphabetical.html).
    - Adjectives can be "light", "dark", "rich", "dull", or stronger versions of those suffixed with "-er", "-est", or
      "-most". You can use any combination of adjectives, and can also combine multiple colors, such as "red orange". 
      - There are some more adjectives that act like pairs of the above four adjectives, for convenience:
        - Combining "light" and "rich" is the same as "bright".
        - Combining "light" and "dull" is the same as "pale".
        - Combining "dark" and "rich" is the same as "deep".
        - Combining "dark" and "dull" is the same as "weak".
    - Some examples: `[RED]`, `[green yellow]`, `[light blue]`, `[duller orange]`, `[darker rich BLUE lavender]`, `[pale pink orange]`, and `[deeper green navy]`.
    - There's a section at the bottom of this README.md that covers some tricky parts of color descriptions.
  - You can use `Font`'s `setColorLookup()` method with your own `ColorLookup` implementation to do what you want here.
    - This isn't a commonly-used feature, but could be handy for some more elaborate color handling.
  - The name can optionally be preceded by `|`, which allows looking up colors with names that contain punctuation.
    For example, `[|;_;]` would look up a color called `;_;`, "the color of sadness," and would not act like `[;]`.
    - Non-alphabetical characters are ignored by default, but a custom `ColorLookup` might not, nor does
      `ColorLookup.INSTANCE`, which looks up String names in the libGDX Colors class verbatim.
  - This also can be used with a color tag, such as `{COLOR=SKY}` (which Colors can handle right away) or
    with a description, such as `{COLOR=lighter orange-red}`, even inside a tag like `{GRADIENT}`.
- `[+region name]`, where region name is the name of a TextureRegion from a registered TextureAtlas, won't change the
  style, but will produce that TextureRegion in-line with the text.
  - This is commonly used with `KnownFonts.addEmoji()` to add the 3000+ Twemoji icons to a Font.
    - If you use Twemoji, the phrases `[+saxophone]` and `[+🎷]` will each show a saxophone icon.
    - This also works with multipart emoji, such as `[+call me hand, medium-dark skin tone]` and `[+🤙🏾]`.

The special modes that can be used in place of scaling are:

- `black outline`, which can be used with the style names `BLACK OUTLINE` or `BLACKEN`.
- `white outline`, which can be used with the style names `WHITE OUTLINE` or `WHITEN`.
- `shiny`, which can be used with the style names `SHINY`, `SHINE`, or `GLOSSY`.
- `drop shadow` or `shadow`, which can be used with the style names `SHADOW`, `DROPSHADOW`, or `DROP SHADOW`.
- `error`, which can be used with the style names `ERROR`, `REDLINE`, or `RED LINE`.
- `warn`, which can be used with the style names `WARN`, `YELLOWLINE`, or `YELLOW LINE`.
- `note`, which can be used with the style names `NOTE`, `INFO`, `BLUELINE`, or `BLUE LINE`.
- `jostle`, which can be used with the style names `JOSTLE`, `WOBBLE`, or `SCATTER`.
    - The jostle mode can also be used with `[%?]`.
- `small caps`, which can be used with the style names `SMALLCAPS` or `SMALL CAPS`.
  - The small caps mode can also be used with `[%^]`. It cannot be used with the `[%?small caps]` syntax; it needs a caret.

The small caps mode can be used with any of the other modes except for jostle. Other than that, no two modes can be
active at the same time, and no modes can be used at the same time as scaling.

## But wait, there's fonts!

Textratypist makes heavy use of its new `Font` class, which is a full overhaul of libGDX's BitmapFont that shares
essentially no code with its ancestor. A Font has various qualities that give it more power than BitmapFont, mostly
derived from how it stores (and makes available) the glyph images as TextureRegions in a map. There's nothing strictly
preventing you from adding your own images to the `mapping` of a Font, as long as they have the requisite information to
be used as a textual glyph, and then placing those images in with your text. Textratypist supports standard bitmap
fonts and also distance field fonts, using SDF or MSDF. `TypingLabel` will automatically enable the ShaderProgram that
the appropriate distance field type needs (if it needs one) and disable it after rendering itself. You can change this
behavior by manually calling the `Font.enableShader(Batch)` method on your Font, and changing the Batch back to your
other ShaderProgram of choice with its `Batch.setShader()` method (often, you just pass null here to reset the shader).

There are several preconfigured font settings in `KnownFonts`; the documentation for each font getter says what files
are needed to use that font. This is meant to save some hassle getting the xAdjust, yAdjust, widthAdjust, 
and heightAdjust parameters just right, though you're still free to change them however you wish. The variety of font
types isn't amazing, but it should be a good starting point. One nice new thing to note is the
`KnownFonts.getStandardFamily()` method, which requires having 13 fonts in your assets, but naturally lets you switch
between any of those 13 fonts using the `[@Medieval]` syntax (where Medieval is one of the names it knows, in this case
for "KingThings Foundation"). All of these fonts work without a distance field effect, so they won't look as good at
very large sizes, but are compatible with each other.

The Twemoji icons are also present in an atlas of over-3000 32x32 images; `KnownFonts.addEmoji()` can register them with
a Font so the `[+name]` syntax mentioned above can draw emoji inline.

The license files for each font are included in the same folder, in `knownFonts` here. All fonts provided here were
checked to ensure their licenses permit commercial use without fees, and all do. Most require attribution; check the
licenses for details.

## Act now and get these features, free of charge!

You can rotate individual glyphs (if you draw them individually) or rotate whole blocks of text as a Layout, using an
optional overload of `Font.drawGlyph()` or `Font.drawGlyphs()`. Custom effects for `TypingLabel` can also individually
change the rotation of any glyph, as well as its position and scale on x and/or y. You can rotate a TextraLabel or
TypingLabel by using their `setRotation()` methods, and the rotation will now act correctly for labels with backgrounds
and/or with different alignment settings. The origin for rotations can be set in the label, and the whole label will
rotate around that origin point. You can also, for some fonts, have
box-drawing characters and block elements be automatically generated. This needs a solid white block character (of any
size, typically 1x1) present in the font at id 9608 (the Unicode full block index, `'\u2588'`). This also enables
a better guarantee of underline and strikethrough characters connecting properly, and without smudging where two
underscores or hyphens overlap each other. `Font` attempts to enable this in some cases, or it can be set with a
parameter, but if it fails then it falls back to using underscores for underline and hyphens for strikethrough. All the
fonts in `KnownFonts` either are configured to use a solid block or to specifically avoid it because that font renders
better without it. Note that if you create a `Font` from a libGDX `BitmapFont`, this defaults to not even trying to make
grid glyphs, because BitmapFonts rarely have a suitable solid block char.

These two features are new in 0.3.0, and are expected to see more attention in future releases.

## Hold the phone, there's widgets!

Starting in the 0.4.0 release, there are various widgets that replace their
scene2d.ui counterparts and swap out `Label` for `TextraLabel`, allowing you to use markup in them.
The widgets are `ImageTextraButton`, `TextraButton`, `TextraCheckBox`, `TextraDialog`, `TextraLabel`, `TextraTooltip`, 
and `TextraWindow`, at least, so far.

Future additions to these widgets should permit setting the `TextraLabel` to a `TypingLabel` of your choice.
While `TextArea` is not yet supported, `TextraLabel` defaults to supporting multiple lines, and may be able to stand-in
for some usage. A counterpart to `TextArea` is planned.

## What about my input?

Input tracking has been an option for `TypingLabel` and code that uses it since 0.7.0 . This expanded in 0.7.4 to allow
the text in a `TypingLabel` to be made selectable with `label.setSelectable(true)`. You can access the
currently-selected text with `label.getSelectedText()` or copy it directly with `label.copySelectedText()`. When the
user completes a click and drag gesture over the TypingLabel (and it is selectable), an event is triggered as well; you
can listen for `"*SELECTED"` in a `TypingListener` and copy text as soon as it is selected, or only copy when some key
is pressed. Other useful features that use input tracking include the `{LINK}` tag, which makes a span of text a
clickable link to an Internet address, `{TRIGGER}`, which triggers an event on-click, and a few other tags that respond
to mouse hovering (`{ATTENTION}`, `{HIGHLIGHT}`, and `{STYLIST}`). These only work for `TypingLabel`, not `TextraLabel`,
so you may want to use a `TypingLabel` and call `skipToTheEnd()` to treat it like still text that happens to respond to
user input and can use animated styles like `{RAINBOW}`.

## How do I get it?

You probably want to get this with Gradle! The dependency for a libGDX project's core module looks like:

```groovy
implementation "com.github.tommyettinger:textratypist:0.7.5"
```

This assumes you already depend on libGDX; TextraTypist depends on version 1.11.0 or higher. The requirement for 1.11.0
was added in TextraTypist 0.5.0 because of some breaking changes in tooltip code in libGDX.

If you use GWT, this should be compatible. It needs these dependencies in the html module:

```groovy
implementation "com.github.tommyettinger:textratypist:0.7.5:sources"
implementation "com.github.tommyettinger:regexodus:0.1.14:sources"
```

GWT also needs this in the GdxDefinition.gwt.xml file (as of version 0.7.5):
```xml
<inherits name="regexodus.regexodus" />
<inherits name="com.github.tommyettinger.textratypist" />
```

In version 0.7.4 and earlier, you would an earlier version of both dependencies:


```groovy
implementation "com.github.tommyettinger:textratypist:0.7.4:sources"
implementation "com.github.tommyettinger:regexodus:0.1.13:sources"
```

use these GWT inherits instead:
```xml
<inherits name="regexodus" />
<inherits name="textratypist" />
```

RegExodus is the GWT-compatible regular-expression library this uses to match some complex patterns internally. Other
than libGDX itself, RegExodus is the only dependency this project has. The GWT inherits changed for TextraTypist and for
RegExodus because it turns out using the default package can cause real problems.

There is at least one release in the [Releases](https://github.com/tommyettinger/textratypist/releases) section of this
repo, but you're still encouraged to use Gradle to handle this library and its dependencies.

## Why doesn't something work?

Some parts of TextraTypist act differently from their counterparts in scene2d.ui and Rafa Skoberg's typing-label.

A big quirk is that `Font` and `BitmapFont` have some core disagreements about how to parse a `.fnt` file, and the
results of creating a `Font` with `new Font("MyFont.fnt")` can be different from
`new Font(new BitmapFont(Gdx.files.internal("MyFont.fnt")))`. `BitmapFont` reads in padding information ([and does so
incorrectly according to the BMFont spec](https://github.com/libgdx/libgdx/pull/4297)), where `Font` ignores padding
information entirely. Some `.fnt` files have been made so they look right in libGDX by using padding, but they will look
wrong in other frameworks/engines without that padding. `Font` compromises by allowing manual adjustment of x and y
position for all glyphs (y often needs to be adjusted, either to a positive or negative value), as well as the width and
height of glyphs (these are useful less frequently, and are mostly used by distance field fonts).

If you load text from a file and display it, you can sometimes get different results from creating that text in code, or
loading it on a different machine. This should only happen if the file actually is different -- that is, the files' line
endings use `\r\n` when checked out with Git on a Windows machine, or `\n` on MacOS or Linux machines. TextraTypist uses
`\r` to mark some kinds of "soft" line breaks that can be re-wrapped, and `\n` for "hard" line breaks that must always
create a new line. Having `\r\n` present generally shows up as two lines for every line break. A simple solution that
works for many projects is to include a `.gitattributes` file in your project root, [like the one here](.gitattributes).
This can be used to force all text files or all text files with a certain file extension to use `LF` mode, where only a
single `\n` is used for line breaks. It's still recommended to keep `.bat` files using `CRLF` mode, with `\r\n` used,
for compatibility. Using `.gitattributes` from the start is a good idea, and should keep files exactly the same on all
current OSes. Older Windows programs (like Notepad from Windows 7) aren't able to read `\n` line endings, but the
versions distributed with recent Windows can use `\n` easily, as can almost all code-oriented text editors.

Colors can be written out as hex strings, like `#FF7700` or `#9783EDFF`, given by name, or described using a simple
syntax. The full list of (case-sensitive!) names can be seen ordered
[by hue](https://tommyettinger.github.io/textratypist/ColorTableHue.html),
[by lightness](https://tommyettinger.github.io/textratypist/ColorTableLightness.html),
or [by name](https://tommyettinger.github.io/textratypist/ColorTableAlphabetical.html). You can take one or more of
these color names, optionally add adjectives like "light" or "dull", and get a color that mixes the named colors and
applies changes from the adjectives. There are some tricky things here:

  - The only adjectives this understands are "light", "dark", "rich", "dull", "bright", "pale", "deep", and "weak", plus
    the stronger versions of those such as "darker", "palest", and "dullmost". Any adjectives this doesn't know, this
    ignores entirely.
    - "light" and "dark" change lightness.
    - "rich" and "dull" change saturation.
    - "bright" raises lightness and raises saturation.
    - "pale" raises lightness and lowers saturation.
    - "deep" lowers lightness and raises saturation.
    - "weak" lowers lightness and lowers saturation.
  - Color names are case-sensitive. Some names are from the libGDX `Colors` class, and are `ALL_CAPS`, sometimes with
    underscores. Other names are from colorful-gdx, and are `lowercased` single words. In a few cases, the same word
    refers to a different color value if you use ALL_CAPS or use lowercase (`ORANGE` and `orange` are a good example).
  - If there isn't a color name present in a description, the result is the int 256 (`0x00000100`), or fully transparent
    very dark blue, which is used as a placeholder because visually it is the same as transparent black. If a color does
    wind up as 256 at the time it is finally rendered, it will probably be ignored.
  - You can add colors to `Palette` with its static `addColor()` method. This makes another color name usable, but won't
    retroactively make that color name parse correctly. You may have to call methods like `Font.markup()` again, so it's
    best if you can change colors before using them.

If you encounter issues with TypingLabel tokens, and you use ProGuard, the configuration for that tool needs a small
addition:
```
-keep class com.github.tommyettinger.textra.** { *; }
```
There may be more strict versions of this ProGuard instruction possible, but at the very least, the
`com.github.tommyettinger.textra.effects` package needs to be kept as-is, for reflection reasons. You may also need to
ensure the `com.github.tommyettinger.textra.Effect` class is kept. Keeping all of TextraTypist should be perfectly fine
for obfuscation purposes because this is an open-source library, but it does add a small amount to the size of the final
JAR or APK. Right now, that appears to be 202 KB if you don't include any assets, so I wouldn't worry about it.

Distance field fonts generally seem more useful than they actually are, as implemented here. Both SDF and MSDF fonts use
a shader to handle size changes, but the way things are now, the shader only changes when the scale of a `Font` is
changed, not when it has its size changed inline using (for example) the `[%200]` tag, or changed with an effect like
`{SQUASH}`. In these cases, the enlarged text just looks blurry, though it is worse with MSDF. Using a standard font
actually looks a lot better for these small-to-moderate size adjustments. Because it doesn't need a different
shader, the standard fonts are much more compatible with things like emoji, and can be used in the same batch as
graphics that use the default SpriteBatch shader. SDF fonts support kerning, and `Gentium-sdf.fnt` uses both an SDF
effect and kerning, but MSDF fonts have to be created through a cumbersome process that does not support kerning. This
makes MSDF fonts only work their best for fixed-width typefaces. The best approach most of the time seems to be to use a
large standard font texture, without SDF or MSDF, and scale it down as needed.

If you happen to use both tommyettinger's TextraTypist library and tommyettinger's
[colorful-gdx](https://github.com/tommyettinger/colorful-gdx) library, you may encounter various issues. `ColorfulBatch`
and `ColorfulSprite` both are incompatible with how TextraTypist sends vertex information to a Batch, though it would be
possible to extend Font and override any of its code that handles vertices. Color description can be done by both
colorful-gdx's `SimplePalette` and `ColorUtils.describe()` here, but descriptions would really need to use the RGBA
color space to work as expected. Alternative shaders from colorful-gdx's `Shaders` class generally won't work correctly
with the known fonts here and the defaults for neutral colors (here, white is the neutral color, but in most shaders
that allow lightening, 50% gray is the neutral color). The easiest solution for all this is to use a normal, vanilla
`SpriteBatch` for TextraTypist rendering, and whatever `ShaderProgram` or `ColorfulBatch` you want for colorful-gdx
rendering. (Some changes here are planned that could resolve this issue, and also make things easier for games that use
custom Batches with different attributes.)

There are some known issues with scaling, rotation, and integer-positioning in 0.7.5. You may notice labels slide
relatively to their backgrounds when rotated smoothly, and some (typically very small) fonts may need integer positions
enabled to keep a stable baseline. Font debug lines may be quite incorrect in this version, also, even if the text
displays correctly to users.

## License

This is based very closely on [typing-label](https://github.com/rafaskb/typing-label), by Rafa Skoberg.
Typing-label is MIT-licensed according to its repo `LICENSE` file, but (almost certainly unintentionally) does not
include any license headers in any files. Since the only requirement of the MIT license is to leave any license text
as-is, this Apache-licensed project is fully compliant with MIT. The full MIT license text is in the file
`typing-label.LICENSE`, and the Apache 2 license for this project is in the file `LICENSE`. Apache license headers are
also present in all library source files here. The Apache license does not typically apply to non-code resources in the
`src/test/resources` folder; individual fonts have their own licenses stored in that directory.

Twemoji isn't a font, so it might be best to mention it separately. It's licensed under CC-BY 4.0, and requires
attribution to Twitter if used. 
[Twemoji's guidelines for attribution are here](https://github.com/twitter/twemoji#attribution-requirements).

The logo was made by Raymond "raeleus" Buckley and contributed to this project. It can be used freely for any purpose,
but I request that it only be used to refer to this project unless substantially modified.

## Thanks

Wow, raeleus has really helped a tremendous amount. Both by testing TextraTypist in his Skin Composer app (which found
quite a lot of bugs, small and large), and advising on proper scene2d.ui layout practices (which were not easy to get
100% right), the large 0.5.2 release (and those after it) would not be what it is today without his input. Thank you!

Thanks to fraudo for helping me go step-by-step to figure out how badly I had screwed up rotation with backgrounds, and
for writing most of `LabelRotationTest`. Release 0.5.5 would still probably be in development for months without that
help, so thanks are in order.

Thanks to piotr-j (evilentity), mas omenos, and DMC from the libGDX Discord, for really thoroughly testing TextraTypist.
`IncongruityTest` was originally piotr-j's work and it helped me figure out which fonts in KnownFonts had incorrect
bounds information. `TableWrapTest` was based closely on mas omenos' work, and was useful to locate a wrapping bug. DMC
managed to track down a very elusive ProGuard issue, which is now documented in this README.md .

Of course, I have to thank Rafa Skoberg for writing quite a lot of the code here! About 2/3 of the effects are almost
purely by Rafa, much of the TypingLabel-related code is nearly unchanged from his work, and in general he showed what
libGDX UIs could be just by making the initial code.

Thanks to all the font designers who made fonts we use here; by making your fonts freely available, you perform a great
service to the people who depend on them.

Thanks to Twitter for generously contributing Twemoji to the world of open source; having broadly available emoji makes
them much more usable. Note that because this was a generous action by Twitter, it happened before its acquisition.
