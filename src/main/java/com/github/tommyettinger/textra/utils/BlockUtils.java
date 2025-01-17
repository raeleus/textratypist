/*
 * Copyright (c) 2022-2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tommyettinger.textra.utils;

public class BlockUtils {

    public static final float THIN_START = 0.45f;
    public static final float THIN_END = 0.55f;
    public static final float THIN_ACROSS = 0.1f;
    public static final float THIN_OVER = 1f - THIN_START;

    public static final float WIDE_START = 0.4f;
    public static final float WIDE_END = 0.6f;
    public static final float WIDE_ACROSS = 0.2f;
    public static final float WIDE_OVER = 1f - WIDE_START;

    public static final float TWIN_START1 = 0.35f;
    public static final float TWIN_END1 = 0.45f;
    public static final float TWIN_START2 = 0.55f;
    public static final float TWIN_END2 = 0.65f;
    public static final float TWIN_ACROSS = 0.1f;
    public static final float TWIN_OVER1 = 1f - TWIN_START1;
    public static final float TWIN_OVER2 = 1f - TWIN_START2;

    /**
     * Returns true if the given char can be handled by the box drawing and block element data here, or false if the
     * Font should try to handle that char itself.
     *
     * @param c a char (or int in the char range, 0-65535) that could potentially be a box drawing/block element char
     * @return true if c is a box drawing/block element char that this can handle
     */
    public static boolean isBlockGlyph(int c) {
        return (c >= '\u2500' && c <= '\u256C') || (c >= '\u2574' && c <= '\u2590') || (c >= '\u2594' && c <= '\u259F');
    }

    // So, what's the plan here...
    // Each glyph that isn't commented out here can be drawn using only axis-aligned rectangles.
    // The idea is to list the rectangles needed to draw this character, measured in fractions of a cell, so monospace
    // fonts that don't otherwise line up correctly can be made to fill a square (or other) cell.
    // The format is left x, lower y, width, height, ... (may have more rectangles in groups of four).
    public static final float[][] BOX_DRAWING = new float[][]{
            /* ─ : u2500 */ {0, THIN_START, 1, THIN_ACROSS},
            /* ━ : u2501 */ {0, WIDE_START, 1, WIDE_ACROSS},
            /* │ : u2502 */ {THIN_START, 0, THIN_ACROSS, 1},
            /* ┃ : u2503 */ {WIDE_START, 0, WIDE_ACROSS, 1},
            /* ┄ : u2504 */ {0, THIN_START, 0.2f, THIN_ACROSS, 0.4f, THIN_START, 0.2f, THIN_ACROSS, 0.8f, THIN_START, 0.2f, THIN_ACROSS},
            /* ┅ : u2505 */ {0, WIDE_START, 0.2f, WIDE_ACROSS, 0.4f, WIDE_START, 0.2f, WIDE_ACROSS, 0.8f, WIDE_START, 0.2f, WIDE_ACROSS},
            /* ┆ : u2506 */ {THIN_START, 0, THIN_ACROSS, 0.2f, THIN_START, 0.4f, THIN_ACROSS, 0.2f, THIN_START, 0.8f, THIN_ACROSS, 0.2f},
            /* ┇ : u2507 */ {WIDE_START, 0, WIDE_ACROSS, 0.2f, WIDE_START, 0.4f, WIDE_ACROSS, 0.2f, WIDE_START, 0.8f, WIDE_ACROSS, 0.2f},
            /* ┈ : u2508 */ {0, THIN_START, 1 / 7f, THIN_ACROSS, 2 / 7f, THIN_START, 1 / 7f, THIN_ACROSS, 4 / 7f, THIN_START, 1 / 7f, THIN_ACROSS, 6 / 7f, THIN_START, 1 / 7f, THIN_ACROSS},
            /* ┉ : u2509 */ {0, WIDE_START, 1 / 7f, WIDE_ACROSS, 2 / 7f, WIDE_START, 1 / 7f, WIDE_ACROSS, 4 / 7f, WIDE_START, 1 / 7f, WIDE_ACROSS, 6 / 7f, WIDE_START, 1 / 7f, WIDE_ACROSS},
            /* ┊ : u250A */ {THIN_START, 0, THIN_ACROSS, 1 / 7f, THIN_START, 2 / 7f, THIN_ACROSS, 1 / 7f, THIN_START, 4 / 7f, THIN_ACROSS, 1 / 7f, THIN_START, 6 / 7f, THIN_ACROSS, 1 / 7f},
            /* ┋ : u250B */ {WIDE_START, 0, WIDE_ACROSS, 1 / 7f, WIDE_START, 2 / 7f, WIDE_ACROSS, 1 / 7f, WIDE_START, 4 / 7f, WIDE_ACROSS, 1 / 7f, WIDE_START, 6 / 7f, WIDE_ACROSS, 1 / 7f},
            /* ┌ : u250C */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┍ : u250D */ {THIN_START, WIDE_START, THIN_OVER, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, WIDE_OVER},
            /* ┎ : u250E */ {WIDE_START, THIN_START, WIDE_OVER, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, THIN_OVER},
            /* ┏ : u250F */ {WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┐ : u2510 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┑ : u2511 */ {0, WIDE_START, THIN_OVER, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, WIDE_OVER},
            /* ┒ : u2512 */ {0, THIN_START, WIDE_OVER, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, THIN_OVER},
            /* ┓ : u2513 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* └ : u2514 */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┕ : u2515 */ {THIN_START, WIDE_START, THIN_OVER, WIDE_ACROSS, THIN_START, WIDE_START, THIN_ACROSS, WIDE_OVER},
            /* ┖ : u2516 */ {WIDE_START, THIN_START, WIDE_OVER, THIN_ACROSS, WIDE_START, THIN_START, WIDE_ACROSS, THIN_OVER},
            /* ┗ : u2517 */ {WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ┘ : u2518 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┙ : u2519 */ {0, WIDE_START, THIN_OVER, WIDE_ACROSS, THIN_START, WIDE_START, THIN_ACROSS, WIDE_OVER},
            /* ┚ : u251A */ {0, THIN_START, WIDE_OVER, THIN_ACROSS, WIDE_START, THIN_START, WIDE_ACROSS, THIN_OVER},
            /* ┛ : u251B */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ├ : u251C */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┝ : u251D */ {THIN_START, WIDE_START, THIN_OVER, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┞ : u251E */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER, WIDE_START, THIN_START, WIDE_ACROSS, THIN_OVER},
            /* ┟ : u251F */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┠ : u2520 */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ┡ : u2521 */ {WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┢ : u2522 */ {WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┣ : u2523 */ {WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ┤ : u2524 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┥ : u2525 */ {0, WIDE_START, THIN_OVER, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┦ : u2526 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER, WIDE_START, THIN_START, WIDE_ACROSS, THIN_OVER},
            /* ┧ : u2527 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┨ : u2528 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ┩ : u2529 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┪ : u252A */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┫ : u252B */ {0, WIDE_START, THIN_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ┬ : u252C */ {0, THIN_START, 1, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┭ : u252D */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┮ : u252E */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┯ : u252F */ {0, WIDE_START, 1, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ┰ : u2530 */ {0, THIN_START, 1, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┱ : u2531 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┲ : u2532 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┳ : u2533 */ {0, WIDE_START, 1, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ┴ : u2534 */ {0, THIN_START, 1, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┵ : u2535 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┶ : u2536 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┷ : u2537 */ {0, WIDE_START, 1, WIDE_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ┸ : u2538 */ {0, THIN_START, 1, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ┹ : u2539 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ┺ : u253A */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ┻ : u253B */ {0, WIDE_START, 1, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ┼ : u253C */ {0, THIN_START, 1, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┽ : u253D */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┾ : u253E */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ┿ : u253F */ {0, WIDE_START, 1, WIDE_ACROSS, THIN_START, 0, THIN_ACROSS, 1},
            /* ╀ : u2540 */ {0, THIN_START, 1, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ╁ : u2541 */ {0, THIN_START, 1, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ╂ : u2542 */ {0, THIN_START, 1, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ╃ : u2543 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ╄ : u2544 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ╅ : u2545 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ╆ : u2546 */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ╇ : u2547 */ {0, WIDE_START, 1, WIDE_ACROSS, WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ╈ : u2548 */ {0, WIDE_START, 1, WIDE_ACROSS, THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ╉ : u2549 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ╊ : u254A */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ╋ : u254B */ {0, WIDE_START, 1, WIDE_ACROSS, WIDE_START, 0, WIDE_ACROSS, 1},
            /* ╌ : u254C */ {0.125f, THIN_START, 0.25f, THIN_ACROSS, 0.625f, THIN_START, 0.25f, THIN_ACROSS},
            /* ╍ : u254D */ {0.125f, WIDE_START, 0.25f, WIDE_ACROSS, 0.625f, WIDE_START, 0.25f, WIDE_ACROSS},
            /* ╎ : u254E */ {THIN_START, 0.125f, THIN_ACROSS, 0.25f, THIN_START, 0.625f, THIN_ACROSS, 0.25f},
            /* ╏ : u254F */ {WIDE_START, 0.125f, WIDE_ACROSS, 0.25f, WIDE_START, 0.625f, WIDE_ACROSS, 0.25f},
            /* ═ : u2550 */ {0, TWIN_START1, 1, TWIN_ACROSS, 0, TWIN_START2, 1, TWIN_ACROSS},
            /* ║ : u2551 */ {TWIN_START1, 0, TWIN_ACROSS, 1, TWIN_START2, 0, TWIN_ACROSS, 1},
            /* ╒ : u2552 */ {THIN_START, 0, THIN_ACROSS, TWIN_OVER1, THIN_START, TWIN_START1, THIN_OVER, TWIN_ACROSS, THIN_START, TWIN_START2, THIN_OVER, TWIN_ACROSS},
            /* ╓ : u2553 */ {TWIN_START1, 0, TWIN_ACROSS, THIN_OVER, TWIN_START2, 0, TWIN_ACROSS, THIN_OVER, TWIN_START1, THIN_START, TWIN_OVER1, THIN_ACROSS},
            /* ╔ : u2554 */ {TWIN_START1, 0, TWIN_ACROSS, TWIN_OVER1, TWIN_START2, 0, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START1, TWIN_OVER2, TWIN_ACROSS, TWIN_START1, TWIN_START2, TWIN_OVER1, TWIN_ACROSS},
            /* ╕ : u2555 */ {THIN_START, 0, THIN_ACROSS, TWIN_OVER1, 0, TWIN_START1, THIN_OVER, TWIN_ACROSS, 0, TWIN_START2, THIN_OVER, TWIN_ACROSS},
            /* ╖ : u2556 */ {TWIN_START1, 0, TWIN_ACROSS, THIN_OVER, TWIN_START2, 0, TWIN_ACROSS, THIN_OVER, 0, THIN_START, TWIN_OVER1, THIN_ACROSS},
            /* ╗ : u2557 */ {TWIN_START1, 0, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, 0, TWIN_ACROSS, TWIN_OVER1, 0, TWIN_START1, TWIN_OVER2, TWIN_ACROSS, 0, TWIN_START2, TWIN_OVER1, TWIN_ACROSS},
            /* ╘ : u2558 */ {THIN_START, TWIN_START1, THIN_OVER, TWIN_ACROSS, THIN_START, TWIN_START2, THIN_OVER, TWIN_ACROSS, THIN_START, TWIN_START1, THIN_ACROSS, TWIN_OVER1},
            /* ╙ : u2559 */ {TWIN_START1, THIN_START, TWIN_ACROSS, THIN_OVER, TWIN_START2, THIN_START, TWIN_ACROSS, THIN_OVER, TWIN_START1, THIN_START, TWIN_OVER1, THIN_ACROSS},
            /* ╚ : u255A */ {TWIN_START1, TWIN_START1, TWIN_ACROSS, TWIN_OVER1, TWIN_START2, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, TWIN_START1, TWIN_START1, TWIN_OVER1, TWIN_ACROSS, TWIN_START2, TWIN_START2, TWIN_OVER2, TWIN_ACROSS},
            /* ╛ : u255B */ {THIN_START, TWIN_START1, THIN_ACROSS, TWIN_OVER1, 0, TWIN_START1, THIN_OVER, TWIN_ACROSS, 0, TWIN_START2, THIN_OVER, TWIN_ACROSS},
            /* ╜ : u255C */ {TWIN_START1, THIN_START, TWIN_ACROSS, THIN_OVER, TWIN_START2, THIN_START, TWIN_ACROSS, THIN_OVER, 0, THIN_START, TWIN_OVER1, THIN_ACROSS},
            /* ╝ : u255D */ {TWIN_START1, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START1, TWIN_ACROSS, TWIN_OVER1, 0, TWIN_START1, TWIN_OVER1, TWIN_ACROSS, 0, TWIN_START2, TWIN_OVER2, TWIN_ACROSS},
            /* ╞ : u255E */ {THIN_START, 0, THIN_ACROSS, 1, THIN_START, TWIN_START1, THIN_OVER, TWIN_ACROSS, THIN_START, TWIN_START2, THIN_OVER, TWIN_ACROSS},
            /* ╟ : u255F */ {TWIN_START1, 0, TWIN_ACROSS, 1, TWIN_START2, 0, TWIN_ACROSS, 1, TWIN_START2, THIN_START, TWIN_OVER2, THIN_ACROSS},
            /* ╠ : u2560 */ {TWIN_START1, 0, TWIN_ACROSS, 1, TWIN_START2, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START2, TWIN_OVER2, TWIN_ACROSS, TWIN_START2, 0, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START1, TWIN_OVER2, TWIN_ACROSS},
            /* ╡ : u2561 */ {THIN_START, 0, THIN_ACROSS, 1, 0, TWIN_START1, THIN_OVER, TWIN_ACROSS, 0, TWIN_START2, THIN_OVER, TWIN_ACROSS},
            /* ╢ : u2562 */ {TWIN_START1, 0, TWIN_ACROSS, 1, TWIN_START2, 0, TWIN_ACROSS, 1, 0, THIN_START, TWIN_START1, THIN_ACROSS},
            /* ╣ : u2563 */ {TWIN_START2, 0, TWIN_ACROSS, 1, TWIN_START1, 0, TWIN_ACROSS, TWIN_OVER2, 0, TWIN_START1, TWIN_OVER2, TWIN_ACROSS, TWIN_START1, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, 0, TWIN_START2, TWIN_OVER2, TWIN_ACROSS},
            /* ╤ : u2564 */ {THIN_START, 0, THIN_ACROSS, TWIN_START1, 0, TWIN_START1, 1, TWIN_ACROSS, 0, TWIN_START2, 1, TWIN_ACROSS},
            /* ╥ : u2565 */ {TWIN_START1, 0, TWIN_ACROSS, THIN_OVER, TWIN_START2, 0, TWIN_ACROSS, THIN_OVER, 0, THIN_START, 1, THIN_ACROSS},
            /* ╦ : u2566 */ {0, TWIN_START2, 1, TWIN_ACROSS, TWIN_START2, 0, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START1, TWIN_OVER2, TWIN_ACROSS, TWIN_START1, 0, TWIN_ACROSS, TWIN_OVER2, 0, TWIN_START1, TWIN_OVER2, TWIN_ACROSS},
            /* ╧ : u2567 */ {THIN_START, TWIN_START2, THIN_ACROSS, TWIN_OVER2, 0, TWIN_START1, 1, TWIN_ACROSS, 0, TWIN_START2, 1, TWIN_ACROSS},
            /* ╨ : u2568 */ {TWIN_START1, THIN_START, TWIN_ACROSS, THIN_OVER, TWIN_START2, THIN_START, TWIN_ACROSS, THIN_OVER, 0, THIN_START, 1, THIN_ACROSS},
            /* ╩ : u2569 */ {0, TWIN_START1, 1, TWIN_ACROSS, TWIN_START2, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START2, TWIN_OVER2, TWIN_ACROSS, TWIN_START1, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, 0, TWIN_START2, TWIN_OVER2, TWIN_ACROSS},
            /* ╪ : u256A */ {THIN_START, 0, THIN_ACROSS, 1, 0, TWIN_START1, 1, TWIN_ACROSS, 0, TWIN_START2, 1, TWIN_ACROSS},
            /* ╫ : u256B */ {TWIN_START1, 0, TWIN_ACROSS, 1, TWIN_START2, 0, TWIN_ACROSS, 1, 0, THIN_START, 1, THIN_ACROSS},
            /* ╬ : u256C */ {TWIN_START2, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START2, TWIN_OVER2, TWIN_ACROSS, TWIN_START2, 0, TWIN_ACROSS, TWIN_OVER2, TWIN_START2, TWIN_START1, TWIN_OVER2, TWIN_ACROSS, TWIN_START1, 0, TWIN_ACROSS, TWIN_OVER2, 0, TWIN_START1, TWIN_OVER2, TWIN_ACROSS, TWIN_START1, TWIN_START2, TWIN_ACROSS, TWIN_OVER2, 0, TWIN_START2, TWIN_OVER2, TWIN_ACROSS},
            /* ╭ : u256D */ {}, // NOT USED
            /* ╮ : u256E */ {}, // NOT USED
            /* ╯ : u256F */ {}, // NOT USED
            /* ╰ : u2570 */ {}, // NOT USED
            /* ╱ : u2571 */ {}, // NOT USED
            /* ╲ : u2572 */ {}, // NOT USED
            /* ╳ : u2573 */ {}, // NOT USED
            /* ╴ : u2574 */ {0, THIN_START, THIN_OVER, THIN_ACROSS},
            /* ╵ : u2575 */ {THIN_START, THIN_START, THIN_ACROSS, THIN_OVER},
            /* ╶ : u2576 */ {THIN_START, THIN_START, THIN_OVER, THIN_ACROSS},
            /* ╷ : u2577 */ {THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ╸ : u2578 */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS},
            /* ╹ : u2579 */ {WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER},
            /* ╺ : u257A */ {WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS},
            /* ╻ : u257B */ {WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ╼ : u257C */ {0, THIN_START, THIN_OVER, THIN_ACROSS, WIDE_START, WIDE_START, WIDE_OVER, WIDE_ACROSS},
            /* ╽ : u257D */ {THIN_START, THIN_START, THIN_ACROSS, THIN_OVER, WIDE_START, 0, WIDE_ACROSS, WIDE_OVER},
            /* ╾ : u257E */ {0, WIDE_START, WIDE_OVER, WIDE_ACROSS, THIN_START, THIN_START, THIN_OVER, THIN_ACROSS},
            /* ╿ : u257F */ {WIDE_START, WIDE_START, WIDE_ACROSS, WIDE_OVER, THIN_START, 0, THIN_ACROSS, THIN_OVER},
            /* ▀ : u2580 */ {0, 0.5f, 1, 0.5f},
            /* ▁ : u2581 */ {0, 0, 1, 0.125f},
            /* ▂ : u2582 */ {0, 0, 1, 0.25f},
            /* ▃ : u2583 */ {0, 0, 1, 0.375f},
            /* ▄ : u2584 */ {0, 0, 1, 0.5f},
            /* ▅ : u2585 */ {0, 0, 1, 0.625f},
            /* ▆ : u2586 */ {0, 0, 1, 0.75f},
            /* ▇ : u2587 */ {0, 0, 1, 0.875f},
            /* █ : u2588 */ {0, 0, 1, 1},
            /* ▉ : u2589 */ {0, 0, 0.875f, 1},
            /* ▊ : u258A */ {0, 0, 0.75f, 1},
            /* ▋ : u258B */ {0, 0, 0.625f, 1},
            /* ▌ : u258C */ {0, 0, 0.5f, 1},
            /* ▍ : u258D */ {0, 0, 0.375f, 1},
            /* ▎ : u258E */ {0, 0, 0.25f, 1},
            /* ▏ : u258F */ {0, 0, 0.125f, 1},
            /* ▐ : u2590 */ {0.5f, 0, 0.5f, 1},
            /* ░ : u2591 */ {}, // NOT USED
            /* ▒ : u2592 */ {}, // NOT USED
            /* ▓ : u2593 */ {}, // NOT USED
            /* ▔ : u2594 */ {0, 0.875f, 1, 0.125f},
            /* ▕ : u2595 */ {0.875f, 0, 0.125f, 1},
            /* ▖ : u2596 */ {0, 0, 0.5f, 0.5f},
            /* ▗ : u2597 */ {0.5f, 0, 0.5f, 0.5f},
            /* ▘ : u2598 */ {0, 0.5f, 0.5f, 0.5f},
            /* ▙ : u2599 */ {0, 0, 0.5f, 1f, 0.5f, 0, 0.5f, 0.5f},
            /* ▚ : u259A */ {0, 0, 0.5f, 0.5f, 0.5f, 0, 0.5f, 0.5f},
            /* ▛ : u259B */ {0, 0, 0.5f, 1f, 0.5f, 0.5f, 0.5f, 0.5f},
            /* ▜ : u259C */ {0.5f, 0, 0.5f, 1, 0, 0.5f, 0.5f, 0.5f},
            /* ▝ : u259D */ {0.5f, 0.5f, 0.5f, 0.5f},
            /* ▞ : u259E */ {0, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f},
            /* ▟ : u259F */ {0.5f, 0, 0.5f, 1, 0, 0, 0.5f, 0.5f}
    };

    public static final String ALL_BLOCK_CHARS =
            "─━│┃┄┅┆┇┈┉┊┋┌┍┎┏┐┑┒┓└┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄╅╆╇╈╉╊╋╌╍╎╏═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬╴╵╶╷╸╹╺╻╼╽╾╿▀▁▂▃▄▅▆▇█▉▊▋▌▍▎▏▐▔▕▖▗▘▙▚▛▜▝▞▟";
}
